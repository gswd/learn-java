package com.hm707.io.socket.bio.v03;

import static com.hm707.io.socket.ConsoleColor.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 全双工，启动两个线程，分别用于接收 和 发送 消息
 */
public class Server03 {
	public static int servePort = 9999;

	public Server03() throws IOException {
		ServerSocket serverSocket = new ServerSocket(servePort);

		System.out.println("--> wait connect.");
		Socket socket = serverSocket.accept();

		System.out.println("--> connected!");

		//启动两个线程分别用于 接收 和 发送 消息.
		Thread t1 = new Thread(new ServerSender(socket));
		Thread t2 = new Thread(new ServerReceiver(socket));
		t1.start();
		t2.start();

		//serverSocket.close();
	}

	public static void main(String[] args) throws IOException {
		Server03 s = new Server03();
	}

	class ServerSender implements Runnable{
		Socket s;
		public ServerSender(Socket s) {
			this.s = s;
		}

		@Override
		public void run() {
			System.out.println("server sender [start]");
			while (true) {
				try {
					//发送消息到客户端
					System.out.println("==> send msg to client : ");
					BufferedReader reader2 = new BufferedReader(new InputStreamReader(System.in));
					PrintWriter pw = new PrintWriter(s.getOutputStream(),true);
					pw.println(reader2.readLine());
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		}
	}

	class ServerReceiver implements Runnable{
		Socket s;
		public ServerReceiver(Socket s) {
			this.s = s;
		}

		@Override
		public void run() {
			System.out.println("server receiver [start]");
			while (true) {

				try {
					//从客户端接收消息
					InputStream is = s.getInputStream();
					BufferedReader reader = new BufferedReader(new InputStreamReader(is));
					String line = reader.readLine();
					//PrintWriter pw = new PrintWriter(System.out);
					//pw.
					System.out.println("server accept : " + ANSI_RED + line + ANSI_RESET);

					//客户端发起关闭
					if ("bye".equals(line)) {
						System.out.println("--> close server socket.");
						s.close();
						System.exit(0);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
	}
}
