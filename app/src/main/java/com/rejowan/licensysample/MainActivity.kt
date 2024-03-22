package com.rejowan.licensysample

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rejowan.licensy.LicenseContent
import com.rejowan.licensy.Licenses
import com.rejowan.licensysample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.activityCardView.setOnClickListener {
            startActivity(Intent(this, Credit::class.java))
        }

    }
}