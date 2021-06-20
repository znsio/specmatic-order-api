package com.store.model

import org.springframework.http.HttpStatus
import org.springframework.web.client.HttpClientErrorException

const val authToken: String = "abc123"

fun credsAreValid(creds: Creds): Boolean {
    return creds.username == "jackie" && creds.password == "PaSsWoRd"
}

fun notValid(token: String): Boolean {
    return token != authToken
}

fun validateAuthToken(token: String) {
    if(notValid(token))
        throw HttpClientErrorException(HttpStatus.UNAUTHORIZED)
}