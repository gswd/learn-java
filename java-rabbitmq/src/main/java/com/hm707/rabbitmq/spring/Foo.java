package com.hm707.rabbitmq.spring;

import org.springframework.stereotype.Component;

@Component
public class Foo {
	public void listen(String message) {
		System.out.println(" [x] Received '" + message + "'");
	}
}
