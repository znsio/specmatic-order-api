package com.store.config

import com.store.config.SecurityConfig.Companion.API_TOKEN
import com.store.filters.DummyAPIKeyAuthFilter
import com.store.security.AuthManager
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.core.annotation.Order
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain

@TestConfiguration
@Order(1)
@EnableWebSecurity
open class TestSecurityConfig {
    @Bean
    open fun testFilterChain(http: HttpSecurity): SecurityFilterChain? {
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
}