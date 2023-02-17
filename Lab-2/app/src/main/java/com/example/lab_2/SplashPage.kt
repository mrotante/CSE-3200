package com.example.lab_2

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.lab_2.databinding.ActivitySplashPageBinding

class SplashPage : AppCompatActivity() {

    private lateinit var binding : ActivitySplashPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.launchButton.setOnClickListener(
            View.OnClickListener {
                startActivity(Intent(this, MainActivity::class.java))
            }
        )

    }
}