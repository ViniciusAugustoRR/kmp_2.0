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
import com.example.mp3.view.fragments.detailfragments.AlbumDetailFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AlbumAdapter(
    private val onClickListener: OnClickListener, private val isArtistAlbumList: Boolean
) : RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder>(){

    companion object DIFF_CALLBACK : DiffUtil.ItemCallback<AlbumModel>() {
        override fun areItemsTheSame(oldItem: AlbumModel, newItem: AlbumModel): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: AlbumModel, newItem: AlbumModel): Boolean {
            return oldItem.albumName == newItem.albumName
        }
    }
    private val differ = AsyncListDiffer(this, DIFF_CALLBACK)

    class AlbumViewHolder(
        itemView: View,
        _context: Context
    ) : ViewHolder(itemView) {
        private val title: TextView
        private val subtitle: TextView
        private val cover: ImageView
        private val mmr = MediaMetadataRetriever()
        private val pContext : Context

        init {
            title = itemView.findViewById(R.id.title)
            subtitle = itemView.findViewById(R.id.subtitle)
            cover = itemView.findViewById(R.id.image)
            pContext = _context
        }

        fun bind(item: AlbumModel, position: Int) {
            try {
                CoroutineScope(Dispatchers.Default).launch {
                    mmr.setDataSource(item.tracks[0].mDirect)
                    CoroutineScope(Dispatchers.Main).launch {
                        title.text = item.albumName
                        subtitle.text = item.tracks[0].artistName
                        Glide.with(pContext)
                            .asBitmap()
                            .load(mmr.embeddedPicture)
                            .into(cover)
                    }
                }
            } catch (e : Exception){
                var x = e.message
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        var layout = R.layout.fragment_item
        if(isArtistAlbumList){
            layout = R.layout.fragment_album_item_list}

        val view = LayoutInflater.from(parent.context)
            .inflate(layout, parent, false)

        return AlbumViewHolder(view, parent.context)
    }

    override fun getItemCount() = differ.currentList.size
    fun submitList(list: List<AlbumModel>) {
        differ.submitList(list)
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        try{
            holder.itemView.setOnClickListener{
                onClickListener.clickListener(differ.currentList[position], position)}

            holder.bind(differ.currentList[position], position)
        } catch (e : Exception){
            var x = e.message
        }
    }



    class OnClickListener(val clickListener: (_albumModel: AlbumModel, _position : Int) -> Unit) {
        fun onClick(albumModel: AlbumModel, Position: Int) = clickListener(albumModel, Position)
    }
}