package com.example.lab_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    private val text : EditText
        get() = findViewById(R.id.output)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onPause() {
        super.onPause()
        Log.e("DEBUG_ACTIVITY_STATE", "Application paused.")
        text.setText(text.text.toString() + "\n Application paused.\n")
    }

    override fun onStop() {
        super.onStop()
        Log.e("DEBUG_ACTIVITY_STATE", "Application stopped.")
        text.setText(text.text.toString() + "\n Application stopped.\n")
    }

    override fun onResume() {
        super.onResume()
        Log.e("DEBUG_ACTIVITY_STATE", "Application resumed.")
        text.setText(text.text.toString() + "\n Application resumed.\n")
    }
}