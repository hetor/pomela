package com.pomela.amqp.rabbitmq.spring_rabbit;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by hetor on 16/3/30.
 */
public class Main {
    public static void main(String[] args) {
//        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-applicationContext.xml", Main.class);
        ClassPathXmlApplicationContext context1 = new ClassPathXmlApplicationContext("com/pomela/amqp/rabbitmq/spring_rabbit/spring-applicationContext.xml");
    }
}
