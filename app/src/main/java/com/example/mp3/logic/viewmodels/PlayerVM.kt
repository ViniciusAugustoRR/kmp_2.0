package com.example.mp3.logic.viewmodels
import android.media.MediaPlayer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap


import com.example.mp3.data.models.TrackModel
import com.example.mp3.data.repository.MPlayer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Default
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class PlayerVM : ViewModel() {

    private var _mReprodList: MutableLiveData<ArrayList<TrackModel>> = MPlayer.getRL()
    private var _mPosition: MutableLiveData<Int> = MPlayer.getPos()

    fun getMP() = MPlayer.getMP()
    fun getPosition() = _mPosition
    fun getReprodList() = _mReprodList

    fun setMP(newMp : MediaPlayer){
        MPlayer.setMP(newMp)

    }
    fun setRL(newRL : ArrayList<TrackModel>){
        if(newRL != _mReprodList.value!!) {
            _mReprodList.value = newRL
            MPlayer.setRL(newRL)
        }
    }
    fun setPos(newPos: Int){
        if(newPos != _mPosition.value!!) {
            _mPosition.value = newPos
            MPlayer.setPos(newPos)
        }
    }

    var isPlaying = MutableLiveData(false)
    var isDefaultTrack = MutableLiveData(true)
    var _Track: LiveData<TrackModel> =
        _mPosition.switchMap {
            MutableLiveData(_mReprodList.value!![_mPosition.value!!])
        }

    var _tempoTotal = MutableLiveData("00:00")
    var _tempoDecor = MutableLiveData( "0:00")
    var _progressBar = MutableLiveData(0)

    fun setTempoTotal(){
        _tempoTotal.value = setTimeLabel(MPlayer.getMP().value!!.duration)
    }
    fun setTempoDecorrido(progress: Int){
        _tempoDecor.value = setTimeLabel(progress)
    }

    // =====================================================================================================

    fun setPlusPosition(){
        if(_mPosition.value!! < _mReprodList.value!!.size){
            _mPosition.value = _mPosition.value!! + 1
        }
        else {
            println("----------------- > Limit_Reached ++")
        }

    }
    fun setSubPosition(){
        if(_mPosition.value!! > 0 && 0 < _mReprodList.value!!.size){
            _mPosition.value = _mPosition.value!! - 1
        }
        else {
            println("----------------- > Limit_Reached --")
        }

    }

    suspend fun updateTiming(){
        CoroutineScope(Default).launch {
            while(isPlaying.value!!) {
                delay(500)

                CoroutineScope(Main).launch {
                    _tempoDecor.value = setTimeLabel(MPlayer.getMP().value!!.currentPosition)
                    _progressBar.value = MPlayer.getMP().value!!.currentPosition
                }
            }
        }
    }
    private fun setTimeLabel(time: Int?): String {

        val minutos = time!! / 1000 / 60
        val segundos = time / 1000 % 60

        var label = "$minutos:"

        if (segundos < 10) label += "0"
        label += segundos

        return label
    }


    fun _Pause(){
        MPlayer._Pause()
        isPlaying.value = false

    }
    fun _Play(){
        MPlayer._Play()
        isPlaying.value = true

        CoroutineScope(Default).launch {
            updateTiming()
        }
    }

}