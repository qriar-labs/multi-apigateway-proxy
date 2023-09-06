package com.qriarlabs.qap.multigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Mono;

@SpringBootApplication
public class MultigatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(MultigatewayApplication.class, args);

	}

@Bean
public GlobalFilter customGlobalFilter() {
    return (exchange, chain) -> chain.filter(exchange)
        .then(Mono.just(exchange))
        .map(serverWebExchange -> {
          //adds header to response
          serverWebExchange.getResponse().getHeaders().set("Server", "QAP Multi-Gateway");
          return serverWebExchange;
        })
        .then();

}
}
