package com.hm707.io.socket.bio.v01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client01 {

	public Client01() throws IOException {

		Socket socket = new Socket("127.0.0.1", Server01.servePort);

		PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
		printWriter.println("Hello Server01");

		InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

		String line = bufferedReader.readLine();
		System.out.println("client accept : " + line);
		String line2 = bufferedReader.readLine();
		System.out.println("client accept2 : " + line2);

		socket.close();
	}

	public static void main(String[] args) throws IOException {
		Client01 c = new Client01();
	}
}
