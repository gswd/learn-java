package nio.socket.t01;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class EchoServer {
	public static void main(String[] args) {
		try (ServerSocketChannel ssc = ServerSocketChannel.open()) {
			ssc.configureBlocking(false);
			ssc.bind(new InetSocketAddress(9999));

			Selector selector = Selector.open();
			ssc.register(selector, SelectionKey.OP_ACCEPT);

			while (true) {
				int optCount = selector.select();
				if (optCount == 0) {
					continue;
				}

				Set<SelectionKey> selectionKeys = selector.selectedKeys();
				Iterator<SelectionKey> it = selectionKeys.iterator();
				while (it.hasNext()) {
					SelectionKey key = it.next();

					if (key.isAcceptable()) {
						SocketChannel clientChannel = ssc.accept();
						clientChannel.configureBlocking(false);
						clientChannel.register(selector, SelectionKey.OP_READ);

						System.out.println(
							"Accepted connection from " + clientChannel);
					}

					if (key.isReadable()) {

						SocketChannel channel = (SocketChannel)key.channel();

						readFromChannel(channel);

						System.out.println(channel);
						System.out.println("=========" + key.isWritable());
					}

					it.remove();

				}

			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void readFromChannel(SocketChannel channel) throws IOException {
		ByteBuffer buffer = ByteBuffer.allocate(1024);

		int count = channel.read(buffer);
		if (count == -1) {
			channel.close();
			return;
		}
		String result = new String(buffer.array());
		System.out.println(result);

		buffer.flip();
		channel.write(buffer);

//		int c;
//		while ((c = channel.read(buffer)) != -1){
//
//
//		}


	}
}
