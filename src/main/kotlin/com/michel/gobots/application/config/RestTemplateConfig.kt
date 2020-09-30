package com.michel.gobots.application.config

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.michel.gobots.application.errorHandler.RestTemplateResponseErrorHandler
import com.michel.gobots.application.interceptors.AuthenticationInterceptor
import com.michel.gobots.domain.gateways.AuthenticationGateway
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Lazy
import org.springframework.context.annotation.Primary
import org.springframework.http.converter.FormHttpMessageConverter
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter

@Configuration
class RestTemplateConfig(@Lazy val authenticationGateway: AuthenticationGateway) {

    @Bean
    @Primary
    fun restTemplate() =
            RestTemplateBuilder()
                    .messageConverters(jacksonHttpMessageConverter())
                    .additionalMessageConverters(FormHttpMessageConverter())
                    .errorHandler(RestTemplateResponseErrorHandler())
                    .build()

    @Bean
    @Qualifier("restTemplateSpotify")
    fun restTemplateSpotify() =
            RestTemplateBuilder()
                    .messageConverters(jacksonHttpMessageConverter())
                    .additionalMessageConverters(FormHttpMessageConverter())
                    .errorHandler(RestTemplateResponseErrorHandler())
                    .interceptors(AuthenticationInterceptor(authenticationGateway))
                    .build()

    private fun jacksonHttpMessageConverter() =
            MappingJackson2HttpMessageConverter(
                    jacksonObjectMapper()
                            .setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE)
                            .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES))
}
