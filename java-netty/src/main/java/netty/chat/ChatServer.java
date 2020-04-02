package netty.chat;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class ChatServer {

	private int port;

	public ChatServer(int port) {
		this.port = port;
	}

	private void go() throws InterruptedException {
		EventLoopGroup boosGroup = new NioEventLoopGroup(1);
		EventLoopGroup workGroup = new NioEventLoopGroup();

		try {
			ServerBootstrap serverBootstrap = new ServerBootstrap();
			serverBootstrap
				.group(boosGroup, workGroup)
				.channel(NioServerSocketChannel.class)
				.option(ChannelOption.SO_BACKLOG, 128)
				.childOption(ChannelOption.SO_KEEPALIVE, true)
				//.handler(new LoggingHandler(LogLevel.INFO))
				.childHandler(new ChatServerChannelInit());

			ChannelFuture future = serverBootstrap.bind(port).sync();
			System.out.println("Server Start...");
			future.channel().closeFuture().sync();
		} finally {
			boosGroup.shutdownGracefully();
			workGroup.shutdownGracefully();
		}
	}



	public static void main(String[] args) throws InterruptedException {

		ChatServer server = new ChatServer(9999);
		server.go();

	}
}
