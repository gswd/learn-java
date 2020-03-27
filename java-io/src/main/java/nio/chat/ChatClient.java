package nio.chat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;

public class ChatClient {
	private int port = 6688;

	private String ip = "127.0.0.1";
	private Selector selector;
	private String name;
	private SocketChannel channel;

	public ChatClient() {
		SocketAddress address = new InetSocketAddress(ip, port);
		try {
			channel = SocketChannel.open(address);
			channel.configureBlocking(false);

			selector = Selector.open();
			channel.register(selector, SelectionKey.OP_READ);

			this.name = "client-" + channel.socket().getPort();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void go() {
		try {
			while (true) {
				int c = selector.select();
				if (c == 0) {
					continue;
				}

				Iterator<SelectionKey> it = selector.selectedKeys().iterator();
				while (it.hasNext()) {
					SelectionKey key = it.next();
					SocketChannel channel = (SocketChannel)key.channel();

					readMsg(channel);

					it.remove();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void readMsg(SocketChannel channel) {

		ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
		try {
			int readCount = channel.read(byteBuffer);
			if (readCount > 0) {
				String msg = new String(byteBuffer.array());
				System.out.println("From Server - " + name + " : " + msg);
			}

		} catch (IOException e) {
			e.printStackTrace();
			try {
				System.out.println("server 离线..");
				channel.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	private void sendMsg(String msg) {

		try {
			channel.write(ByteBuffer.wrap(msg.getBytes()));

		} catch (IOException e) {

			e.printStackTrace();
			System.out.println("发送数据失败");
		}

	}

	public static void main(String[] args) {
		ChatClient client = new ChatClient();

		new Thread(client::go).start();

		while (true){
			Scanner scanner = new Scanner(System.in);
			String msg = scanner.nextLine();
			client.sendMsg(msg);
		}
	}
}
