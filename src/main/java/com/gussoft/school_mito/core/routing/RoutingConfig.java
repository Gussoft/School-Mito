package com.gussoft.school_mito.core.routing;

import com.gussoft.school_mito.core.handler.RegisterHandler;
import com.gussoft.school_mito.core.handler.StudentHandler;
import com.gussoft.school_mito.core.handler.CourseHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RequestPredicates.DELETE;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RoutingConfig {

    @Bean
    public RouterFunction<ServerResponse> studentRoutes(StudentHandler handler) {
        return route(GET("/v2/students"), handler::findAll)
                .andRoute(GET("/v2/students/{id}"), handler::findById)
                .andRoute(POST("/v2/students"), handler::save)
                .andRoute(PUT("/v2/students/{id}"), handler::update)
                .andRoute(DELETE("/v2/students/{id}"), handler::delete);
    }

    @Bean
    public RouterFunction<ServerResponse> courseRoutes(CourseHandler handler) {
        return route(GET("/v2/courses"), handler::findAll)
                .andRoute(GET("/v2/courses/{id}"), handler::findById)
                .andRoute(POST("/v2/courses"), handler::save)
                .andRoute(PUT("/v2/courses/{id}"), handler::update)
                .andRoute(DELETE("/v2/courses/{id}"), handler::delete);
    }

    @Bean
    public RouterFunction<ServerResponse> registerRoutes(RegisterHandler handler) {
        return route(GET("/v2/registers"), handler::findAll)
                .andRoute(GET("/v2/registers/{id}"), handler::findById)
                .andRoute(POST("/v2/registers"), handler::save)
                .andRoute(PUT("/v2/registers/{id}"), handler::update)
                .andRoute(DELETE("/v2/registers/{id}"), handler::delete);
    }
}
