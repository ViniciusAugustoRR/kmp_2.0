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
import com.example.mp3.data.models.ArtistModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ArtistAdapter (private val onClickListener: ArtistAdapter.OnClickListener
) : RecyclerView.Adapter<ArtistAdapter.ArtistViewHolder>()
{

    companion object DIFF_CALLBACK : DiffUtil.ItemCallback<ArtistModel>() {
        override fun areItemsTheSame(oldItem: ArtistModel, newItem: ArtistModel): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: ArtistModel, newItem: ArtistModel): Boolean {
            return oldItem.artistName == newItem.artistName
        }
    }
    private val differ = AsyncListDiffer(this, DIFF_CALLBACK)

    class ArtistViewHolder
        (itemView: View,
        _context: Context
    ) : ViewHolder(itemView) {
        private val title: TextView
        private val cover: ImageView
        private val mmr = MediaMetadataRetriever()
        private val pContext : Context

        init {
            title = itemView.findViewById(R.id.title)
            cover = itemView.findViewById(R.id.image)
            pContext = _context
        }

        fun bind(item: ArtistModel, position: Int) {
            CoroutineScope(Dispatchers.Default).launch{
                mmr.setDataSource(item.albuns[0].tracks[0].mDirect)
                CoroutineScope(Dispatchers.Main).launch {
                    title.text = item.artistName
                    Glide.with(pContext)
                        .asBitmap()
                        .load(mmr.embeddedPicture)
                        .into(cover)
                }
            }
        }

    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_item, parent, false)

        return ArtistViewHolder(view, parent.context)
    }
    override fun getItemCount() = differ.currentList.size
    fun submitList(list: List<ArtistModel>) {
        differ.submitList(list)
    }
    override fun onBindViewHolder(holder: ArtistViewHolder, position: Int) {
        holder.itemView.setOnClickListener{
            onClickListener.clickListener(differ.currentList[position], position)}

        holder.bind(differ.currentList[position], position)
    }



    class OnClickListener(val clickListener: (_artistModel: ArtistModel, _position : Int) -> Unit) {
        fun onClick(artist: ArtistModel, Position: Int) = clickListener(artist, Position)

    }

}