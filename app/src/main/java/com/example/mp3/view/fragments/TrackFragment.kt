package com.example.mp3.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mp3.R
import com.example.mp3.data.models.TrackModel
import com.example.mp3.data.repository.Repository
import com.example.mp3.logic.viewmodels.PlayerVM
import com.example.mp3.logic.viewmodels.TrackVM
import com.example.mp3.view.adapters.TrackAdapter

/**
 * A fragment representing a list of Items.
 */
class TrackFragment(private val frag_Interation : OnClickInteraction? = null): Fragment(){
    private lateinit var trackAdapter: TrackAdapter
    private lateinit var trackVM: TrackVM
    private lateinit var playerVM: PlayerVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        playerVM = ViewModelProvider(requireActivity())[PlayerVM::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_item_list, container, false)
        try {
            trackAdapter = TrackAdapter(
                TrackAdapter.OnClickListener { track, position ->
                    playerVM.setRL(trackVM.getTracks().value!!)
                    playerVM.setPos(position)})
            trackVM = ViewModelProvider(this)[TrackVM::class.java]
            trackVM.getTracks().observe(viewLifecycleOwner, Observer {
                trackAdapter.notifyDataSetChanged()})
        } catch (e: Exception){
            var x = e.message;
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val rv = view.findViewById<RecyclerView>(R.id.list)

        trackAdapter.submitList(trackVM.getTracks().value!!)
        rv.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = trackAdapter
            setHasFixedSize(true)
        }
    }

    interface OnClickInteraction {
        fun onItemClicked(TrackModel: TrackModel, position: Int)
    }



}