package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.Employee;
import com.example.service.RabbitMQSender;


@RestController
@RequestMapping(value="/example-rabbitmq/")
public class RabbitMQController {

	@Autowired
	RabbitMQSender rabbitMQSender;
	
	@GetMapping(value="/producer")
	public String producer(@RequestParam("empName") String empName, @RequestParam("empId") String empId) {
		
		Employee employee = new Employee();
		employee.setEmpId(empId);
		employee.setEmpName(empName);
		
		rabbitMQSender.send(employee);
		
		return "Message sent to the RabbitMQ Successfully";
	}
}
