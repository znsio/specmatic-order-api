package com.store.config

import com.store.filters.APIKeyAuthFilter
import com.store.filters.DummyAPIKeyAuthFilter
import com.store.model.DB
import com.store.security.AuthManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.core.annotation.Order
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain

@Configuration
@Order(1)
@EnableWebSecurity
open class SecurityConfig {
    @Bean
    @Profile("default")
    open fun filterChain(http: HttpSecurity): SecurityFilterChain? {
        val filter = APIKeyAuthFilter(API_TOKEN, DB)
        filter.setAuthenticationManager(AuthManager())
        http.csrf().disable().sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
            .addFilter(filter)
            .authorizeRequests()
            .anyRequest()
            .authenticated()
        return http.build()
    }

    @Bean
    @Profile("specmatic-contract-test")
    open fun dummyFilterChain(http: HttpSecurity): SecurityFilterChain? {
        val filter = DummyAPIKeyAuthFilter(API_TOKEN)
        filter.setAuthenticationManager(AuthManager())
        http.csrf().disable().sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
            .addFilter(filter)
            .authorizeRequests()
            .anyRequest()
            .authenticated()
        return http.build()
    }

    companion object {
        const val API_TOKEN = "authenticate"
    }
}