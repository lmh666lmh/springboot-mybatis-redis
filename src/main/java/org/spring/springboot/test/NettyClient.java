package org.spring.springboot.test;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;

import java.util.Date;

public class NettyClient {

    public static void main(String[] args) throws InterruptedException {
        Bootstrap bootstrap = new Bootstrap(); //1.
        NioEventLoopGroup group = new NioEventLoopGroup();

        bootstrap.group(group) //2.帮助处理客户端连接Reactor线程组
                .channel(NioSocketChannel.class)//3.创建客户端连接的NioSocketChannel
                .handler(new ChannelInitializer<Channel>() {
                    @Override //4.创建pipeline和ChannelHandler
                    protected void initChannel(Channel ch) {
                        ch.pipeline().addLast(new StringEncoder());
                    }
                });

        //异步发起TCP连接并判断是否成功
        Channel channel = bootstrap.connect("127.0.0.1", 8000).channel();

        while (true) {
            channel.writeAndFlush(new Date() + ": hello world!");
            Thread.sleep(2000);
        }
    }
}
