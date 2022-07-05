package com.example.jboss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class SpringJbosseapApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringJbosseapApplication.class, args);
	}

	@EventListener(classes = { ApplicationReadyEvent.class })
	public void appReady() {

		// Log amount cpu and memory is set
		System.out.println("Runtime.getRuntime().availableProcessors() = " +
				Runtime.getRuntime().availableProcessors());
		System.out.println("Runtime.getRuntime().maxMemory() = " + Runtime.getRuntime().maxMemory());
	}
}
