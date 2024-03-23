package com.rejowan.licensysample

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.rejowan.licensy.LicensyBottomSheet
import com.rejowan.licensy.LicensyDialog
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
//            licensyDialog.setTitle("Licenses")
//            licensyDialog.setCloseText("Dismiss")
//            licensyDialog.setAccentColor(Color.GREEN)
//            licensyDialog.setBackgroundColor(Color.DKGRAY)
            licensyDialog.setLicenses(list)
//            licensyDialog.setOnDialogListener(object : LicensyDialog.OnDialogListener {
//                override fun onShow() {
//                    Log.e("Licensy", "Dialog shown")
//                }
//                override fun onDismiss() {
//                    Log.e("Licensy", "Dialog dismissed")
//                }
//            })
//            licensyDialog.setCustomization(
//                LicensyDialog.LicensyCustomization(
//
//                )
//            )
            licensyDialog.show()

        }

        binding.bottomSheetCardView.setOnClickListener {

            val list = Credit.listOfLicenses

            val licensyBottomSheet = LicensyBottomSheet(this)
//            licensyBottomSheet.setTitle("Licenses")
//            licensyBottomSheet.setCloseText("Dismiss")
//            licensyBottomSheet.setAccentColor(Color.GREEN)
//            licensyBottomSheet.setBackgroundColor(Color.RED)
            licensyBottomSheet.setLicenses(list)
//            licensyBottomSheet.setOnDialogListener(object : LicensyBottomSheet.OnDialogListener {
//                override fun onShow() {
//                    Log.e("Licensy", "Dialog shown")
//                }
//
//                override fun onDismiss() {
//                    Log.e("Licensy", "Dialog dismissed")
//                }
//
//            })
//            licensyBottomSheet.setCustomization(
//                LicensyBottomSheet.LicensyCustomization(
//                    lvPrimaryColor = Color.RED
//                )
//            )
            licensyBottomSheet.show()
        }

    }
}