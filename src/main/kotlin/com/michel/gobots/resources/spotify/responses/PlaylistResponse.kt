package com.michel.gobots.resources.spotify.responses

data class PlaylistResponse(
        val playlists: PlaylistData
)

data class PlaylistData(
        val items: List<PlaylistItem>
)

data class PlaylistItem(
        val id: String
)
