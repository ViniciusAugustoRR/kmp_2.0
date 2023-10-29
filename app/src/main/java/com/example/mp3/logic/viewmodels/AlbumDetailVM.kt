package com.example.mp3.logic.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mp3.data.models.AlbumModel
import androidx.lifecycle.ViewModel

class AlbumDetailVM : ViewModel() {
    private var _mSelectedAlbum : MutableLiveData<AlbumModel> = MutableLiveData()

    fun setSelectedAlbum(newAlbumShow : AlbumModel){ _mSelectedAlbum.value = newAlbumShow }
    fun getSelectedAlbum(): LiveData<AlbumModel> = _mSelectedAlbum
}