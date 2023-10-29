package com.example.mp3.view.fragments.detailfragments

import android.media.MediaMetadataRetriever
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.mp3.R
import com.example.mp3.data.models.AlbumModel
import com.example.mp3.data.repository.Repository.getTracks
import com.example.mp3.databinding.ActivityMainBinding
import com.example.mp3.databinding.FragmentAlbumBinding
import com.example.mp3.databinding.FragmentAlbumDetailBinding
import com.example.mp3.databinding.FragmentRunningPlayerBinding
import com.example.mp3.logic.FragmentInstances.FragmentInstances
import com.example.mp3.logic.viewmodels.AlbumDetailVM
import com.example.mp3.logic.viewmodels.AlbumVM
import com.example.mp3.logic.viewmodels.PlayerVM
import com.example.mp3.logic.viewmodels.TrackVM
import com.example.mp3.view.adapters.TrackAdapter


class AlbumDetailFragment(private val _SelectedAlbum: AlbumModel) : Fragment() {
    private lateinit var binding : FragmentAlbumDetailBinding
    private val mmr = MediaMetadataRetriever()
    private lateinit var trackAdapter: TrackAdapter
    private lateinit var playerVM: PlayerVM
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        playerVM = ViewModelProvider(requireActivity())[PlayerVM::class.java]
        binding = FragmentAlbumDetailBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = binding.root
        try {
            //change track and reprodlist
            trackAdapter = TrackAdapter(TrackAdapter.OnClickListener { album, position ->
                playerVM.setRL(_SelectedAlbum.tracks)
                playerVM.setPos(position)
            })
        } catch (ex: Exception){
            var x = ex.message
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setRV()
    }


    fun setRV(){
        trackAdapter.submitList(_SelectedAlbum.tracks)
        binding.albumDetailList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = trackAdapter
            setHasFixedSize(true)
        }
        mmr.setDataSource(_SelectedAlbum.tracks[0].mDirect)
        Glide.with(requireContext())
            .asBitmap()
            .load(mmr.embeddedPicture)
            .into(binding.albumDetailCover)
    }

}