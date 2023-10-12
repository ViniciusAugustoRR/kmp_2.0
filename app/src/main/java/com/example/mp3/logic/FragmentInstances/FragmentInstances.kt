package com.example.mp3.logic.FragmentInstances

import com.example.mp3.R
import com.example.mp3.view.fragments.AlbumFragment
import com.example.mp3.view.fragments.ArtistFragment
import com.example.mp3.view.fragments.MenuFragment
import com.example.mp3.view.fragments.TrackFragment

object FragmentInstances {

    val detailFrags = arrayListOf(
        MenuFragment()
    )

    val listFrags = arrayListOf(
        TrackFragment(),
        AlbumFragment(),
        ArtistFragment()
    )

    val iconList = arrayListOf(
        R.drawable.baseline_playlist_play_24,
        R.drawable.baseline_album_24,
        R.drawable.baseline_person_24
    )

}