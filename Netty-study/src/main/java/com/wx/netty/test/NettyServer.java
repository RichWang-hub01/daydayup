package com.wx.netty.test;

import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author WX
 * @date 2026-05-24 22:20
 * @description
 *
 **/
public class NettyServer {
    private static final Logger log = Logger.getLogger(NettyServer.class);

    public static void main(String[] args) throws IOException {
        // 创建一个ServerSocket对象，并指定端口号
        ServerSocket serverSocket = new ServerSocket(8181);
        log.info("服务器已启动");

        // 创建一个循环，等待客户端连接
        while (true){
            // 等待客户端连接
            // 有客户端连接就返回一个socket
            Socket accept = serverSocket.accept();
            log.info("有客户端连接");
            InputStream inputStream = accept.getInputStream();
            byte[] buffer = new byte[1024];
            int length;
            /**
             *
             inputStream.read(buffer) - 从输入流读取数据到buffer数组中，最多读取1024字节
             length = ... - 将实际读取到的字节数赋值给length变量
             != -1 - 判断是否不等于-1（-1表示流已到达末尾，即连接关闭）
             while(...) - 只要不是-1就持续循环读取
             */
            while ( (length = inputStream.read(buffer)) != -1){
                String messege = new String(buffer, 0, length);
                log.info("接收到客户端消息：" + messege);
            }

            log.info("客户端已断开");
        }
    }
}
