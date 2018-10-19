package com.hm707.okhttp.hello;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DownloadVideoTest {
	public static void main(String[] args) throws IOException {
		String videoUrl = "https://static.yximgs.com/s1/videos/www_main-059ce9beee.mp4";
		download(videoUrl);
	}

	public static void download(String url) throws IOException {
		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder().url(url).build();

		Response response = client.newCall(request).execute();


		Files.createDirectories(Paths.get("d:/video"));
		File file = new File("D:/video/test.mp4");
		if (response.isSuccessful()) {
			InputStream is = response.body().byteStream();


			FileOutputStream fos = new FileOutputStream(file);
			BufferedInputStream buffered = new BufferedInputStream(is);
			byte[] bytes = new byte[1024];
			int length;
			while ((length = buffered.read(bytes)) != -1){
				fos.write(bytes, 0, length);
			}

		} else {
			throw new IOException("Unexpected code " + response);
		}
	}
}
