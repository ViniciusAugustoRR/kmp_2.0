package com.example.mp3.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mp3.R
import com.example.mp3.logic.FragmentInstances.FragmentInstances
import com.example.mp3.logic.viewmodels.ArtistDetailVm
import com.example.mp3.logic.viewmodels.ArtistVM
import com.example.mp3.view.adapters.AlbumAdapter
import com.example.mp3.view.adapters.ArtistAdapter
import com.example.mp3.view.fragments.detailfragments.ArtistDetailFragment

class ArtistFragment : Fragment() {
    private lateinit var artistAdapter: ArtistAdapter
    private lateinit var artistVM: ArtistVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_artist, container, false)
        try {
            artistVM = ArtistVM()
            artistAdapter = ArtistAdapter(
                ArtistAdapter.OnClickListener { artist, position ->
                    parentFragmentManager.beginTransaction()
                        .replace(R.id.main_frame, ArtistDetailFragment(artist))
                        .commit()
                    Toast.makeText(requireContext(), artist.artistName, Toast.LENGTH_SHORT).show()
                })
        } catch (e: Exception){
            var x = e.message;
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val rv = view.findViewById<RecyclerView>(R.id.list)

        artistAdapter.submitList(artistVM.getAlbums().value!!)
        rv.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = artistAdapter
            setHasFixedSize(true)
        }
    }

}