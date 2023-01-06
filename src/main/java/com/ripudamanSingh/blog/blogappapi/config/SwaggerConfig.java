package com.ripudamanSingh.blog.blogappapi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
public class SwaggerConfig {

    public static final String AUTHORIZATION_HEADER="Authorization";

    private ApiKey apiKeys(){
        return new ApiKey("JWT", AUTHORIZATION_HEADER,"header");
    }

    private List<SecurityContext> securityContext(){
        return Arrays.asList(SecurityContext.builder().securityReferences(sf()).build());
    }

    private List<SecurityReference> sf(){
        AuthorizationScope scope = new AuthorizationScope("global","accessEveryThing");

        return Arrays.asList(new SecurityReference("JWT",new AuthorizationScope[]{scope}));
    }

    @Bean
    public Docket api(){

        return new Docket(DocumentationType.SWAGGER_2).apiInfo(getInfo()).securityContexts(securityContext()).securitySchemes(Arrays.asList(apiKeys())).select().apis(RequestHandlerSelectors.any()).paths(PathSelectors.any()).build();
    }

    private ApiInfo getInfo(){
        return new ApiInfo("Blogging Application : Backend","This project is Developed By RIPUDAMAN SINGH","1.0","Terms Of Service",new Contact("Ripudaman Singh","https://www.linkedin.com/in/ripudaman-singh-259957155","ripud60@gmail.com"),"License Of APIS","APIS License URL", Collections.emptyList());
    };
}
