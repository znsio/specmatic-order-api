package com.store.filters

import com.store.model.DB
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter
import javax.servlet.http.HttpServletRequest

class APIKeyAuthFilter(private val principalRequestHeader: String, DB: DB) :
    AbstractPreAuthenticatedProcessingFilter() {

    override fun setAuthenticationManager(authenticationManager: AuthenticationManager?) {
        super.setAuthenticationManager(authenticationManager)
    }

    override fun getPreAuthenticatedPrincipal(request: HttpServletRequest): Any {
        return if (request.headerNames.toList().contains(principalRequestHeader))
            DB.getUserForToken(request.getHeader(principalRequestHeader))!!
        else
            "N/A"
    }

    override fun getPreAuthenticatedCredentials(request: HttpServletRequest?): Any {
        return "N/A"
    }
}