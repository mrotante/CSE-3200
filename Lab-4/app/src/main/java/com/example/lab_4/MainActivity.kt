package com.example.lab_4

import android.media.AudioAttributes
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {

    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var mediaThread: HandlerThread
    private lateinit var mediaHandler: Handler
    private lateinit var nowPlaying : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        nowPlaying = findViewById(R.id.nowPlaying)

        mediaThread = HandlerThread("MediaThread")
        mediaThread.start()
        mediaHandler = Handler(mediaThread.looper)

        setUpRadio()

        val radioArray = AllRadios(this)
        val recyclerView : RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = RadioView(radioArray, mediaHandler, mediaPlayer, nowPlaying)

    }

    private fun setUpRadio() {
        mediaPlayer = MediaPlayer().apply {
            setAudioAttributes(
                AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .build()
            )
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaThread.quitSafely()
    }
}
