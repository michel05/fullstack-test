package com.michel.gobots.resources.openweathermaps.services

import com.michel.gobots.domain.gateways.WeatherGateway
import com.michel.gobots.resources.openweathermaps.responses.WeatherInfoResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.client.RestClientException
import org.springframework.web.client.RestTemplate

@Component
class OpenWeatherMapsGateway(
        val restTemplate: RestTemplate,
        @Value("\${OPEN_WEATHER_MAP_KEY}") val apiKey: String
) : WeatherGateway {

    companion object {
        private const val OPEN_WEATHER_MAP_URL = "https://api.openweathermap.org"
    }

    override fun getTemperatureInCelcius(city: String) =
            restTemplate.getForEntity("$OPEN_WEATHER_MAP_URL/data/2.5/weather?q=$city&appid=$apiKey&units=metric",
                    WeatherInfoResponse::class.java)
                    .let { it.body?.main?.temp ?: throw RestClientException("Error when access Open Weather Map api") }
}