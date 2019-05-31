package com.hm707.io.socket.nio.channel.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class ChannelAccept {
	public static final String GREETING = "Hello I must be going.\r\n";

	public static void main(String[] args) throws IOException, InterruptedException {
		int port = 9999;

		ByteBuffer buffer = ByteBuffer.wrap(GREETING.getBytes());

		ServerSocketChannel ssc = ServerSocketChannel.open().bind(new InetSocketAddress(port));
		ssc.configureBlocking(false);

		while (true) {
			System.out.println("waiting for connections");

			SocketChannel sc = ssc.accept();
			if (sc == null) {
				Thread.sleep(2000);
			} else {
				System.out.println("Incoming connection from : " + sc.socket().getRemoteSocketAddress());
				buffer.flip();
				sc.write(buffer);
				sc.close();
			}

		}
	}
}
