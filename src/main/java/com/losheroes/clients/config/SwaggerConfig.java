package com.losheroes.clients.config;

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

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    public static final String CLIENT_TAG = "client service";

    @Bean
    public Docket apiDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.losheroes.clients.application.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(getApiInfo())
                ;
    }

    private ApiInfo getApiInfo() {
        return new ApiInfo(
                "Los Heroes",
                "Clients Service API",
                "1.0",
                "https://www.losheroes.cl/wps/wcm/connect/internet/trabajadores/nuestra-empresa",
                new Contact("Luis Contreras", "https://www.losheroes.cl/", "luis.contreras@api-ux.com"),
                "© Los Héroes Todos los Derechos Reservados",
                "https://www.losheroes.cl/",
                Collections.emptyList()
        );
    }
}