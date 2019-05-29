package com.hm707.io.socket.bio.v03;

import static com.hm707.io.socket.ConsoleColor.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client03 {

	public Client03() throws IOException {

		Socket socket = new Socket("127.0.0.1", Server03.servePort);

		//启动两个线程分别用于 接收 和 发送 消息.
		Thread t1 = new Thread(new ClientSender(socket));
		Thread t2 = new Thread(new ClientReceiver(socket));
		t1.start();
		t2.start();

	}

	class ClientSender implements Runnable {
		Socket s;

		public ClientSender(Socket s) {
			this.s = s;
		}

		@Override
		public void run() {
			System.out.println("client sender [start]");
			while (true) {
				try {
					//发送消息到服务端
					System.out.println("==> send msg to server : ");
					BufferedReader reader2 = new BufferedReader(new InputStreamReader(System.in));
					PrintWriter pw = new PrintWriter(s.getOutputStream(), true);
					pw.println(reader2.readLine());
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		}
	}

	class ClientReceiver implements Runnable {
		Socket s;

		public ClientReceiver(Socket s) {
			this.s = s;
		}

		@Override
		public void run() {
			System.out.println("client receiver [start]");
			while (true) {

				try {
					//从服务端接收消息
					InputStream is = s.getInputStream();
					BufferedReader reader = new BufferedReader(new InputStreamReader(is));
					String line = reader.readLine();
					System.out.println("client accept : " + ANSI_YELLOW + line + ANSI_RESET);

					//客户端发起关闭
					if ("bye".equals(line)) {
						System.out.println("--> close client socket.");
						s.close();
						System.exit(0);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	public static void main(String[] args) throws IOException {
		Client03 c = new Client03();
	}
}
