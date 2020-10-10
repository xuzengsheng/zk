package com.nccs;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;


/**
 * @program: nssc-parent
 * @author: xuzengsheng
 * @create: 2020-10-10 10:11
 * @description:
 **/
@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients //开启feign客户端
@EnableHystrix  //开启熔断降级
public class GatewayServerApp {

    public static void main(String[] args) {
        SpringApplication.run(GatewayServerApp.class, args);
    }

    /**
     * 创建用户唯一标识：使用IP作为用户唯一标识，根据IP进行限流操作
     *
     * @return
     */
    @Bean(name = "iPKeyResolver")
    public KeyResolver userKeyResolver() {
        return new KeyResolver() {
            @Override
            public Mono<String> resolve(ServerWebExchange exchange) {
                // 获得用户iP
                String ip = exchange.getRequest().getRemoteAddress().getHostString();
                return Mono.just(ip);
            }
        };
    }

}
