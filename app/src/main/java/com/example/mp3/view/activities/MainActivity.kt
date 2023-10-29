package com.example.mp3.view.activities

import android.media.MediaMetadataRetriever
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.mp3.R
import com.example.mp3.databinding.ActivityMainBinding
import com.example.mp3.logic.FragmentInstances.FragmentInstances
import com.example.mp3.logic.viewmodels.PlayerVM
import com.example.mp3.view.fragments.MenuFragment
import com.example.mp3.view.fragments.TrackFragment
import com.example.mp3.view.fragments.RunningPlayerFragment
import com.sothree.slidinguppanel.SlidingUpPanelLayout

class MainActivity : AppCompatActivity() {
    private val mmr = MediaMetadataRetriever()
    private lateinit var playerVM: PlayerVM
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val callback = this.onBackPressedDispatcher.addCallback(this) {
            if(binding.mainSliderlayout.panelState == SlidingUpPanelLayout.PanelState.EXPANDED){
                binding.mainSliderlayout.panelState = SlidingUpPanelLayout.PanelState.COLLAPSED
            }
        }
        this.onBackPressedDispatcher.addCallback(callback)
        
        try {
            setPanelSlideLayout()
            playerVM = ViewModelProvider(this)[PlayerVM::class.java]
            playerVM._Track.observe(this, Observer { track ->
                binding.mainRunningTitle.text = track.trackName
                binding.mainRunningAlbum.text = track.albumName
                mmr.setDataSource(track.mDirect)
                Glide.with(this).asBitmap()
                    .load(mmr.embeddedPicture)
                    .into(binding.mainRunningCover)
            })

            supportFragmentManager
                .beginTransaction()
                .add(binding.mainFrame.id, FragmentInstances.detailFrags[0])
                .add(binding.mainRunningFrame.id, RunningPlayerFragment())
                .commit()

        } catch (e: Exception){
            var x = e.message;
        }
    }

    private fun setPanelSlideLayout(){
        val mSliderListener = object : SlidingUpPanelLayout.PanelSlideListener{
            override fun onPanelSlide(panel: View, slideOffset: Float) {
                binding.mainDragger.alpha = 1 - slideOffset
                binding.mainScrolled.alpha = slideOffset
                println("---------------------------- ----> $slideOffset")
            }
            override fun onPanelStateChanged(panel: View?, previousState: SlidingUpPanelLayout.PanelState?,
                                             newState: SlidingUpPanelLayout.PanelState?) {
                //TODO
            }

        }
        binding.mainSliderlayout.addPanelSlideListener(mSliderListener)
        binding.mainDragger.bringToFront()
    }

}