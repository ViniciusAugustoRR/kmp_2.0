package com.example.mp3.data.repository

import androidx.lifecycle.MutableLiveData
import com.example.mp3.data.models.track

object Repository {

    val mDirect: ArrayList<String> = arrayListOf("Get", "data", "here")

    var mTracks: ArrayList<track>
    /*var mAlbuns: ArrayList<album>
    var mArtists: ArrayList<artist>*/

    init{
        mTracks = createTracks()
    }

    fun getTracks() = MutableLiveData(mTracks)
    fun createTracks(): ArrayList<track>{
        val DummyTracks: ArrayList<track> =
            arrayListOf(
                track(trackName = "nome 1", albumName = "album 1", artistName = "artista 1", mDirect = "kek"),
                track(trackName = "nome 2", albumName = "album 1", artistName = "artista 1", mDirect = "kek"),
                track(trackName = "nome 3", albumName = "album 1", artistName = "artista 1", mDirect = "kek"),
                track(trackName = "nome 4", albumName = "album 1", artistName = "artista 1", mDirect = "kek"),
                track(trackName = "nome 5", albumName = "album 1", artistName = "artista 1", mDirect = "kek"),
                track(trackName = "nome 6", albumName = "album 1", artistName = "artista 1", mDirect = "kek"),
                track(trackName = "nome 7", albumName = "album 1", artistName = "artista 1", mDirect = "kek"),
                track(trackName = "nome 8", albumName = "album 1", artistName = "artista 1", mDirect = "kek"),
                track(trackName = "nome 9", albumName = "album 1", artistName = "artista 1", mDirect = "kek"),
                track(trackName = "nome 10", albumName = "album 1", artistName = "artista 1", mDirect = "kek"),
                track(trackName = "nome 11", albumName = "album 1", artistName = "artista 1", mDirect = "kek"),
                track(trackName = "nome 12", albumName = "album 1", artistName = "artista 1", mDirect = "kek"),
                track(trackName = "nome 13", albumName = "album 1", artistName = "artista 1", mDirect = "kek"),
                track(trackName = "nome 14", albumName = "album 1", artistName = "artista 1", mDirect = "kek")
            )

        return DummyTracks
    }



}