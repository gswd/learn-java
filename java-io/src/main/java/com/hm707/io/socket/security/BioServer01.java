package com.hm707.io.socket.security;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javax.net.ssl.SSLContext;

/**
 * keytool -genkeypair -v -alias lh-ssl-server -keyalg RSA -keystore ./server_ks -storepass server -keypass 123123 -validity 36500 -dname "CN=localhost,OU=cn,O=cn,L=cn,ST=cn,C=cn"
 */
public class BioServer01 {
	public static void main(String[] args) throws IOException {
		String keyStorePath = Thread.currentThread().getContextClassLoader().getResource("server_ks").getPath();
		SSLContext context = KeyStoreUtil.load(keyStorePath, "123123");
		ServerSocket ss = KeyStoreUtil.createSSLServerSocket(context, 8443);

		Socket socket = ss.accept();

		BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		PrintWriter writer = new PrintWriter(socket.getOutputStream());

		String data = reader.readLine();
		writer.println(data);

		writer.close();
		socket.close();
	}
}
