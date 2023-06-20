package com.projetLocMns.ProjetFilRougeLocMnsV3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@EnableJpaAuditing
@SpringBootApplication
public class ProjetFilRougeLocMnsV3Application extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(ProjetFilRougeLocMnsV3Application.class, args);
    }


	//définit le fuseau horaire par défaut de l'application sur UTC (Temps universel coordonné)
	@PostConstruct
	public void init(){
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
	}

    // ici important pour le deployement :
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ProjetFilRougeLocMnsV3Application.class);
    }

    @PostConstruct
    public void init() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }


}
