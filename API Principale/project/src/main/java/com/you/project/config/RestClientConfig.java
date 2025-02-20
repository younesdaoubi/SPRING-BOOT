package com.you.project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestClientConfig {
    // La méthode annotée avec @Bean signifie que cette méthode produit un bean à gérer par le conteneur Spring.
    // Ici, elle crée et configure une instance de RestTemplate.
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
