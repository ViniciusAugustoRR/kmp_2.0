package com.example.mp3.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mp3.R
import com.example.mp3.data.models.track
import com.example.mp3.view.adapters.TrackAdapter

/**
 * A fragment representing a list of Items.
 */
class ItemFragment : Fragment() {

    private var columnCount = 1

    var DummyTracks: ArrayList<track> =
         arrayListOf(
            track(trackName = "nome 1", albumName = "album 1", artistName = "artista 1", mDirect = "kek"),
            track(trackName = "nome 2", albumName = "album 1", artistName = "artista 1", mDirect = "kek"),
            track(trackName = "nome 3", albumName = "album 1", artistName = "artista 1", mDirect = "kek"),
            track(trackName = "nome 4", albumName = "album 1", artistName = "artista 1", mDirect = "kek"),
            track(trackName = "nome 5", albumName = "album 1", artistName = "artista 1", mDirect = "kek"),
            track(trackName = "nome 6", albumName = "album 1", artistName = "artista 1", mDirect = "kek"),
            track(trackName = "nome 7", albumName = "album 1", artistName = "artista 1", mDirect = "kek"),
            track(trackName = "nome 8", albumName = "album 1", artistName = "artista 1", mDirect = "kek"),
            track(trackName = "nome 9", albumName = "album 1", artistName = "artista 1", mDirect = "kek"),
            track(trackName = "nome 10", albumName = "album 1", artistName = "artista 1", mDirect = "kek"),
            track(trackName = "nome 11", albumName = "album 1", artistName = "artista 1", mDirect = "kek"),
            track(trackName = "nome 12", albumName = "album 1", artistName = "artista 1", mDirect = "kek"),
            track(trackName = "nome 13", albumName = "album 1", artistName = "artista 1", mDirect = "kek"),
            track(trackName = "nome 14", albumName = "album 1", artistName = "artista 1", mDirect = "kek")
         )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_item_list, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var rv = view.findViewById<RecyclerView>(R.id.list)
        val customAdapter = TrackAdapter(DummyTracks)

        rv.adapter = customAdapter
        rv.layoutManager = LinearLayoutManager(context)

    }

}