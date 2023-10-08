package com.example.mp3.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mp3.R
import com.example.mp3.data.models.TrackModel

class itemFragmentAdapter(
    private val trackModelList: ArrayList<TrackModel>
) : RecyclerView.Adapter<itemFragmentAdapter.TrackRecyclerVH>() {

    class TrackRecyclerVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView
        val subtitle: TextView

        init {
            title = itemView.findViewById(R.id.title)
            subtitle = itemView.findViewById(R.id.subtitle)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TrackRecyclerVH {

        val view= LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_item, parent, false)

        return TrackRecyclerVH(view)
    }

    override fun onBindViewHolder(holder: TrackRecyclerVH, position: Int) {
        val currentItem = trackModelList[position]
        holder.title.text = currentItem.trackName.toString()
        holder.subtitle.text = currentItem.albumName.toString()
    }

    override fun getItemCount(): Int {
        return trackModelList.count()
    }

}