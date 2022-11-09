package com.server.somnium.global.swagger

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.*
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spi.service.contexts.SecurityContext
import springfox.documentation.spring.web.plugins.Docket

@Configuration
class SwaggerConfig {

    @Bean
    fun api(): Docket {
        return Docket(DocumentationType.OAS_30)
                .apiInfo(this.apiInfo())
                .useDefaultResponseMessages(false)
                .securityContexts(listOf(securityContext()))
                .securitySchemes(listOf(apiKey()))
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.server.somnium"))
                .paths(PathSelectors.any())
                .build();
    }

    private fun apiInfo(): ApiInfo? {
        return ApiInfoBuilder()
                .title("somnium")
                .description("management your somnium")
                .version("1.0")
                .build()
    }

    private fun securityContext(): SecurityContext? {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .build()
    }

    private fun defaultAuth(): List<SecurityReference> {
        val authorizationScope = AuthorizationScope("global", "accessEverything")
        val authorizationScopes = arrayOfNulls<AuthorizationScope>(1)
        authorizationScopes[0] = authorizationScope
        return listOf(SecurityReference("Authorization", authorizationScopes))
    }

    private fun apiKey(): ApiKey {
        return ApiKey("Authorization", "Authorization", "header")
    }
}