package com.example.demo.GateWay.Filter;

import java.net.URI;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.core.Ordered;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.util.UriComponentsBuilder;

import reactor.core.publisher.Mono;

/*
 * 在pre filter中重新设置url
 */
public class TestPreFilter2 implements GatewayFilter, Ordered {

	@Override
	public int getOrder() {
		// TODO Auto-generated method stub
		return -200;
	}

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		System.out.println("i am pre filter2, order is : "+ getOrder()+", thread id is :"+Thread.currentThread().getId());
		
		Route route = exchange.getAttribute(ServerWebExchangeUtils.GATEWAY_ROUTE_ATTR);
		URI uri = UriComponentsBuilder.fromHttpUrl("http://esb.hna.net/api").build().toUri();
		Route newRoute = Route.builder().id(route.getId()).order(route.getOrder()).
									 uri(uri).predicate(route.getPredicate()).filters(route.getFilters())
									 .build();
		exchange.getAttributes().put(ServerWebExchangeUtils.GATEWAY_ROUTE_ATTR, newRoute);
		
		return chain.filter(exchange);
	}


}
