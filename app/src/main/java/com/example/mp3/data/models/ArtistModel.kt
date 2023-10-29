package com.example.mp3.data.models

class ArtistModel (
    val artistName: String?,
    val albuns: ArrayList<AlbumModel>
){

    fun getTracks(): ArrayList<TrackModel>{
        val tracks = ArrayList<TrackModel>()
        for(album in albuns){
            tracks.addAll(album.tracks)}
        tracks.sortedWith(compareBy{it.trackName})
        return tracks
    }

}