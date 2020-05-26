package dht;

import java.net.InetSocketAddress;

public class UdpMainTest {


	public static void main(String[] args) throws InterruptedException {
		T01_ping ping = new T01_ping();
		ping.start();
	}

}
