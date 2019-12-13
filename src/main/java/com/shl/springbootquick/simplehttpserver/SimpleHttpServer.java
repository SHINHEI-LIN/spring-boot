package com.shl.springbootquick.simplehttpserver;

import com.shl.springbootquick.threadpool.MyThreadPool;
import com.shl.springbootquick.threadpool.MyThreadPoolImpl;
import org.springframework.util.StringUtils;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleHttpServer {
    // 处理HttpRequest的线程池
    private static MyThreadPool<MyHttpRequestHandler> threadPool = new MyThreadPoolImpl<>();
    // SimpleHttpServer的根路径
    private static String basePath;
    private static ServerSocket serverSocket;
    // 服务端口号
    private static int port;

    public static void setPort(int _port) {
        port = _port;
    }

    public static void setBasePath(String _basePath) {
        if (StringUtils.isEmpty(_basePath)) {
            return;
        }
        boolean isExit = new File(_basePath).exists();
        boolean isDirectory = new File(_basePath).isDirectory();
        if (isExit && isDirectory) {
            basePath = _basePath;
        }
    }

    public static void start() throws IOException {
        // 接收一个客户端socket，创建一个HTTP RequestHandler，放入线程池中执行
        serverSocket = new ServerSocket(port);
        Socket socket;
        while ((socket = serverSocket.accept()) != null) {
            threadPool.executor(new MyHttpRequestHandler(socket));
        }
        serverSocket.close();
    }

    static class MyHttpRequestHandler implements Runnable {
        private Socket socket;

        public MyHttpRequestHandler(Socket _socket) {
            socket = _socket;
        }

        @Override
        public void run() {
            BufferedReader reader = null;
            PrintWriter writer = null;
            ByteArrayOutputStream byteOut = null;
            InputStream inputStream = null;
            BufferedReader fileReader = null;
            PrintWriter fileWriter = null;
            try {
                reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String header = reader.readLine();
                // 由相对路径计算出绝对路径
                String filePath = basePath + header.split(" ")[1].replace("/", "\\");
                writer = new PrintWriter(socket.getOutputStream());
                if (filePath.endsWith("jpg") || filePath.endsWith("ico")) {
                    inputStream = new FileInputStream(filePath);
                    byteOut = new ByteArrayOutputStream();
                    int i;
                    while ((i = inputStream.read()) != -1) {
                        byteOut.write(i);
                    }
                    byte[] bytes = byteOut.toByteArray();
                    writer.println("HTTP/1.1 200 OK");
                    writer.println("Server: Molly");
                    writer.println("Content-Type: image/jpeg");
                    writer.println("Content-Length: " + bytes.length);
                    writer.println("");
                    socket.getOutputStream().write(bytes, 0, bytes.length);
                } else {
                    fileReader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
                    fileWriter = new PrintWriter(socket.getOutputStream());
                    fileWriter.println("HTTP/1.1 200 OK");
                    fileWriter.println("Server: Molly");
                    fileWriter.println("Content-Type: text/html; charset=UTF-8");
                    fileWriter.println("");
                    String contentLine;
                    while ((contentLine = fileReader.readLine()) != null) {
                        fileWriter.println(contentLine);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
                writer.println("HTTP/1.1 500");
                writer.println("");
            } finally {
                close(reader, writer, byteOut, inputStream, fileReader, fileWriter);
            }
            writer.flush();
            fileWriter.flush();
        }

        private void close(Closeable...closeables) {
            for (Closeable closeable : closeables) {
                if (closeable != null) {
                    try {
                        closeable.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        SimpleHttpServer.setPort(8080);
        SimpleHttpServer.setBasePath("D:\\IdeaProject\\springboot\\spring-boot-quick\\spring-boot\\src\\main\\resources\\resources");
        SimpleHttpServer.start();
    }
}
