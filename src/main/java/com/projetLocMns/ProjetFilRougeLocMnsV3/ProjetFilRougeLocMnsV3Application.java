package com.projetLocMns.ProjetFilRougeLocMnsV3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class ProjetFilRougeLocMnsV3Application {

	public static void main(String[] args) {
		SpringApplication.run(ProjetFilRougeLocMnsV3Application.class, args);
	}

}
