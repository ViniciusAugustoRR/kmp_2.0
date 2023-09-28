package com.example.mp3.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.example.mp3.R
import com.example.mp3.view.adapters.itemFragmentAdapter
import com.example.mp3.view.fragments.ItemFragment
import com.example.mp3.data.models.track

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragment = ItemFragment()

        supportFragmentManager
            .beginTransaction()
            .add(R.id.main_frame, fragment)
            .commit()

    }
}