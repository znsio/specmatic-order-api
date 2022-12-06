package com.store.controllers

import com.store.config.ApplicationConfig
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.context.annotation.ComponentScan
import org.springframework.http.MediaType
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@WebMvcTest(Products::class)
@AutoConfigureMockMvc(addFilters = false)
@ComponentScan("com.store.config")
class ProductsTest(@Autowired val mockMvc: MockMvc) {

    @Test
    fun shouldNotAcceptBooleanAsName() {
        mockMvc.perform(
            MockMvcRequestBuilders.post("/products").content("""{"name":true,"type":"gadget","inventory":1}""").contentType(
                MediaType.APPLICATION_JSON
            ).header("authenticate", "API-TOKEN-HARI")
        ).andExpect(status().is4xxClientError)
    }
}