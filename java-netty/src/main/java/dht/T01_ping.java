package dht;

import java.net.InetAddress;
import java.net.InetSocketAddress;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.DatagramChannel;
import io.netty.channel.socket.DatagramPacket;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.CharsetUtil;

public class T01_ping {

	public void start() throws InterruptedException {
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			Bootstrap bootstrap = new Bootstrap();
			bootstrap.group(workerGroup)
				.channel(NioDatagramChannel.class)
				.option(ChannelOption.SO_BROADCAST, true)
				.handler(new ChannelInitializer<DatagramChannel>() {

					@Override
					protected void initChannel(DatagramChannel ch) throws Exception {
						ch.pipeline()
							.addLast(new LoggingHandler())
							.addLast(new PingHandler());

					}
				});

			ChannelFuture future = bootstrap.bind(1234).sync();

			String msg = "d1:ad2:id20:ad1bc97bb68c6e3d1a036:target20:ad1bc97bb68c6e3d1a03e1:q9:find_node1:t4:12341:y1:qe";
			ByteBuf dataBuf = Unpooled.copiedBuffer(msg, CharsetUtil.UTF_8);

			DatagramPacket datagramPacket = new DatagramPacket(dataBuf, new InetSocketAddress("127.0.0.1", 6881));

			future.channel().writeAndFlush(datagramPacket);
//			if (!future.channel().closeFuture().await(50000)) {
//				System.err.println("request timed out.");
//			}
		} finally {

			workerGroup.shutdownGracefully();
		}
	}
}
