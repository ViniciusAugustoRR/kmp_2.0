package com.example.mp3.view.fragments

import android.icu.text.CaseMap.Title
import android.media.Image
import android.media.MediaMetadataRetriever
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import androidx.compose.runtime.snapshots.Snapshot.Companion.observe
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.mp3.R
import com.example.mp3.data.repository.MPlayer._Pause
import com.example.mp3.data.repository.MPlayer._Play
import com.example.mp3.databinding.FragmentRunningPlayerBinding
import com.example.mp3.logic.viewmodels.PlayerVM
import com.example.mp3.logic.viewmodels.TestingVM
import com.google.android.material.slider.Slider


class RunningPlayerFragment : Fragment() {
    private lateinit var binding : FragmentRunningPlayerBinding
    private val mmr = MediaMetadataRetriever()
    lateinit var playerVM: PlayerVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        try {

            playerVM = ViewModelProvider(requireActivity())[PlayerVM::class.java]
            playerVM._Track.observe(viewLifecycleOwner) {
                killMP()
                createMP()

                setSeekBar()
                setAssets()

                if (playerVM.isDefaultTrack.value!!) {
                    _Play()
                    _Pause()
                } else _Play()
            }

            playerVM._progressBar.observe(viewLifecycleOwner) { music_prog ->
                binding.runningSlider.progress = music_prog
            }
            playerVM._tempoDecor.observe(viewLifecycleOwner) { time_prog ->
                binding.runningTimePassed.text = time_prog
            }
            playerVM._tempoTotal.observe(viewLifecycleOwner) { time_total ->
                binding.runningTimeTotal.text = time_total
            }

            binding.runningPrevious.setOnClickListener{_Previus()}
            binding.runningNext.setOnClickListener{_Next()}
            binding.runningPlayPause.setOnClickListener{_PlayPause()}

        } catch (e: Exception){
            var x = e.message
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //val view = inflater.inflate(R.layout.fragment_running_player, container, false)
        binding = FragmentRunningPlayerBinding.inflate(layoutInflater)
        val view = binding.root
        return view
    }

    private fun createMP(){
        val uri = Uri.parse(playerVM._Track.value!!.mDirect)
        val newMP = MediaPlayer.create(this.context, uri)
        playerVM.setMP(newMP)
    }
    private fun killMP(){
        playerVM.getMP().value!!.stop()
        playerVM.getMP().value!!.release()
    }

    private fun _Next(){
        if(playerVM.getPosition().value!! < playerVM.getReprodList().value!!.size){
            playerVM.isDefaultTrack.value = false
            playerVM.setPlusPosition()
        }
    }
    private fun _Previus(){
        if(playerVM.getPosition().value!! > 0){
            playerVM.isDefaultTrack.value = false
            playerVM.setSubPosition()
        }
    }

    private fun _Pause(){
        playerVM._Pause()
        binding.runningPlayPause.setBackgroundResource(R.drawable.baseline_play_arrow_24)
    }
    private fun _Play(){
        playerVM._Play()
        binding.runningPlayPause.setBackgroundResource(R.drawable.baseline_pause_24)
    }
    private fun _PlayPause(){
        if (playerVM.getMP().value!!.isPlaying) {
            _Pause()
        } else {
            _Play()
        }
    }

    private fun setSeekBar(){
        binding.runningSlider.max = playerVM.getMP().value!!.duration
        binding.runningSlider.progress = 0

        setSeekBarListener()
    }
    private fun setSeekBarListener(){

        binding.runningSlider.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {

            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    binding.runningSlider.progress = progress
                    playerVM.setTempoDecorrido(progress)
                }
            }
            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {
                playerVM.getMP().value!!.seekTo(binding.runningSlider.progress)
                playerVM.setTempoDecorrido(binding.runningSlider.progress)
            }
        })
    }

    fun setAssets(){
        mmr.setDataSource(playerVM._Track.value!!.mDirect)
        Glide.with(requireActivity())
            .asBitmap()
            .load(mmr.embeddedPicture)
            .into(binding.runningCover)
            binding.runningTitle.text = playerVM._Track.value!!.trackName

        playerVM.setTempoTotal()
    }

}