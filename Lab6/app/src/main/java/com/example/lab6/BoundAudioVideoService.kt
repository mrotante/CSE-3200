package com.example.lab6

import android.app.Service
import android.content.Intent
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.net.Uri
import android.os.*
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util

class BoundAudioVideoService : Service() {

    private lateinit var audioPlayer: MediaPlayer
    private lateinit var videoPlayer : SimpleExoPlayer

    private val binder = LocalBinder()

    inner class LocalBinder : Binder() {
        fun getService() : BoundAudioVideoService = this@BoundAudioVideoService;
    }

    override fun onBind(p0: Intent?): IBinder? {
        setUpVideoPlayer()
        setUpRadio()
        return binder
    }

    fun playVideo(url: String, playerView: PlayerView) {
        videoPlayer?.stop()
        playerView.player = videoPlayer
        val dataSourceFactory = DefaultDataSourceFactory(
            this,
            Util.getUserAgent(this, getString(R.string.app_name)))
        val videoSource = ProgressiveMediaSource.Factory(dataSourceFactory)
            .createMediaSource(Uri.parse(url))
        videoPlayer?.prepare(videoSource)
        videoPlayer?.playWhenReady = true
    }

    fun stopVideo() {
        videoPlayer?.stop()
    }



    fun playAudio(url : String) {
        audioPlayer.reset()
        audioPlayer.setDataSource(url)
        audioPlayer.prepare()
        audioPlayer.start()
    }

    fun stopAudio() {
        audioPlayer.stop()
        audioPlayer.reset()
    }

    private fun setUpRadio() {
        audioPlayer = MediaPlayer().apply {
            setAudioAttributes(
                AudioAttributes.Builder().setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .setUsage(AudioAttributes.USAGE_MEDIA).build()
            )
        }
    }

    private fun setUpVideoPlayer() {
        videoPlayer = SimpleExoPlayer.Builder(this).build()
    }
}