package com.example.mp3.view.fragments.detailfragments

import android.media.MediaMetadataRetriever
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.mp3.R
import com.example.mp3.data.models.ArtistModel
import com.example.mp3.databinding.FragmentAlbumDetailBinding
import com.example.mp3.databinding.FragmentArtistDetailBinding
import com.example.mp3.databinding.FragmentRunningPlayerBinding
import com.example.mp3.logic.FragmentInstances.FragmentInstances
import com.example.mp3.logic.viewmodels.ArtistDetailVm
import com.example.mp3.logic.viewmodels.ArtistVM
import com.example.mp3.logic.viewmodels.PlayerVM
import com.example.mp3.view.adapters.AlbumAdapter
import com.example.mp3.view.adapters.ArtistAdapter
import com.example.mp3.view.adapters.TrackAdapter

class ArtistDetailFragment(private val _SelectedArtist: ArtistModel) : Fragment() {
    private lateinit var binding : FragmentArtistDetailBinding
    private val mmr = MediaMetadataRetriever()
    private lateinit var playerVM: PlayerVM
    private lateinit var trackAdapter: TrackAdapter
    private lateinit var albumAdapter: AlbumAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        playerVM = ViewModelProvider(requireActivity())[PlayerVM::class.java]
        binding = FragmentArtistDetailBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //val view = inflater.inflate(R.layout.fragment_artist_detail, container, false)
        val view = binding.root
        try {
            //change track and reprodlist
            trackAdapter = TrackAdapter(TrackAdapter.OnClickListener { track, position ->
                playerVM.setRL(_SelectedArtist.getTracks())
                playerVM.setPos(position)
            })
            albumAdapter = AlbumAdapter(
                AlbumAdapter.OnClickListener { album, position ->
                    parentFragmentManager.beginTransaction()
                        .replace(R.id.main_frame, AlbumDetailFragment(album))
                        .commit()
                    Toast.makeText(requireContext(), album.albumName, Toast.LENGTH_SHORT).show()
                }, true)
        } catch (ex: Exception){
            var x = ex.message
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        try {

            albumAdapter.submitList(_SelectedArtist.albuns)
            binding.artistDetailAlbums.apply {
                layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                adapter = albumAdapter
                setHasFixedSize(true)
            }
            trackAdapter.submitList(_SelectedArtist.getTracks())
            binding.artistDetailTracks.apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = trackAdapter
                setHasFixedSize(true)
            }
            mmr.setDataSource(_SelectedArtist.getTracks()[0].mDirect)
            Glide.with(requireContext())
                .asBitmap()
                .load(mmr.embeddedPicture)
                .into(binding.artistDetailCover)
        } catch (ex: Exception){
            var x = ex.message
        }
    }


}