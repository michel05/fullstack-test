package com.michel.gobots.resources.spotify.responses

data class TrackResponse(
        val items: List<TrackItem>
)

data class TrackItem(
        val track: TrackItemInfo
)

data class TrackItemInfo(
        val name: String,
        val album: AlbumInfo,
        val artists: List<TrackArtist>
)

data class AlbumInfo(
        val images: List<AlbumImage>
)

data class AlbumImage(
        val url: String
)

data class TrackArtist(
        val name: String
)
