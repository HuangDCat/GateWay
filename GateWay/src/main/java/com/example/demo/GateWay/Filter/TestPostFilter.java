package com.example.demo.GateWay.Filter;

import java.io.ByteArrayOutputStream;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.core.io.buffer.NettyDataBuffer;
import org.springframework.core.io.buffer.NettyDataBufferFactory;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.reactive.function.BodyExtractors;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.ipc.netty.http.client.HttpClientResponse;

/*
 * 测试post filter
 */
public class TestPostFilter implements GatewayFilter, Ordered {

	private static final String TEST_VAR = "testVar";
	
	@Override
	public int getOrder() {
		// TODO Auto-generated method stub
		return Ordered.LOWEST_PRECEDENCE;
	}

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		exchange.getAttributes().put(TEST_VAR, "hhgtestvar");
		System.out.println("before, i am post filter, order is : "+ getOrder()+", thread id is :"+Thread.currentThread().getId());
        return chain.filter(exchange).then(
                Mono.fromRunnable(() -> {
                    String testVar = exchange.getAttribute(TEST_VAR);
                    System.out.println("after, i am post filter, order is : "+ getOrder()+", thread id is :"+Thread.currentThread().getId()+", and the testvar is : "+testVar);
                    ServerHttpResponse response = exchange.getResponse();
                    HttpClientResponse clientResponse = exchange.getAttribute(ServerWebExchangeUtils.CLIENT_RESPONSE_ATTR);
                    NettyDataBufferFactory factory = (NettyDataBufferFactory) response.bufferFactory();
                    /*Flux<NettyDataBuffer> body = clientResponse.receive()
                    		.retain()
                    		.map(factory::wrap);*/
                    
                    /*
                     * 如何从clientResponse获取接口的原始返回
                     * 例如：原始接口返回的响应为：{“code”:200,”data”:”success”} 的字符串
                     */
                })
        );
	}


}
