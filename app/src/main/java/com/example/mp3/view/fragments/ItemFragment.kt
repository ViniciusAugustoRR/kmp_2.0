package com.example.mp3.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mp3.R
import com.example.mp3.data.models.track
import com.example.mp3.data.repository.Repository
import com.example.mp3.view.adapters.TrackAdapter

/**
 * A fragment representing a list of Items.
 */
class ItemFragment(private val frag_Interation : OnClickInteraction? = null): Fragment(){

    var DummyTracks: ArrayList<track> = Repository.getTracks()
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

        val rv = view.findViewById<RecyclerView>(R.id.list)
        val customAdapter = TrackAdapter(DummyTracks,
            TrackAdapter.OnClickListener { track ->
                Toast.makeText(context, "${track.trackName}", Toast.LENGTH_SHORT).show()
                requireActivity().supportFragmentManager
                    .beginTransaction()
                    .add(R.id.main_frame, RunningPlayerFragment())
                    .commit()

            })

        rv.adapter = customAdapter
        rv.layoutManager = LinearLayoutManager(context)
    }



    interface OnClickInteraction {
        fun onItemClicked(track: track, position: Int)
    }



}