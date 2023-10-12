package com.example.mp3.view.adapters

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.mp3.logic.FragmentInstances.FragmentInstances

class MenuPageAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    private val pageFragmentList = FragmentInstances.listFrags
    private val iconList = FragmentInstances.iconList
    fun getIcon(position: Int) = iconList[position]
    override fun getItemCount() = pageFragmentList.size
    override fun createFragment(position: Int) = pageFragmentList[position]

}