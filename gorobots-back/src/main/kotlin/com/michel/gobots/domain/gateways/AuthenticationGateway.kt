package com.michel.gobots.domain.gateways

import com.michel.gobots.domain.entities.Authentication

interface AuthenticationGateway {

    fun authenticate(): Authentication
}