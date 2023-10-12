package com.example.mp3.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.mp3.R
import com.example.mp3.data.models.AlbumModel
import com.example.mp3.logic.FragmentInstances.FragmentInstances
import com.example.mp3.logic.viewmodels.AlbumVM
import com.example.mp3.view.adapters.AlbumAdapter
import com.example.mp3.view.adapters.TrackAdapter


class AlbumFragment : Fragment() {
    private lateinit var albumListAdapter: AlbumAdapter
    private lateinit var albumVM: AlbumVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_album, container, false)
        try {
            albumVM = AlbumVM()
            albumListAdapter = AlbumAdapter(
                AlbumAdapter.OnClickListener { album, position ->
                    /*parentFragmentManager.beginTransaction()
                        .replace(R.id.main_frame, FragmentInstances.listFrags[1])
                        .commit()*/
                    Toast.makeText(requireContext(), album.albumName, Toast.LENGTH_SHORT).show()
                })
        } catch (e: Exception){
            var x = e.message;
        }
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val rv = view.findViewById<RecyclerView>(R.id.list)

        albumListAdapter.submitList(albumVM.getAlbums().value!!)
        rv.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = albumListAdapter
            setHasFixedSize(true)
        }
    }



}
