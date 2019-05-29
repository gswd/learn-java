package com.hm707.io.socket.bio.v02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
/**
 * 半双工，轮流发送消息
 */
public class Server02 {
	public static int servePort = 9999;

	public Server02() throws IOException {
		ServerSocket serverSocket = new ServerSocket(servePort);

		System.out.println("--> wait connect.");
		Socket socket = serverSocket.accept();

		System.out.println("--> connected!");

		while (true) {

			//从客户端接收消息
			InputStream is = socket.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));
			String line = reader.readLine();
			System.out.println("server accept : " + line);

			//客户端发起关闭
			if ("bye".equals(line)) {
				System.out.println("--> close server socket.");
				break;
			}

			//发送消息到客户端
			System.out.println("==> send msg to client : ");
			BufferedReader reader2 = new BufferedReader(new InputStreamReader(System.in));
			PrintWriter pw = new PrintWriter(socket.getOutputStream(),true);
			pw.println(reader2.readLine());

		}

		socket.close();
		serverSocket.close();

	}

	public static void main(String[] args) throws IOException {
		Server02 s = new Server02();
	}
}
