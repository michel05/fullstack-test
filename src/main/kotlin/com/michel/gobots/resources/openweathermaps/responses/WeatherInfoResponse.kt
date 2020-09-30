package com.michel.gobots.resources.openweathermaps.responses

data class WeatherInfoResponse(
        val main: WeatherData
)

data class WeatherData(
        val temp: Double
)
