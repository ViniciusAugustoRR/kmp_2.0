package com.example.mp3.logic.viewmodels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TestingVM : ViewModel() {

    var tempo = MutableLiveData("00:00")
    var _tempo = MutableLiveData( "0:00")
    fun setTempo(){
        tempo.value = "55:55"
    }
    fun set_Tempo(){
        _tempo.value = "88:88"
    }
}