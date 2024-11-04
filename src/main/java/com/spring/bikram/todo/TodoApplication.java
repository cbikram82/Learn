package com.spring.bikram.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TodoApplication {

	@Autowired
	public static void main(String[] args) {
		SpringApplication.run(TodoApplication.class, args);
	}

}
