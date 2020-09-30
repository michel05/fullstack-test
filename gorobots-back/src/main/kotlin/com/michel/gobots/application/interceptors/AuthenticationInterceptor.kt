package com.michel.gobots.application.interceptors

import com.michel.gobots.domain.entities.Authentication
import com.michel.gobots.domain.gateways.AuthenticationGateway
import org.springframework.http.HttpRequest
import org.springframework.http.client.ClientHttpRequestExecution
import org.springframework.http.client.ClientHttpRequestInterceptor
import org.springframework.http.client.ClientHttpResponse

class AuthenticationInterceptor(
        private val authenticationGateway: AuthenticationGateway
) : ClientHttpRequestInterceptor {

    private var authToken: Authentication = Authentication()

    override fun intercept(request: HttpRequest, body: ByteArray, execution: ClientHttpRequestExecution): ClientHttpResponse {
        if (!authToken.isValid()) {
            authToken = authenticationGateway.authenticate()
        }
        request.headers.add(AUTHORIZATION_HEAD, "${authToken.tokenType} ${authToken.accessToken}")
        return execution.execute(request, body)
    }

    companion object {
        private const val AUTHORIZATION_HEAD = "Authorization"
    }
}
