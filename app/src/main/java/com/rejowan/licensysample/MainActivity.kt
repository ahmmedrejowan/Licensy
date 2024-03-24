package com.rejowan.licensysample

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.rejowan.licensy.LicensyBottomSheet
import com.rejowan.licensy.LicensyDialog
import com.rejowan.licensy.OnDialogListener
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

        binding.dialogCardView.setOnClickListener {

            val list = Credit.listOfLicenses

            val licensyDialog = LicensyDialog(this)
            licensyDialog.setLicenses(list)
            licensyDialog.show()

        }

        binding.bottomSheetCardView.setOnClickListener {

            val list = Credit.listOfLicenses

            val licensyBottomSheet = LicensyBottomSheet(this)
            licensyBottomSheet.setLicenses(list)
            licensyBottomSheet.show()
        }

    }



}



