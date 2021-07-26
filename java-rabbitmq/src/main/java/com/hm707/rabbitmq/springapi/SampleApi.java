package com.hm707.rabbitmq.springapi;

import java.util.Arrays;

import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;

public class SampleApi {
	public static void main(String[] args) throws InterruptedException {
		CachingConnectionFactory cf = new CachingConnectionFactory("192.168.37.102",5672);
		cf.setUsername("admin");
		cf.setPassword("admin");

		RabbitAdmin admin = new RabbitAdmin(cf);

		TopicExchange exchange = new TopicExchange("myExchange");
		admin.declareExchange(exchange);

		Queue queue = new Queue("myQueue");
		admin.declareQueue(queue);

		admin.declareBinding(BindingBuilder.bind(queue).to(exchange).with("foo.*"));

		//设置监听
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(cf);

		container.setMessageListener(message -> System.out.println(" \n[x] Received '" + Arrays.toString(
			message.getBody()) + "'\n"));

		container.setQueueNames("myQueue");
		container.start();

		//发送消息
		RabbitTemplate template = new RabbitTemplate(cf);
		template.convertAndSend("myExchange", "foo.bar", "Hello, world!");
		Thread.sleep(1000);
		container.stop();

	}
}
