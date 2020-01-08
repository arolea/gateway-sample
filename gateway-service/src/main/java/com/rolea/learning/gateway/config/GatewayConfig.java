package com.rolea.learning.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

/**
 * Route - an id, associated with a collection of predicates and a collection of filters
 * Predicate - used to match routes, can be chained
 * Filters - used to modify requests and responses, can be cached
 */
@Configuration
public class GatewayConfig {

	@Bean
	public RouteLocator routeLocator(RouteLocatorBuilder builder){
		return builder.routes()
				.route("find_all_students", r -> r
						.path("/students").and()
						.method(HttpMethod.GET)
						.uri("http://localhost:8081/students"))
				.route("create_student", r -> r
						.path("/students").and()
						.method(HttpMethod.POST)
						.uri("http://localhost:8081/students"))
				.route("find_student_by_id", r -> r
						.path("/students/**").and()
						.method(HttpMethod.GET)
						.filters(rw -> rw
								// forward path variable
								.rewritePath("/students/(?<segment>.*)", "/students/${segment}")
								.addRequestHeader("my-header", "my-header-value"))
						.uri("http://localhost:8081/students/"))
				.build();
	}

}
