package com.michel.gobots

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MusicRecommendationApplication

fun main(args: Array<String>) {
    runApplication<MusicRecommendationApplication>(*args)
}
