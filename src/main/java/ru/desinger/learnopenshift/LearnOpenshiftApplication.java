package ru.desinger.learnopenshift;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@SpringBootApplication
@RestController
@RequestMapping("/main")
public class LearnOpenshiftApplication {

    @GetMapping("/hello")
    public Mono<String> route () {
        return Mono.just("Hello" + "GET");
    }

    @PostMapping("/hello-post")
    public Mono<String> route1 () {
        return Mono.just("Hello " + " POST");
    }

    public static void main(String[] args) {
        SpringApplication.run(LearnOpenshiftApplication.class, args);
    }

}
