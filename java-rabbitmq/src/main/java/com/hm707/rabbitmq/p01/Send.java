package com.hm707.rabbitmq.p01;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Send {
	private final static String QUEUE_NAME = "hello";
	private final static String host = "192.168.37.100";

	public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost(host);
		factory.setUsername("admin");
		factory.setPassword("admin");
		factory.setPort(5672);

		try (Connection connection = factory.newConnection()) {
			Channel channel = connection.createChannel();
			channel.queueDeclare(QUEUE_NAME, false, false, false, null);

			for (int i = 0; i < 2000000; i++) {
				//TimeUnit.MILLISECONDS.sleep(1);

				String message = "Hello World! - ["+i+"]";
				channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
			}

			System.out.println(" [x] Sent '\" + message + \"'");
		}
	}
}
