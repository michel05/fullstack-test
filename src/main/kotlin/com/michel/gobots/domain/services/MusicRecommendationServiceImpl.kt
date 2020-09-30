package com.michel.gobots.domain.services

import com.michel.gobots.domain.entities.Track
import com.michel.gobots.domain.entities.TrackCategory
import com.michel.gobots.domain.gateways.MusicRecommendationGateway
import com.michel.gobots.domain.gateways.WeatherGateway
import org.springframework.stereotype.Service

@Service
class MusicRecommendationServiceImpl(
        private val weatherGateway: WeatherGateway,
        private val recommendationGateway: MusicRecommendationGateway
) : MusicRecommendationService {

    override fun getRecommendationByCity(city: String): List<Track> {
        val temperature = weatherGateway.getTemperatureInCelcius(city)
        val category = when {
            temperature > 30.0 -> TrackCategory.PARTY
            temperature in 15.0..30.0 -> TrackCategory.POP
            temperature in 10.0..14.0 -> TrackCategory.ROCK
            else -> TrackCategory.CLASSICAL
        }
        return recommendationGateway.getRecommendationByCategory(category.categoryName)
    }
}
