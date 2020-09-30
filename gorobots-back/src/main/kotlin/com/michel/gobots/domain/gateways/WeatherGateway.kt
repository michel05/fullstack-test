package com.michel.gobots.domain.gateways

interface WeatherGateway {
    fun getTemperatureInCelcius(city: String): Double
}
