package nio.socket.t01;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.concurrent.locks.LockSupport;

public class EchoClient {
	public static void main(String[] args) {
		try (SocketChannel socketChannel = SocketChannel.open()) {

			socketChannel.configureBlocking(false);
			SocketAddress serverAddr = new InetSocketAddress("127.0.0.1", 9999);

			Selector selector = Selector.open();
			socketChannel.connect(serverAddr);
			System.out.println("发送连接请求");

			while (!socketChannel.finishConnect()) {
				System.out.println("正在连接中...");
			}
			socketChannel.register(selector, SelectionKey.OP_WRITE);


			for (int i = 0; i < 2; i++) {

				selector.select();
				Iterator<SelectionKey> keysIt = selector.selectedKeys().iterator();

				while (keysIt.hasNext()) {
					SelectionKey key = keysIt.next();
					SocketChannel sc = (SocketChannel)key.channel();
					if (key.isReadable()) {
						System.out.println("readable ...");

					}

					if (key.isWritable()) {
						System.out.println("writable ...");
						socketChannel.register(selector, SelectionKey.OP_READ);
						readFromChannel(sc);
						sayHello(sc);
					}

					keysIt.remove();

				}

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void sayHello(SocketChannel channel) throws IOException {
		ByteBuffer buffer = ByteBuffer.wrap("hello Server !!!".getBytes());
		channel.write(buffer);
	}

	private static void readFromChannel(SocketChannel channel) throws IOException {
		ByteBuffer buffer = ByteBuffer.allocate(1024);

		channel.read(buffer);
		String result = new String(buffer.array());
		System.out.println(result);

	}
}
