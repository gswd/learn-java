package com.hm707.okhttp.hello;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HelloWorld {
	public static void main(String[] args) throws IOException {
		String getUrl = "https:www.baidu.com";
		getMethod(getUrl);
	}

	private static void getMethod(String url) throws IOException {
		OkHttpClient client = new OkHttpClient();

		Request request = new Request.Builder().url(url).build();

		Response response = client.newCall(request).execute();

		if (response.isSuccessful()) {
			String body = response.body().string();
			System.out.println(body);
		} else {
			throw new IOException("Unexpected code " + response);
		}
	}

	private static void postMethod(String url, String json) throws IOException {
		OkHttpClient client = new OkHttpClient();
		RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);

		Request request = new Request.Builder().url(url).post(body).build();

		Response response = client.newCall(request).execute();

		if (response.isSuccessful()) {
			String responseBody = response.body().string();
			System.out.println(responseBody);
		} else {
			throw new IOException("Unexpected code " + response);
		}

	}

}
