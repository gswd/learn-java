package nio.chat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class ChatServer {

	private int port = 6688;
	private ServerSocketChannel ssc;
	private Selector selector;

	public ChatServer() {
		try {
			ssc = ServerSocketChannel.open();
			ssc.configureBlocking(false);
			ssc.bind(new InetSocketAddress(port));

			selector = Selector.open();
			ssc.register(selector, SelectionKey.OP_ACCEPT);

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

				Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
				while (iterator.hasNext()) {
					SelectionKey key = iterator.next();

					if (key.isAcceptable()) {
						SocketChannel clientSocket = ssc.accept();
						clientSocket.configureBlocking(false);
						clientSocket.register(selector, SelectionKey.OP_READ);

						System.out.println(clientSocket.getRemoteAddress() + "上线");
					}

					if (key.isReadable()) {
						forwardMsg(key);
					}

					iterator.remove();
				}

			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void forwardMsg(SelectionKey key) throws IOException {
		SocketChannel clientChannel = (SocketChannel)key.channel();
		ByteBuffer msg = readMsg(clientChannel);

		sendMsgToOtherChannel(msg, clientChannel);

	}

	private void sendMsgToOtherChannel(ByteBuffer msg, SocketChannel self) throws IOException {
		if (msg == null) {
			return;
		}

		Iterator<SelectionKey> allClients = selector.keys().iterator();
		while (allClients.hasNext()) {
			SelectableChannel channel = allClients.next().channel();
			if (!(channel instanceof SocketChannel)) {
				continue;
			}
			SocketChannel otherChannel = (SocketChannel)channel;
			if (otherChannel != self) {
				msg.flip();
				otherChannel.write(msg);
			}
		}
	}

	private ByteBuffer readMsg(SocketChannel channel) {

		ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
		try {
			int readCount = channel.read(byteBuffer);
			if (readCount > 0) {
				String msg = new String(byteBuffer.array());
				System.out.println("From Client : " + msg);
			}

			return byteBuffer;
		} catch (IOException e) {
			e.printStackTrace();
			try {
				System.out.println(channel.getRemoteAddress() + " 离线..");
				channel.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

		return null;

	}

	public static void main(String[] args) {

		ChatServer server = new ChatServer();
		server.go();

	}

}
