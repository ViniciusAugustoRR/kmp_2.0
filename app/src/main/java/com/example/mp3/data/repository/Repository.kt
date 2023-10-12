package com.example.mp3.data.repository

import android.media.MediaMetadataRetriever
import android.os.Environment
import androidx.lifecycle.MutableLiveData
import com.example.mp3.data.models.AlbumModel
import com.example.mp3.data.models.ArtistModel
import com.example.mp3.data.models.TrackModel
import java.io.File

object Repository {

    val mDirect: ArrayList<String> = readDirectory(Environment.getExternalStorageDirectory())

    var mTrackModels: ArrayList<TrackModel>
    var mAlbumModels: ArrayList<AlbumModel>
    var mArtists: ArrayList<ArtistModel>

    init{
        mTrackModels = createTracks(mDirect)
        mAlbumModels = createAlbums()
        mArtists = createArtists()
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
            for(file in root.listFiles()!!){
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

    fun createAlbums(): ArrayList<AlbumModel>{

        val albums = ArrayList<AlbumModel>()
        var tracks = ArrayList<TrackModel>()
        var album_nm : String? = "_default"

        var orderedList = mTrackModels.sortedWith(compareBy { it.albumName })
        for(track in orderedList){
            if(album_nm == "_default"){
                album_nm = track.albumName
            }
            if(album_nm!! == track.albumName){
                tracks.add(track)
            } else {
                albums.add(AlbumModel(
                    album_nm,
                    tracks
                ))
                album_nm = track.albumName
                tracks = ArrayList()
                tracks.add(track)
            }
        }
        albums.add(AlbumModel(
            album_nm,
            tracks
        ))
        return albums
    }
    fun getAlbums() = MutableLiveData(mAlbumModels)


    fun createArtists(): ArrayList<ArtistModel>{

        val artistas = ArrayList<ArtistModel>()
        var albums = ArrayList<AlbumModel>()
        var artista_nm : String? = "_default"

        for(album in mAlbumModels.sortedWith(compareBy { it.tracks[0].artistName })){
            if(artista_nm == "_default"){
                artista_nm = album.tracks[0].artistName
            }
            if(artista_nm!! == album.tracks[0].artistName){
                albums.add(album)
            } else {
                artistas.add(ArtistModel(
                    artista_nm,
                    albums))
                artista_nm = album.tracks[0].artistName
                albums = ArrayList()
                albums.add(album)
            }
        }
        artistas.add(ArtistModel(
            artista_nm,
            albums))
        return artistas
    }

    fun getArtists() = MutableLiveData(mArtists)

}