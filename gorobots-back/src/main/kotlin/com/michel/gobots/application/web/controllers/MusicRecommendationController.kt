package com.michel.gobots.application.web.controllers

import com.michel.gobots.domain.entities.Track
import com.michel.gobots.domain.services.MusicRecommendationService
import org.springframework.web.bind.annotation.*

@CrossOrigin
@RestController
class MusicRecommendationController(
        val musicRecommendationService: MusicRecommendationService
) {

    companion object {
        private const val RECOMMENDATION_BY_CITY_PATH = "/recommendations"
    }

    @GetMapping(RECOMMENDATION_BY_CITY_PATH)
    @ResponseBody
    fun getMusicRecommendationByCity(@RequestParam("city") city: String): List<Track> {
        return musicRecommendationService.getRecommendationByCity(city)
    }
}