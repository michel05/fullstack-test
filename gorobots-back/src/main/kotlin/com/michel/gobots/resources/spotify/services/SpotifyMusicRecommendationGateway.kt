package com.michel.gobots.resources.spotify.services

import com.michel.gobots.domain.entities.Track
import com.michel.gobots.domain.gateways.MusicRecommendationGateway
import com.michel.gobots.resources.spotify.responses.PlaylistResponse
import com.michel.gobots.resources.spotify.responses.TrackItem
import com.michel.gobots.resources.spotify.responses.TrackResponse
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component
import org.springframework.web.client.RestClientException
import org.springframework.web.client.RestTemplate

@Component
class SpotifyMusicRecommendationGateway(
        @Qualifier("restTemplateSpotify") private val restTemplate: RestTemplate
) : MusicRecommendationGateway {

    companion object {
        private const val SPOTIFY_URL = "https://api.spotify.com/v1"
        private const val PLAYLIST_LIMIT = 1
    }

    override fun getRecommendationByCategory(category: String) =
            restTemplate.getForEntity("$SPOTIFY_URL/browse/categories/$category/playlists?country=BR&limit=$PLAYLIST_LIMIT",
                    PlaylistResponse::class.java)
                    .let {
                        it.body?.playlists?.items ?: throw RestClientException("Error when access Spotify api")
                    }
                    .flatMap { playlistItem ->
                        restTemplate.getForEntity("$SPOTIFY_URL/playlists/${playlistItem.id}/tracks", TrackResponse::class.java)
                                .let { it.body ?: throw RestClientException("Error when access Spotify api") }
                                .let { trackResponse -> trackResponse.items.map { mapToTrack(it) } }
                    }


    private fun mapToTrack(it: TrackItem) =
            it.run { Track(track.artists.first().name, track.name, track.album.images.first().url) }
}
