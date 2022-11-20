package com.store.filters

import com.store.model.User
import javax.servlet.http.HttpServletRequest
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

class DummyAPIKeyAuthFilter(private val principalRequestHeader: String) :
    AbstractPreAuthenticatedProcessingFilter() {
    override fun getPreAuthenticatedPrincipal(request: HttpServletRequest): Any {
        return User("Dummy User")
    }

    override fun getPreAuthenticatedCredentials(request: HttpServletRequest?): Any {
        return "N/A"
    }
}