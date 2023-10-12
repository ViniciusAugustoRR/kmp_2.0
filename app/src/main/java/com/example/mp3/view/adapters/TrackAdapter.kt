package com.example.mp3.view.adapters

import android.content.Context
import android.media.MediaMetadataRetriever
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.mp3.R
import com.example.mp3.data.models.AlbumModel
import com.example.mp3.data.models.TrackModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Default
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch

class TrackAdapter(
    private val onClickListener: OnClickListener
) : RecyclerView.Adapter<TrackAdapter.TrackViewHolder>(){


    companion object DIFF_CALLBACK : DiffUtil.ItemCallback<TrackModel>() {
        override fun areItemsTheSame(oldItem: TrackModel, newItem: TrackModel): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: TrackModel, newItem: TrackModel): Boolean {
            return oldItem.mDirect == newItem.mDirect
        }
    }
    private val differ = AsyncListDiffer(this, DIFF_CALLBACK)


    class TrackViewHolder(
    itemView: View,
    _context: Context)
    : ViewHolder(itemView) {
    val title: TextView
    val subtitle: TextView
    val cover: ImageView
    val mmr = MediaMetadataRetriever()
    val pContext : Context

    init {
        title = itemView.findViewById(R.id.title)
        subtitle = itemView.findViewById(R.id.subtitle)
        cover = itemView.findViewById(R.id.image)
        pContext = _context
    }

    fun bind(item: TrackModel, position: Int) {

        CoroutineScope(Default).launch{
            mmr.setDataSource(item.mDirect)
            CoroutineScope(Main).launch {
                title.text = item.trackName
                subtitle.text = item.albumName
                Glide.with(pContext)
                    .asBitmap()
                    .load(mmr.embeddedPicture)
                    .into(cover)

            }
        }
    }

}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_item, parent, false)
        return TrackViewHolder(view, parent.context)
    }

    override fun getItemCount() = differ.currentList.size

    fun submitList(list: List<TrackModel>) {
        differ.submitList(list)
    }

    override fun onBindViewHolder(holder: TrackViewHolder, position: Int) {
        holder.itemView.setOnClickListener{
            onClickListener.clickListener(differ.currentList[position], position)}

        holder.bind(differ.currentList[position], position)
    }

    class OnClickListener(val clickListener: (_trackModel: TrackModel, _position : Int) -> Unit) {
        fun onClick(TrackModel: TrackModel, Position: Int) = clickListener(TrackModel, Position)
    }

}



