package com.michel.gobots.resources.spotify.services

import com.michel.gobots.domain.entities.Authentication
import com.michel.gobots.domain.gateways.AuthenticationGateway
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap
import org.springframework.web.client.RestClientException
import org.springframework.web.client.RestTemplate
import java.util.*

@Component
class SpotifyAuthInterception(
        private val restTemplate: RestTemplate,
        @Value("\${SPOTIFY_CLIENT_KEY}") val clientKey: String,
        @Value("\${SPOTIFY_SECRET_KEY}") val secretKey: String
) : AuthenticationGateway {

    companion object {
        const val AUTH_URL = "https://accounts.spotify.com/api/token"
    }

    override fun authenticate(): Authentication {
        val headers = HttpHeaders()
        val auth = Base64.getEncoder().encodeToString("$clientKey:$secretKey".toByteArray())
        headers.add("Content-type", MediaType.APPLICATION_FORM_URLENCODED_VALUE)
        headers.add("Authorization", "Basic $auth")

        val map: MultiValueMap<String, String> = LinkedMultiValueMap()
        map.add("grant_type", "client_credentials")
        val entity = HttpEntity(map, headers)

        return restTemplate.postForEntity(AUTH_URL, entity, Authentication::class.java)
                .let { it.body ?: throw RestClientException("Error when access Spotify auth api") }
    }
}
