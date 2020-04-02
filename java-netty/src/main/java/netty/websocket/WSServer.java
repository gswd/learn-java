package netty.websocket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

public class WSServer {
    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup boosGroup = new NioEventLoopGroup(1);
        EventLoopGroup workGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap
                    .group(boosGroup, workGroup)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            //基于http协议，试用http的编解码器
                            pipeline.addLast(new HttpServerCodec());
                            //以块方式写， ChunkedWriteHandler
                            pipeline.addLast(new ChunkedWriteHandler());
                            //http传输过程中是分段的， HttpObjectAggregator 可以将多个段聚合起来
                            pipeline.addLast(new HttpObjectAggregator(8192));
                            //websocket 的数据是以 帧(frame)形式传递, WebSocketFrame 下面有6个子类
                            //WebSocketServerProtocolHandler的功能是将 http 协议升级为 ws 协议.
                            //如何升级：Status Code: 101 Switching Protocols
                            //ws://localhost:9999/xxx
                            pipeline.addLast(new WebSocketServerProtocolHandler("/hello"));
                            //处理业务逻辑
                            pipeline.addLast(new TextWebsocketFrameHandler());
                        }
                    });

            ChannelFuture future = serverBootstrap.bind(9999).sync();
            System.out.println("Server Start...");
            future.channel().closeFuture().sync();
        } finally {
            boosGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }
}
