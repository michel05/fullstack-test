package com.michel.gobots.domain.gateways

import com.michel.gobots.domain.entities.Track

interface MusicRecommendationGateway {

    fun getRecommendationByCategory(category: String): List<Track>
}