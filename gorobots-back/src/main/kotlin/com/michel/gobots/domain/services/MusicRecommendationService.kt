package com.michel.gobots.domain.services

import com.michel.gobots.domain.entities.Track

interface MusicRecommendationService {

    fun getRecommendationByCity(city: String) : List<Track>
}