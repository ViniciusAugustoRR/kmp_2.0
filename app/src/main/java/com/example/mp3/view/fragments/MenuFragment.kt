package com.example.mp3.view.fragments

import android.annotation.SuppressLint
import android.content.res.Resources.Theme
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.mp3.R
import com.example.mp3.databinding.FragmentMenuBinding
import com.example.mp3.view.adapters.MenuPageAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class MenuFragment : Fragment() {
    private lateinit var binding : FragmentMenuBinding
    lateinit var  menuPageAdapter : MenuPageAdapter



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //val view = inflater.inflate(R.layout.fragment_menu, container, false)
        binding = FragmentMenuBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        try {
            menuPageAdapter = MenuPageAdapter(requireActivity())
            binding.menuViewPager.adapter = menuPageAdapter
            setTabLayoutMediator(binding.menuTabs, binding.menuViewPager, menuPageAdapter)
        } catch (e: Exception){
            var x = e.message
        }
    }
    @SuppressLint("UseCompatLoadingForDrawables")
    private fun setTabLayoutMediator
        (tabLayout: TabLayout,
         viewPager2: ViewPager2,
         pageAdapter: MenuPageAdapter
    ){
        TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
            tab.icon = resources.getDrawable(pageAdapter.getIcon(position), null)
        }.attach()
    }

}