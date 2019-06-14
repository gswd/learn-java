package com.hm707.io.socket.security;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.net.SocketFactory;
import javax.net.ssl.SSLSocketFactory;

/**
 * 由于服务端的证书是我们自己生成的，没有任何受信任机构的签名，所以客户端是无法验证服务端证书的有效性的，通信必然会失败。
 * 所以需要为客户端创建一个保存所有信任证书的仓库，然后把服务端证书导进这个仓库。
 * 这样，当客户端连接服务端时，会发现服务端的证书在自己的信任列表中，就可以正常通信了。
 *
 * 1. 创建客户端仓库
 *
 * 因为keytool不能仅生成一个空白仓库，所以和服务端一样，我们还是生成一个证书加一个仓库（客户端证书加仓库）
 *
 * keytool -genkeypair -v -alias lh-ssl-client -keyalg RSA -keystore ./client_ks -storepass client -keypass 123123 -validity 36500 -dname "CN=localhost,OU=cn,O=cn,L=cn,ST=cn,C=cn"
 *
 * 2. 从服务端导出证书
 *
 * keytool -exportcert -v -alias lh-ssl-server -keystore ./server_ks -storepass server -file server_key.cer
 *
 * 3. 将证书导入客户端仓库并信任此证书
 *
 * keytool -importcert -trustcacerts -v -alias lh-ssl-server -file ./server_key.cer -keystore ./client_ks -storepass client
 *
 */
public class BioClient01 {

	public static void main(String[] args) throws Exception {
		String clientKS = Thread.currentThread().getContextClassLoader().getResource("client_ks").getPath();

		System.setProperty("javax.net.ssl.trustStore", clientKS);

		System.setProperty("javax.net.debug", "ssl,handshake");

		BioClient01 client = new BioClient01();
		Socket s = client.clientWithoutCert();

		PrintWriter writer = new PrintWriter(s.getOutputStream());
		BufferedReader reader = new BufferedReader(new InputStreamReader(s.getInputStream()));

		writer.println("hello");
		writer.flush();
		System.out.println("===============> " + reader.readLine());
		s.close();
	}

	private Socket clientWithoutCert() throws Exception {
		SocketFactory sf = SSLSocketFactory.getDefault();
		Socket s = sf.createSocket("localhost", 8443);
		return s;
	}
}
