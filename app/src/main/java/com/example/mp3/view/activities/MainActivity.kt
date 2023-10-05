package com.example.mp3.view.activities

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.accessibility.AccessibilityViewCommand.SetProgressArguments
import com.example.mp3.R
import com.example.mp3.view.fragments.ItemFragment
import com.example.mp3.view.fragments.RunningPlayerFragment
import com.sothree.slidinguppanel.SlidingUpPanelLayout

class MainActivity : AppCompatActivity() {


    lateinit var main_dragger : ConstraintLayout
    lateinit var main_scrolled : ConstraintLayout
    lateinit var main_sliderlayout : SlidingUpPanelLayout
    lateinit var main_title_tv : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*val fragment = ItemFragment()
        val runningFragment = RunningPlayerFragment()*/

        main_scrolled = findViewById(R.id.main_scrolled)
        main_dragger = findViewById(R.id.main_dragger)
        main_sliderlayout = findViewById(R.id.main_sliderlayout)
        main_title_tv = findViewById(R.id.main_runningTitle)
        setPanelSlideLayout()
        setAssets()
        main_dragger.bringToFront()
    }

    private fun setAssets() {

        supportFragmentManager
            .beginTransaction()
            .add(R.id.main_frame, ItemFragment())
            .add(R.id.main_runningFrame, RunningPlayerFragment())
            .commit()
    }
    private fun setPanelSlideLayout(){
        val mSliderListener = object : SlidingUpPanelLayout.PanelSlideListener{
            override fun onPanelSlide(panel: View, slideOffset: Float) {
                main_dragger.alpha = 1 - slideOffset
                main_scrolled.alpha = slideOffset
                println("--------------------------------> $slideOffset")
            }
            override fun onPanelStateChanged(panel: View?, previousState: SlidingUpPanelLayout.PanelState?,
                                             newState: SlidingUpPanelLayout.PanelState?) {
                //TODO
            }

        }
        main_sliderlayout.addPanelSlideListener(mSliderListener)
    }
}