package com.example.mp3.data.repository

import android.media.MediaPlayer
import androidx.lifecycle.MutableLiveData
import com.example.mp3.data.models.TrackModel

object MPlayer {

    private var MdaPlayer = MediaPlayer()
    private var reproductionList = Repository.mTrackModels
    private var position: Int = 0

    fun getMP() = MutableLiveData(MdaPlayer)
    fun getRL() = MutableLiveData(reproductionList)
    fun getPos() = MutableLiveData(position)

    fun setMP(newMp: MediaPlayer){
        MdaPlayer = newMp
    }
    fun setRL(newReprod: ArrayList<TrackModel>){
        reproductionList = newReprod
    }
    fun setPos(newPos: Int){
        position = newPos
    }

    fun _Pause(){
        MdaPlayer.pause()
    }
    fun _Play(){
        MdaPlayer.start()
    }
}