package com.example.service;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.domain.Employee;

@Service
public class RabbitMQSender {

	@Autowired
	AmqpTemplate template;
	
	@Value("${example.rabbitmq.exchange}")
	private String exchange;
	
	@Value("${example.rabbitmq.routingKey}")
	private String routingKey;
	
	public void send(Employee employee) {
			template.convertAndSend(exchange, routingKey, employee);
			System.out.println("send message "+ employee);
	}

}
