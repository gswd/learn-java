package com.hm707.io.socket.bio.v02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class Client02 {

	public Client02() throws IOException {

		Socket socket = new Socket("127.0.0.1", Server02.servePort);

		while (true){
			//向服务端发送消息
			PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));
			printWriter.println(br1.readLine());

			//接收消息
			InputStreamReader ir2 = new InputStreamReader(socket.getInputStream());
			BufferedReader bufferedReader = new BufferedReader(ir2);

			String line = bufferedReader.readLine();
			if ("bye".equals(line)) {
				System.out.println("--> close client socket.");
				break;
			}
			System.out.println("client accept : " + line);
		}


		socket.close();
	}

	public static void main(String[] args) throws IOException {
		Client02 c = new Client02();
	}
}
