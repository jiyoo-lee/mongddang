package com.jeeyulee.mongddang.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.RequestParameterBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.RequestParameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.*;

@Configuration
@EnableWebMvc
public class SwaggerConfig {

        private ApiInfo swaggerInfo() {
            return new ApiInfoBuilder().title("Drop The Mongddang API")
                    .description("Drop The Mongddang의 서비스 API 모음").build();
        }

        @Bean
        public Docket swaggerApi() {
            RequestParameterBuilder builder = new RequestParameterBuilder();
            builder.name("Authorization")
                    .description("access_token")
                    .required(false)
                    .in("header")
                    .accepts(Collections.singleton(MediaType.APPLICATION_JSON))
                    .build();
            List<RequestParameter> parameters = new ArrayList<>();
            parameters.add(builder.build());

            return new Docket(DocumentationType.SWAGGER_2)
                    .consumes(getConsumeContentTypes())
                    .produces(getProduceContentTypes())
                    .apiInfo(swaggerInfo())
                    .globalRequestParameters(parameters).select()
                    .apis(RequestHandlerSelectors.basePackage("com.jeeyulee.mongddang"))
                    .paths(PathSelectors.any())
                    .build()
                    .useDefaultResponseMessages(false);
        }

        private Set<String> getConsumeContentTypes() {
            Set<String> consumes = new HashSet<>();
            consumes.add("application/json;charset=UTF-8");
            consumes.add("application/x-www-form-urlencoded");
            return consumes;
        }

        private Set<String> getProduceContentTypes() {
            Set<String> produces = new HashSet<>();
            produces.add("application/json;charset=UTF-8");
            return produces;
        }
}
