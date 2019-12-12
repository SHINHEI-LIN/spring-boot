package com.shl.springbootquick.mutilthread;

public class PrintABC {
    private static volatile int count =1 ;

    static class PrintA implements Runnable {
        private Object lock;
        private String str;
        public PrintA(Object _object, String _str) {
            lock = _object;
            str = _str;
        }
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                synchronized (lock){
                    while (count != 1) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.print(str);
                    count = 2;
                    lock.notifyAll();

                }
            }
        }
    }
    static class PrintB implements Runnable {
        private Object lock;
        private String str;
        public PrintB(Object _object, String _str) {
            lock = _object;
            str = _str;
        }
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                synchronized (lock){
                    while (count != 2) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.print(str);
                    count = 3;
                    lock.notifyAll();
                }
            }
        }
    }
    static class PrintC implements Runnable {
        private Object lock;
        private String str;
        public PrintC(Object _object, String _str) {
            lock = _object;
            str = _str;
        }
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                synchronized (lock){
                    while (count != 3) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.print(str);
                    count = 1;
                    lock.notifyAll();
                }
            }
        }
    }

    public static void main(String[] args) {
        Object o = new Object();
        PrintA printA = new PrintA(o, "A");
        PrintB printB = new PrintB(o, "B");
        PrintC printC = new PrintC(o, "C");
        Thread aThread = new Thread(printA);
        Thread bThread = new Thread(printB);
        Thread cThread = new Thread(printC);
        aThread.start();
        bThread.start();
        cThread.start();
    }
}
