package com.store.security

import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.core.Authentication

class AuthManager: AuthenticationManager {
    override fun authenticate(authentication: Authentication?): Authentication {
        authentication!!.isAuthenticated = true
        return authentication
    }
}