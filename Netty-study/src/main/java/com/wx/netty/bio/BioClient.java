package com.wx.netty.bio;

import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * @author WX
 * @date 2026-05-24 22:38
 * @description
 **/
public class BioClient {
    public static void main(String[] args) throws Exception{
        Thread tom = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    sendHello();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }, "tom");

        Thread jerry = new Thread(() -> {
            try {
                sendHello();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }, "jerry");

        tom.start();
        jerry.start();
        // 等待线程结束后，主线程才会结束
        tom.join();
        jerry.join();
    }

    private static void sendHello() throws Exception{
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress("localhost", 8181));
        OutputStream outputStream = socket.getOutputStream();
        for (int i = 0; i < 10; i++) {
            outputStream.write((Thread.currentThread().getName() + "hello,第" + i + "次").getBytes());
            outputStream.flush();
        }
        socket.close();
    }
}