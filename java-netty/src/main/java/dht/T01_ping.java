package dht;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.DatagramChannel;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.handler.logging.LoggingHandler;

public class T01_ping {

	public void start() throws InterruptedException {
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			Bootstrap bootstrap = new Bootstrap();
			bootstrap.group(workerGroup)
				.channel(NioDatagramChannel.class)
				.handler(new ChannelInitializer<DatagramChannel>() {

					@Override
					protected void initChannel(DatagramChannel ch) throws Exception {
						ch.pipeline()
							.addLast(new LoggingHandler())
							.addLast(new PingHandler());

					}
				});
			ChannelFuture future = bootstrap.bind(1234).sync();
			future.channel().closeFuture().sync();
		} finally {

			workerGroup.shutdownGracefully();
		}
	}
}
