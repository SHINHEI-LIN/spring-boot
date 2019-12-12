package com.shl.springbootquick.threadpool;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.util.LinkedList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class ConnectionPool {
    // 使用一个双端队列来存储连接
    private LinkedList<Connection> connectionPool = new LinkedList<>();

    // 通过构造函数初始化线程池最大连接上限
    public ConnectionPool(Integer size) {
        for (int i = 0; i < size; i++) {
            // 将连接追加到队列的末尾
            connectionPool.addLast(ConnectionDriver.createConnection());
        }
    }

    /**
     * 释放连接，将连接追加到队列末尾, 并通知所有等待线程
     *
     * @param connection
     */
    public void releaseConnection(Connection connection) {
        if (connection != null) {
            synchronized (connectionPool) {
                connectionPool.addLast(connection);
                connectionPool.notifyAll();
            }
        }
    }

    /**
     * 获取线程池连接
     * 如果未设置超时(mills<=0)，那么在未获取到连接时直接等待；
     * 如果设置了超时时间，超时时间未过并且线程池为空时会等待连接，超时时间已过则直接返回空连接
     *
     * @param mills 设置超时时间
     * @return
     */
    public Connection fetchConnection(long mills) throws InterruptedException {
        synchronized (connectionPool) {
            if (mills <= 0) {
                while (connectionPool.size() <= 0) {
                    connectionPool.wait();
                }
                return connectionPool.removeFirst();
            } else {
                long future = System.currentTimeMillis() + mills;
                long remainMills = mills;
                while (connectionPool.size() <= 0 && remainMills > 0) {
                    connectionPool.wait(mills);
                    remainMills = future - System.currentTimeMillis();
                }
                Connection result = null;
                if (connectionPool.size() > 0) {
                    result = connectionPool.removeFirst();
                }
                return result;
            }
        }
    }

    static class ConnectionDriver {
        static class ConnectionInvoker implements InvocationHandler {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if (method.getName().equals("commit")) {
                    Thread.sleep(100);
                }
                return null;
            }
        }

        static Connection createConnection() {
            return (Connection) Proxy.newProxyInstance(ConnectionDriver.class.getClassLoader(),
                    new Class[]{Connection.class}, new ConnectionInvoker());
        }
    }

    static class ConnectionRunner implements Runnable {
        private AtomicInteger getCount;
        private AtomicInteger notGetCount;
        private int count;

        public ConnectionRunner(AtomicInteger _getCount, AtomicInteger _notGetCount, int _count) {
            getCount = _getCount;
            notGetCount = _notGetCount;
            count = _count;
        }

        @Override
        public void run() {
            try {
                // 等待所有线程一同开始
                start.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            while (count > 0) {
                try {
                    // 从线程池中获取连接，等待1秒
                    Connection connection = pool.fetchConnection(1000);
                    if (connection != null) {
                        // 拿到数据库连接
                        try {
                            connection.createStatement();
                            connection.commit();
                        } finally {
                            // 释放连接并将获取结果加1
                            pool.releaseConnection(connection);
                            getCount.getAndIncrement();
                        }
                    } else {
                        // 没拿到数据库连接，将统计结果加1
                        notGetCount.getAndIncrement();
                    }
                } catch (Exception e) {
                } finally {
                    count--;
                }
            }
            end.countDown();
        }
    }

    static ConnectionPool pool = new ConnectionPool(10);
    // 保证所有线程同时开始
    static CountDownLatch start = new CountDownLatch(1);
    // main线程会等待所有线程结束后才能继续运行
    static CountDownLatch end;

    public static void main(String[] args) throws InterruptedException {
        AtomicInteger getCount = new AtomicInteger(0);
        AtomicInteger notGetCount = new AtomicInteger(0);
        // 启动threadCount个线程, 增加线程，测试获取连接的概率
        int threadCount = 30;
        end = new CountDownLatch(threadCount);
        int getTimes = 20;
        for (int i = 0; i < threadCount; i++) {
            ConnectionRunner runner = new ConnectionRunner(getCount, notGetCount, getTimes);
            Thread thread = new Thread(runner);
            thread.start();
        }
        // 当所有线程准备完毕之后统一执行
        start.countDown();
        end.await();
        // 总共调用次数 = 启动的线程数*每个线程获取连接的次数
        System.out.println("total invoke count: " + threadCount * getTimes);
        System.out.println("got connection count: " + getCount);
        System.out.println("not get connection count: " + notGetCount);
    }
}
