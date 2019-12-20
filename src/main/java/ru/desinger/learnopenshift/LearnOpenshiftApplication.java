package ru.desinger.learnopenshift;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@RequestMapping("/main")
public class LearnOpenshiftApplication {

	@GetMapping("/hello")
	public String route () {
		return "Hello";
	}

	public static void main(String[] args) {
		SpringApplication.run(LearnOpenshiftApplication.class, args);
	}

}
