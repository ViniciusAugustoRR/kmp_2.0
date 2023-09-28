package com.example.mp3.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.mp3.R
import com.example.mp3.data.models.track
import org.w3c.dom.Text

class TrackAdapter(
    private val trackList: ArrayList<track>
) : RecyclerView.Adapter<TrackAdapter.TrackViewHolder>(){
    class TrackViewHolder(itemView: View)
        : ViewHolder(itemView) {
        val title: TextView
        val subtitle: TextView

        init {
            title = itemView.findViewById(R.id.title)
            subtitle = itemView.findViewById(R.id.subtitle)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_item, parent, false)

        return TrackViewHolder(view)
    }

    override fun getItemCount() = trackList.size

    override fun onBindViewHolder(holder: TrackViewHolder, position: Int) {
        holder.title.text = trackList[position].trackName.toString()
        holder.subtitle.text = trackList[position].albumName.toString()
    }

}