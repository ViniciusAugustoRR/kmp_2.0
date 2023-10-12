package com.example.mp3.logic.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mp3.data.models.AlbumModel
import com.example.mp3.data.models.TrackModel
import com.example.mp3.data.repository.Repository

class AlbumVM {
    private val _mAlbums : MutableLiveData<ArrayList<AlbumModel>> = Repository.getAlbums()
    fun getAlbums(): LiveData<ArrayList<AlbumModel>> = _mAlbums

}