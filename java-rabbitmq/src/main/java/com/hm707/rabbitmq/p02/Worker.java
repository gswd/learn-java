package com.hm707.rabbitmq.p02;

import java.util.concurrent.TimeUnit;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

public class Worker {
	private final static String QUEUE_NAME = "hello";
	private final static String host = "192.168.37.100";

	public static void main(String[] args) throws Exception {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost(host);
		factory.setUsername("admin");
		factory.setPassword("admin");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();

		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

		DeliverCallback deliverCallback = (consumerTag, delivery) -> {
			String message = new String(delivery.getBody(), "UTF-8");
			System.out.println(" [x] Received '" + message + "'");
			try {
				doWork(message);
			} finally {
				System.out.println(" [x] Done");
			}
		};

		boolean autoAck = true;
		channel.basicConsume(QUEUE_NAME, autoAck, deliverCallback, consumerTag -> { });
	}

	private static void doWork(String task) {

		for (char ch: task.toCharArray()) {
			if (ch == '.') {
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
