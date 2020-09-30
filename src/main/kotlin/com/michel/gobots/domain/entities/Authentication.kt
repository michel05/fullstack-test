package com.michel.gobots.domain.entities

import java.time.LocalDateTime

data class Authentication(
        val accessToken: String = "",
        val tokenType: String = "",
        val scope: String = "",
        val expiresIn: Long = 0,
        val created: LocalDateTime = LocalDateTime.now()
) {
    fun isValid() = accessToken.isNotBlank() && LocalDateTime.now().isBefore(created.plusSeconds(expiresIn))
}

