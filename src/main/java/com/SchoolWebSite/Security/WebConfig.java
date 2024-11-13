package com.SchoolWebSite.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {

	  @Bean
	    public WebMvcConfigurer corsConfigurer() {
	        return new WebMvcConfigurer() {
	            @Override
	            public void addCorsMappings(CorsRegistry registry) {
	                registry.addMapping("/**") // Autorise tous les chemins de votre API
	                        .allowedOrigins("https://nlschool.vercel.app/") // Autorise les requêtes de cette origine
	                        .allowedMethods("GET", "POST", "PUT", "DELETE") // Méthodes HTTP autorisées
	                        .allowedHeaders("*") // Autorise tous les en-têtes
	                        .allowCredentials(true); // Autorise les cookies/credentials
	            }
	        };
	    }
}

