package com.michel.gobots.domain.services


import com.michel.gobots.domain.entities.TrackCategory.*
import com.michel.gobots.domain.gateways.MusicRecommendationGateway
import com.michel.gobots.domain.gateways.WeatherGateway
import io.mockk.every
import io.mockk.mockk
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class MusicRecommendationServiceImplTest {

    private val weatherGateway = mockk<WeatherGateway>()
    private val musicRecommendationGateway = mockk<MusicRecommendationGateway>()
    private val musicRecommendationService = MusicRecommendationServiceImpl(weatherGateway, musicRecommendationGateway)

    @BeforeAll
    fun mockRecommendationTracksByCategory() {
        every { musicRecommendationGateway.getRecommendationByCategory(PARTY.categoryName) } returns TrackFactory.partyTracks
        every { musicRecommendationGateway.getRecommendationByCategory(POP.categoryName) } returns TrackFactory.popTracks
        every { musicRecommendationGateway.getRecommendationByCategory(ROCK.categoryName) } returns TrackFactory.rockTracks
        every { musicRecommendationGateway.getRecommendationByCategory(CLASSICAL.categoryName) } returns TrackFactory.classicalTracks
    }

    @Test
    fun `should return party tracks`() {
        val city = "Goiania"
        every { weatherGateway.getTemperatureInCelcius(city) } returns 40.0
        val tracks = musicRecommendationService.getRecommendationByCity(city)

        assertThat(tracks.size).isEqualTo(TrackFactory.partyTracks.size)
        TrackFactory.partyTracks.forEach {
            assertThat(tracks).contains(it)
        }
    }

    @Test
    fun `should return pop tracks`() {
        val city = "Sao Paulo"
        every { weatherGateway.getTemperatureInCelcius(city) } returns 30.0
        val tracks = musicRecommendationService.getRecommendationByCity(city)

        assertThat(tracks.size).isEqualTo(TrackFactory.popTracks.size)
        TrackFactory.popTracks.forEach {
            assertThat(tracks).contains(it)
        }
    }

    @Test
    fun `should return rock tracks`() {
        val city = "Londres"
        every { weatherGateway.getTemperatureInCelcius(city) } returns 12.0
        val tracks = musicRecommendationService.getRecommendationByCity(city)

        assertThat(tracks.size).isEqualTo(TrackFactory.rockTracks.size)
        TrackFactory.rockTracks.forEach {
            assertThat(tracks).contains(it)
        }
    }

    @Test
    fun `should return classical tracks`() {
        val city = "Alasca"
        every { weatherGateway.getTemperatureInCelcius(city) } returns 5.0
        val tracks = musicRecommendationService.getRecommendationByCity(city)

        assertThat(tracks.size).isEqualTo(TrackFactory.classicalTracks.size)
        TrackFactory.classicalTracks.forEach {
            assertThat(tracks).contains(it)
        }
    }
}
