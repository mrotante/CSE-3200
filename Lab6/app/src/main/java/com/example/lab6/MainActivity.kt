package com.example.lab6

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.os.Message
import android.os.Messenger
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.VideoView

class MainActivity : AppCompatActivity() {

    private lateinit var mService: BoundAudioVideoService
    private var mBound: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val radioStations: HashMap<String, String> = hashMapOf(
            "Classic Radio" to "https://icecast.walmradio.com:8443/classic?type=.mp3",
            "Smooth Jazz Radio" to "https://0n-smoothjazz.radionetz.de/0n-smoothjazz.aac",
            "70s Radio" to "https://0n-70s.radionetz.de/0n-70s.mp3",
            "90s Radio" to "https://0n-90s.radionetz.de/0n-90s.mp3")

        val videoLinks: HashMap<String, String> = hashMapOf(
            "Park" to "https://samplelib.com/lib/preview/mp4/sample-30s.mp4",
            "Busses" to "https://samplelib.com/lib/preview/mp4/sample-10s.mp4",
            "Traffic" to "https://samplelib.com/lib/preview/mp4/sample-20s.mp4")

        val radios = resources.getStringArray(R.array.radios)
        val videos = resources.getStringArray(R.array.videos)

        val radioSpinner = findViewById<Spinner>(R.id.radioSpinner)
        val videoSpinner = findViewById<Spinner>(R.id.videoSpinner)
        val radioStart = findViewById<Button>(R.id.radioStart)
        val radioStop = findViewById<Button>(R.id.radioStop)
        val videoStart = findViewById<Button>(R.id.videoStart)
        val videoStop = findViewById<Button>(R.id.videoStop)


        if (radioSpinner != null) {
            val radioAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, radios)
            radioSpinner.adapter = radioAdapter
        }

        if (videoSpinner != null) {
            val videoAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, videos)
            videoSpinner.adapter = videoAdapter
        }

        radioStart.setOnClickListener{
            radioStations[radioSpinner.selectedItem.toString()]?.let { it1 -> mService.playAudio(it1) }
        }

        radioStop.setOnClickListener{
            mService.stopAudio()
        }

        videoStart.setOnClickListener{
            videoLinks[videoSpinner.selectedItem.toString()]?.let { it1 -> mService.playVideo(it1, findViewById(R.id.player_view))}
        }

        videoStop.setOnClickListener{
            mService.stopVideo()
        }
    }

    override fun onStart() {
        super.onStart()
        Intent(this, BoundAudioVideoService::class.java).also {
            intent -> bindService(intent, connection, Context.BIND_AUTO_CREATE)
        }
    }

    override fun onStop() {
        super.onStop()
        unbindService(connection)
        mBound = false
    }

    private val connection = object : ServiceConnection {
        override fun onServiceConnected(p0: ComponentName?, service: IBinder?) {
            val binder = service as BoundAudioVideoService.LocalBinder
            mService = binder.getService()
            mBound = true
        }

        override fun onServiceDisconnected(p0: ComponentName?) {
            mBound = false
        }
    }
}