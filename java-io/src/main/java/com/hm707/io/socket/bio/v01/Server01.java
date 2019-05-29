package com.hm707.io.socket.bio.v01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 只能一次通话
 */
public class Server01 {
	public static int servePort = 9999;

	public Server01() throws IOException {
		ServerSocket serverSocket = new ServerSocket(servePort);

		System.out.println("--> wait connect.");
		Socket socket = serverSocket.accept();

		System.out.println("--> connected!");

		InputStream is = socket.getInputStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));

		String line = reader.readLine();
		System.out.println("server accept : " + line);

		socket.close();
		serverSocket.close();

	}

	public static void main(String[] args) throws IOException {
		Server01 s = new Server01();
	}
}
