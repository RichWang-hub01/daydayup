package com.wx.netty.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

/**
 * @author WX
 * @date 2026-05-24 23:33
 * @description Nio 模型的服务端
 **/
public class NioServer {
    public static void main(String[] args) throws IOException {
        // 创建一个Channel对象
        ServerSocketChannel channel = ServerSocketChannel.open();
        // 设置为非阻塞模式
        channel.configureBlocking(false);
        // 绑定端口
        channel.bind(new InetSocketAddress(8181));
        // 创建一个Selector对象
        Selector selector = Selector.open();
        // 注册selector, 监听accept事件
        channel.register(selector, SelectionKey.OP_ACCEPT);
    }
}
