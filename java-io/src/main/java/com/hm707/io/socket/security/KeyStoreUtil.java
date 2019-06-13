package com.hm707.io.socket.security;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.security.KeyStore;

import javax.net.ServerSocketFactory;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocket;

public abstract class KeyStoreUtil {

	public static SSLContext load(String keyStorePath, String password) {

		try {
			System.setProperty("javax.net.ssl.trustStore", keyStorePath);
			System.setProperty("javax.net.debug", "ssl,handshake");

			SSLContext context = SSLContext.getInstance("TLS");
			KeyStore ks = KeyStore.getInstance("jceks");
			ks.load(new FileInputStream(keyStorePath), null);
			KeyManagerFactory kf = KeyManagerFactory.getInstance("SunX509");
			kf.init(ks, password.toCharArray());
			context.init(kf.getKeyManagers(), null, null);


			return context;

		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	public static ServerSocket createSSLServerSocket(SSLContext context, int port) {

		try {
			ServerSocketFactory factory = context.getServerSocketFactory();
			ServerSocket socket = factory.createServerSocket(port);
			//单向认证，即客户端认证服务端,如果需要双向认证可以将值设置为true，并将证书导入到服务端并信任
			((SSLServerSocket) socket).setNeedClientAuth(false);
			return socket;

		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}


	}
}
