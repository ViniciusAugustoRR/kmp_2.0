package com.example.mp3.logic.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mp3.data.models.AlbumModel
import com.example.mp3.data.models.ArtistModel
import com.example.mp3.data.repository.Repository

class ArtistVM {
    private val _mArtists : MutableLiveData<ArrayList<ArtistModel>> = Repository.getArtists()
    fun getAlbums(): LiveData<ArrayList<ArtistModel>> = _mArtists

}