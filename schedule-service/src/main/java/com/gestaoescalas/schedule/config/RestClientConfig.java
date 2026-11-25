package com.gestaoescalas.schedule.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

/**
 * Configura os clientes HTTP utilizados pelo schedule-service
 * para comunicação com outros microsserviços.
 */
@Configuration
public class RestClientConfig {

    @Value("${app.services.auth.base-url}")
    private String authServiceBaseUrl;

    /**
     * Disponibiliza um cliente HTTP configurado para comunicação
     * com o auth-service.
     *
     * @return cliente HTTP configurado com a URL-base do auth-service.
     */
    @Bean
    public RestClient authServiceRestClient(RestClient.Builder builder){

        return builder
                .baseUrl(authServiceBaseUrl)
                .build();
    }
}
