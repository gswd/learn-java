package netty.stickyBag.t_01;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

public class EchoServer {

	public static void main(String[] args) throws InterruptedException {
		EventLoopGroup boosGroup = new NioEventLoopGroup(1);
		EventLoopGroup workerGroup = new NioEventLoopGroup(1);

		try {
			ServerBootstrap b = new ServerBootstrap();
			b.group(boosGroup, workerGroup)
				.channel(NioServerSocketChannel.class)
				.handler(new LoggingHandler(LogLevel.INFO))
				.childHandler(new ChannelInitializer<SocketChannel>() {
					@Override
					protected void initChannel(SocketChannel ch) throws Exception {
						//https://www.cnblogs.com/java-chen-hao/p/11571229.html
						// 这里将LengthFieldBasedFrameDecoder添加到pipeline的首位，因为其需要对接收到的数据
						// 进行长度字段解码，这里也会对数据进行粘包和拆包处理
						ch.pipeline().addLast(new LengthFieldBasedFrameDecoder(1024, 0, 2, 0, 2));
						// LengthFieldPrepender是一个编码器，在响应字节数据前面添加字节长度字段
						ch.pipeline().addLast(new LengthFieldPrepender(2));
						ch.pipeline().addLast(new JsonDecoder());
						ch.pipeline().addLast(new JsonEncoder());
						ch.pipeline().addLast(new EchoServerHandler());
					}
				});

			ChannelFuture future = b.bind(9999).sync();
			future.channel().closeFuture().sync();
		} finally {
			boosGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}

	}

}
