package com.store.config

import com.fasterxml.jackson.databind.MapperFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.module.SimpleModule
import com.store.model.StrictStringDeserializer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary

@Configuration
open class ApplicationConfig {
    @Bean
    @Primary
    open fun objectMapper(): ObjectMapper {
        val objectMapper = ObjectMapper()
        val strictModule = SimpleModule()
        strictModule.addDeserializer(String::class.java, StrictStringDeserializer())
        objectMapper.registerModule(strictModule)
        return objectMapper
    }
}
