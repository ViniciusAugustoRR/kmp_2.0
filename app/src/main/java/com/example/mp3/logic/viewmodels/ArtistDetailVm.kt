package com.example.mp3.logic.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mp3.data.models.ArtistModel
import androidx.lifecycle.ViewModel

class ArtistDetailVm : ViewModel() {

    private val _mSelectedArtist : MutableLiveData<ArtistModel> = MutableLiveData()

    fun setSelectedArtist(newArtistShow : ArtistModel){ _mSelectedArtist.value = newArtistShow}
    fun getSelectedArtist(): LiveData<ArtistModel> = _mSelectedArtist

}