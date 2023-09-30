package com.example.mp3.logic.viewmodels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mp3.data.models.track
import com.example.mp3.data.repository.Repository

class TrackViewModel : ViewModel() {

    private val _mTracks : MutableLiveData<ArrayList<track>> = Repository.getTracks()

    fun getTracks(): LiveData<ArrayList<track>> = _mTracks
}