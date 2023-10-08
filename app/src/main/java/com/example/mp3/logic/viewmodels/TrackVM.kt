package com.example.mp3.logic.viewmodels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mp3.data.models.TrackModel
import com.example.mp3.data.repository.Repository

class TrackVM : ViewModel() {

    private val _mTracks : MutableLiveData<ArrayList<TrackModel>> = Repository.getTracks()
    fun getTracks(): LiveData<ArrayList<TrackModel>> = _mTracks

}