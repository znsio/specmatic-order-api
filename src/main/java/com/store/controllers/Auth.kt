package com.store.controllers

import com.store.model.Creds
import com.store.model.DB
import com.store.model.authToken
import com.store.model.credsAreValid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.client.HttpClientErrorException
import java.lang.Exception

@RestController
class Auth {
    @PostMapping("/auth")
    fun authenticate(@RequestBody creds: Creds): String {
        if(credsAreValid(creds))
            return authToken

        throw Exception("Invalid credentials")
    }
}
