package com.hm707.io.socket.nio.channel.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class SelectSockets02 {

	public static int PORT = 9999;

	public static void main(String[] args) throws IOException {
		new SelectSockets02().go();
	}

	@SuppressWarnings("Duplicates")
	private void go() throws IOException {
		ServerSocketChannel serverSocketChannel = ServerSocketChannel.open().bind(new InetSocketAddress(PORT));
		serverSocketChannel.configureBlocking(false);

		Selector selector = Selector.open();
		serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

		while (true) {
			//.select() 是一个阻塞方法，当检测到通道就绪后才会返回就绪通道的数量.
			int n = selector.select();
			//虽然select()方法是阻塞的，但也有可能被其他线程打断，或者调用selector.wakeup()，而使select()方法立即返回.
			if (n == 0) {
				continue;
			}

			for (Iterator<SelectionKey> it = selector.selectedKeys().iterator(); it.hasNext(); ) {
				SelectionKey selectionKey = it.next();
				if (selectionKey.isAcceptable()) {
					ServerSocketChannel ssc = (ServerSocketChannel)selectionKey.channel();
					SocketChannel socketChannel = ssc.accept();

					registerChannel(selector, socketChannel, SelectionKey.OP_READ);
					sayHello(socketChannel);
				}

				if (selectionKey.isReadable()) {
					readDataFromSocket(selectionKey);
				}
				it.remove();
			}
		}

	}

	@SuppressWarnings("Duplicates")
	private void readDataFromSocket(SelectionKey selectionKey) throws IOException {
		SocketChannel socketChannel = (SocketChannel)selectionKey.channel();
		ByteBuffer buffer = ByteBuffer.allocateDirect(1024);

		while (true) {
			int count = socketChannel.read(buffer);
			if (count > 0) {
				buffer.flip();
				while (buffer.hasRemaining()) {

					socketChannel.write(buffer);

				}

				buffer.clear();
			} else if (count == 0) {
				return;
			} else {
				socketChannel.close();
			}

		}

	}

	private void sayHello(SocketChannel sc) throws IOException {
		ByteBuffer buffer = ByteBuffer.wrap("hello i am SelectSockets02 !!!\n".getBytes());
		sc.write(buffer);
	}

	private void registerChannel(Selector selector, SocketChannel channel, int ops) throws IOException {
		channel.configureBlocking(false);
		channel.register(selector, ops);
	}
}
