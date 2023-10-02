package com.example.mp3.view.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mp3.R
import com.example.mp3.view.fragments.ItemFragment

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