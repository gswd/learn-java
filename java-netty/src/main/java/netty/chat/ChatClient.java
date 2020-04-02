package netty.chat;

import java.util.Scanner;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

public class ChatClient {
	private int port;
	private String host;

	public ChatClient(String host, int port) {

		this.host = host;
		this.port = port;
	}

	private void go() throws InterruptedException {

		EventLoopGroup loopGroup = new NioEventLoopGroup();
		try {
			Bootstrap bootstrap = new Bootstrap();
			bootstrap
				.group(loopGroup)
				.channel(NioSocketChannel.class)
				.handler(new ChatClientChannelInit());

			ChannelFuture future = bootstrap.connect(host, port).sync();
			System.out.println("--- Client " + future.channel().localAddress() + "---");

			Scanner scanner = new Scanner(System.in);
			while (scanner.hasNextLine()) {
				String msg = scanner.nextLine();
				future.channel().writeAndFlush(msg);
			}
		} finally {
			loopGroup.shutdownGracefully();
		}

	}

	public static void main(String[] args) throws InterruptedException {
		ChatClient client = new ChatClient("127.0.0.1", 9999);
		client.go();
	}
}
