package com.example.demo.GateWay.Filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

/*
 * 测试pre filter
 */
public class TestPreFilter implements GatewayFilter, Ordered {

	@Override
	public int getOrder() {
		// TODO Auto-generated method stub
		return -200;
	}

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		System.out.println("i am pre filter1, order is : "+ getOrder()+", thread id is :"+Thread.currentThread().getId());
        return chain.filter(exchange);
	}


}
