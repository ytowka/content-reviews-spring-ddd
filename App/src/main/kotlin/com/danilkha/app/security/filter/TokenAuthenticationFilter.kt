package com.danilkha.app.security.filter

import com.danilkha.app.model.AccountDto
import com.danilkha.app.security.exception.AuthenticationHeaderException
import com.danilkha.app.security.model.TokenRequest
import com.danilkha.app.security.util.HttpResponseUtil
import com.danilkha.app.security.util.HttpSettingUtil
import com.danilkha.app.service.AuthenticationService
import org.springframework.http.HttpHeaders
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken
import org.springframework.security.web.authentication.preauth.RequestHeaderAuthenticationFilter
import java.io.IOException
import java.util.*
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


class TokenAuthenticationFilter(
    private val authenticationService: AuthenticationService,
    authenticationManager: AuthenticationManager
) :
    RequestHeaderAuthenticationFilter() {

    init {
        setAuthenticationManager(authenticationManager)
    }

    @Throws(IOException::class)
    override fun doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {
        try {
            val token: TokenRequest? = HttpSettingUtil.getTokenFromValidatedAuthorizationHeader(
                (request as HttpServletRequest).getHeader(
                    HttpHeaders.AUTHORIZATION
                )
            )
            if(token != null) {
                try {
                    val account: AccountDto = authenticationService.userInfoByToken(token)
                    SecurityContextHolder.getContext().authentication = PreAuthenticatedAuthenticationToken(account, token)
                }catch (e: Exception) {
                    throw AuthenticationHeaderException(e.message, e)
                }
            }
            chain.doFilter(request, response)
        } catch (exception: AuthenticationHeaderException) {
            exception.printStackTrace()
            HttpResponseUtil.putExceptionInResponse(
                (request as HttpServletRequest), (response as HttpServletResponse),
                exception, HttpServletResponse.SC_UNAUTHORIZED
            )
        }
    }
}