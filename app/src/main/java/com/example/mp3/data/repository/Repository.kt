package com.example.mp3.data.repository

import android.media.MediaMetadataRetriever
import android.os.Environment
import androidx.lifecycle.MutableLiveData
import com.example.mp3.data.models.TrackModel
import java.io.File

object Repository {

    val mDirect: ArrayList<String> = readDirectory(Environment.getExternalStorageDirectory())

    var mTrackModels: ArrayList<TrackModel>
    /*var mAlbuns: ArrayList<album>
    var mArtists: ArrayList<artist>*/

    init{
        mTrackModels = createTracks(mDirect)
    }

    fun getTracks() = MutableLiveData(mTrackModels)

    fun createTracks(directs: ArrayList<String>): ArrayList<TrackModel>{

        val newDirects = ArrayList<TrackModel>()
        val mmr = MediaMetadataRetriever()
        var mmrData : ArrayList<String?>

        for (file in directs) {
            mmr.setDataSource(file)

            mmrData = arrayListOf(
                mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE),
                mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ALBUM),
                mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST))

            for(item in mmrData){
                if(item.isNullOrBlank()){
                    mmrData[mmrData.indexOf(item)] = "Desconhecido"
                }
            }

            if (mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION)!!.toLong() < 900000) {
                newDirects.add(
                    TrackModel(
                        file,
                        mmrData[0],
                        mmrData[1],
                        mmrData[2]
                    )
                )
            }

        }

        return ArrayList(newDirects.sortedWith(compareBy {it.trackName}))
    }
    fun readDirectory(root: File): ArrayList<String>{

        val files: ArrayList<String> = ArrayList()

        try {
            for(file in root.listFiles()){
                if(!file.absolutePath.contains("Music")) continue
                if(file.isDirectory){
                    files.addAll(readDirectory(file))
                } else {
                    if(file.name.endsWith(".mp3") || file.name.endsWith(".flac")){
                        files.add(file.toString())
                    }
                }
            }
        } catch (ex: Exception){
            var x: String = ex.message.toString()
        }
        return files
    }

}