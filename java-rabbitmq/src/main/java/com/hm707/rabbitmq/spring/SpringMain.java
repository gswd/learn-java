package com.hm707.rabbitmq.spring;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringMain {
    public static void main(final String... args) throws Exception {

        AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("application.xml");
        RabbitTemplate template = ctx.getBean(RabbitTemplate.class);
        String[] beanNamesForType = ctx.getBeanNamesForType(RabbitTemplate.class);
        System.out.println(beanNamesForType);
        template.convertAndSend("Hello, world!");
        Thread.sleep(1000);
        ctx.destroy();
    }
}