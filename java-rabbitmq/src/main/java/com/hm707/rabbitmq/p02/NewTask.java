package com.hm707.rabbitmq.p02;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

public class NewTask {
	private final static String QUEUE_NAME = "hello";
	private final static String host = "192.168.37.100";

	public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {

		String message = String.join("", args);

		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost(host);
		factory.setUsername("admin");
		factory.setPassword("admin");
		factory.setPort(5672);

		try (Connection connection = factory.newConnection()) {
			Channel channel = connection.createChannel();

			boolean durable = true;
			channel.queueDeclare(QUEUE_NAME, durable, false, false, null);

			//将消息标记为persistent也不能避免message丢失.
			// 1. rabbitMQ接收到msg与保存到磁盘上有一个短暂的间隙
			// 2. 接收到的msg不会立即保存在磁盘中，而是先保存在缓冲区中
			//所以持久性并不强，如果需要更强的持久性，需要使用 publisher confirms(https://www.rabbitmq.com/confirms.html)
			channel.basicPublish("", QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());

			System.out.println(" [x] Sent '\" + message + \"'");
		}
	}
}
