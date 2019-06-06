package com.hm707.io.socket.nio.channel.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;

public class ConnectAsync {
	public static void main(String[] args) throws IOException {
		String host = "www.baidu.com";
		int port = 80;

		SocketChannel sc = SocketChannel.open();
		sc.configureBlocking(false);
		sc.connect(new InetSocketAddress(host, port));


		while (!sc.finishConnect()) {//执行finishConnect()后 isConnected() 才会被更新
			System.out.println("not connect yet.");
		}

		System.out.println("connect success");

	}
}
