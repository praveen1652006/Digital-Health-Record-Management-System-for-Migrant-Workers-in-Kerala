package com.api.apigateway.Validation;

import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.ObjectInputFilter;

@Component
public class JwtValidationFilterFactory extends AbstractGatewayFilterFactory<Object> {

    private final WebClient webClient;

    public JwtValidationFilterFactory(WebClient.Builder webClient,
                                      @Value("${auth.service.url}") String authServiceUrl){
        this.webClient= webClient
                .baseUrl(authServiceUrl)
                .build();
    }

    @Override
    public GatewayFilter apply(Object config) {
        return (exchange, chain)->{
            String token= exchange.getRequest()
                    .getHeaders()
                    .getFirst(HttpHeaders.AUTHORIZATION);

            if(token==null || !token.startsWith("Bearer ")){
                exchange.getResponse().setRawStatusCode(HttpStatus.UNAUTHORIZED.value());
                return exchange.getResponse().setComplete();
            }

            return webClient.get()
                    .uri("/validate")
                    .header(HttpHeaders.AUTHORIZATION, token)
                    .retrieve()
                    .toBodilessEntity()
                    .then(chain.filter(exchange));
        };
    }
}
