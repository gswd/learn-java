package javaudp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;


public class Client {
	public static void main(String[] args) throws IOException {
		DatagramSocket ds = new DatagramSocket(9999);
//		ds.setSoTimeout(1000);

		// connect()方法不是真连接，它是为了在客户端的DatagramSocket实例中保存服务器端的IP和端口号，
		// 确保这个DatagramSocket实例只能往指定的地址和端口发送UDP包，不能往其他地址和端口发送。
		// 这么做不是UDP的限制，而是Java内置了安全检查。
//		ds.connect(InetAddress.getByName("localhost"), 6666);

		// 发送:
		byte[] data = "Hello".getBytes();
		DatagramPacket packet = new DatagramPacket(data, data.length);
		byte[] ip = new byte[]{
			(byte)10, (byte)34, (byte)136, (byte)177
		};
		packet.setAddress(InetAddress.getByAddress(ip));
		packet.setPort(6666);
		ds.send(packet);
		System.out.println("client 发送成功 1");

		byte[] ip2 = new byte[] {
			(byte)169, (byte)254, (byte)228, (byte)125
		};
		packet.setAddress(InetAddress.getByAddress(ip2));
		packet.setPort(7777);
		ds.send(packet);
		System.out.println("client 发送成功 2");

		// 接收:
		while (true) {
			byte[] buffer = new byte[1024];
			packet = new DatagramPacket(buffer, buffer.length);
			System.out.println("client 准备接收数据");
			ds.receive(packet);
			String resp = new String(packet.getData(), packet.getOffset(), packet.getLength());
			System.out.println("client receive : " + resp);
			System.out.println("packet.getSocketAddress() : " + packet.getSocketAddress());

			// disconnect()也不是真正地断开连接，它只是清除了客户端DatagramSocket实例记录的远程服务器地址和端口号，
			// 这样，DatagramSocket实例就可以连接另一个服务器端。
			//		ds.disconnect();
		}

	}
}
