package com.hm707.okhttp.hello;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HelloWorld {

	private static final OkHttpClient client = new OkHttpClient();

	public static void main(String[] args) throws IOException {
		String getUrl = "https:www.baidu.com";
		//getMethod(getUrl);

		//syncGet();
		//asyncGet();
		accessHeaders();
	}

	private static void getMethod(String url) throws IOException {

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

	private static void syncGet() throws IOException {
		Request request = new Request.Builder().url("http://publicobject.com/helloworld.txt").build();

		Response response = client.newCall(request).execute();
		if (!response.isSuccessful()) {
			throw new IOException("Unexpected code " + response);
		}

		Headers responseHeaders = response.headers();

		for (String n : responseHeaders.names()) {
			System.out.println(n + ":" + responseHeaders.get(n));
		}

		System.out.println(response.body().string());

	}

	private static void asyncGet() throws IOException {
		Request request = new Request.Builder().url("http://publicobject.com/helloworld.txt").build();

		client.newCall(request).enqueue(new Callback() {

			@Override
			public void onFailure(Call call, IOException e) {
				e.printStackTrace();
			}

			@Override
			public void onResponse(Call call, Response response) throws IOException {
				if (!response.isSuccessful()) {
					throw new IOException("Unexpected code " + response);
				}

				Headers responseHeaders = response.headers();

				for (String n : responseHeaders.names()) {
					System.out.println(n + ":" + responseHeaders.get(n));
				}

				System.out.println(response.body().string());
			}
		});

	}

	private static void accessHeaders() throws IOException{
		Request request = new Request.Builder()
				.url("https://api.github.com/repos/square/okhttp/issues")
				.header("User-Agent", "OkHttp Headers.java")
				.addHeader("Accept", "application/json; q=0.5")
				.addHeader("Accept", "application/vnd.github.v3+json")
				.build();

		Response response = client.newCall(request).execute();
		if (!response.isSuccessful()) {
			throw new IOException("Unexpected code " + response);
		}

		System.out.println("Server: " + response.header("Server"));
		System.out.println("Date: " + response.header("Date"));
		System.out.println("Vary: " + response.headers("Vary"));
	}

}
