package com.xtm.config;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

/**
 * gateway限流自定义KeyResolver
 */
@Configuration
public class RateLimiterConfiguration {

    @Bean(value = "ipKeyResolver")
    public KeyResolver ipKeyResolver() {
        //IP限流
        //return exchange -> Mono.just(exchange.getRequest().getRemoteAddress().getHostName());
        //用户限流 请求中需要userId参数
        //return exchange -> Mono.just(exchange.getRequest().getQueryParams().getFirst("userId"));
        //接口限流
        //return exchange -> Mono.just(exchange.getRequest().getPath().value());
        return exchange -> Mono.just(exchange.getRequest().getRemoteAddress().getAddress().getHostAddress());
    }
}