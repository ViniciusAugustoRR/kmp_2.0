package com.example.mp3.view.fragments.detailfragments

import android.media.MediaMetadataRetriever
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mp3.R
import com.example.mp3.databinding.FragmentAlbumDetailBinding
import com.example.mp3.databinding.FragmentArtistDetailBinding
import com.example.mp3.logic.viewmodels.PlayerVM

class AlbumDetailFragment : Fragment() {
    private lateinit var binding : FragmentAlbumDetailBinding
    private val mmr = MediaMetadataRetriever()
    lateinit var playerVM: PlayerVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_album_detail, container, false)
        return view
    }

}