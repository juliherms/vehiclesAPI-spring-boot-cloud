package com.github.juliherms.vehiclesapi.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

/**
 * This class responsible to document resources
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Value("${spring.api.version}")
    private String version;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Vehicles REST API",
                "This API serves as an endpoint to track vehicle inventory.",
                version,
                "http://www.udacity.com/tos",
                new Contact("Juliherms Vasconcelos", "www.udacity.com", "j.a.vasconcelos321@gmail.com"),
                "License of API", "http://www.udacity.com/license", Collections.emptyList());
    }
}
