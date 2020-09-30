package com.michel.gobots.domain.services

import com.michel.gobots.domain.entities.Track

object TrackFactory {

    val partyTracks = listOf(
            Track("Artist 01", "Music 01", "image 01"))

    val popTracks = listOf(
            Track("Artist 02", "Music 02", "image 02"),
            Track("Artist 03", "Music 03", "image 03"))

    val rockTracks = listOf(
            Track("Artist 04", "Music 04", "image 04"),
            Track("Artist 05", "Music 05", "image 05"),
            Track("Artist 06", "Music 06", "image 06"))

    val classicalTracks = listOf(
            Track("Artist 07", "Music 07", "image 07"),
            Track("Artist 08", "Music 08", "image 08"),
            Track("Artist 09", "Music 09", "image 09"),
            Track("Artist 10", "Music 10", "image 10"))
}