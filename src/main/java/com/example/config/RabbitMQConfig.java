package com.example.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;


import org.springframework.amqp.rabbit.connection.ConnectionFactory;

@Configuration
public class RabbitMQConfig {

	@Value("${example.rabbitmq.queue}")
	String queueName;
	
	@Value("${example.rabbitmq.username}")
	private String username;
	
	@Value("${example.rabbitmq.password}")
	private String password;
	
	@Value("${example.rabbitmq.exchange}")
	String exchange;
	
	@Value("${example.rabbitmq.routingKey}")
	private String routingKey;
	
	@Bean
	Queue queue() {
		return new Queue(queueName,false);
	}
	
	@Bean
	DirectExchange exchange() {
		return new DirectExchange(exchange);
	}
	
	@Bean
	Binding binding(Queue queue , DirectExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with(routingKey);
	}
	
	@Bean
	public MessageConverter jsonMessageConverter() {
		return new Jackson2JsonMessageConverter();
	}
	
	@Bean
	@Primary
	public AmqpTemplate rabbitMQTemplate(ConnectionFactory factory) {
		final RabbitTemplate rabbitMQTemplate = new RabbitTemplate(factory);
		rabbitMQTemplate.setMessageConverter(jsonMessageConverter());
		return rabbitMQTemplate;
		
	}
	
	
}
