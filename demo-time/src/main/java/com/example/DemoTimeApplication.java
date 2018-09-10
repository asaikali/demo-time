package com.example;

import java.util.Date;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DemoTimeApplication {

	@RequestMapping("/")
	public String home()
	{
		return "Version 7: " + new Date();
	}
	
    public static void main(String[] args) {
        SpringApplication.run(DemoTimeApplication.class, args);
    }
}
