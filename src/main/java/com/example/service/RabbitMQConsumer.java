package com.example.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.example.domain.Employee;

@Component
public class RabbitMQConsumer {

	@RabbitListener(queues = "${example.rabbitmq.queue}")
	public void receivedMessage(Employee employee) {
		System.out.println("Received message from RabbitMQ " + employee);
	}
}
