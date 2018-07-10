package com.example.demo.GateWay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

import com.example.demo.GateWay.Filter.TestGlobalFilter1;
import com.example.demo.GateWay.Filter.TestGlobalFilter2;
import com.example.demo.GateWay.Filter.TestPostFilter;
import com.example.demo.GateWay.Filter.TestPreFilter;
import com.example.demo.GateWay.Filter.TestPreFilter2;

@SpringBootApplication
public class GateWayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GateWayApplication.class, args);
	}
	
	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(r -> r.path("/hhg")
							.filters(f -> f.filter(new TestPreFilter2()).filter(new TestPostFilter()))
							.uri("http://baidu.com/")
				).build();
	}

}
