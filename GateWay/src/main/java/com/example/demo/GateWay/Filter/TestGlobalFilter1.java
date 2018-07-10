package com.example.demo.GateWay.Filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@Component
public class TestGlobalFilter1 implements GlobalFilter, Ordered  {

	@Override
	public int getOrder() {
		// TODO Auto-generated method stub
		return -200;
	}

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		// TODO Auto-generated method stub
		System.out.println("i am global filter1, order is : "+ getOrder()+", thread id is :"+Thread.currentThread().getId());
		return chain.filter(exchange);
	}

}
