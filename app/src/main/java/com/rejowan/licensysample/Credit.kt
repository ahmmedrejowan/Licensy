package com.rejowan.licensysample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rejowan.licensy.LicenseContent
import com.rejowan.licensy.Licenses
import com.rejowan.licensysample.databinding.ActivityCreditBinding

class Credit : AppCompatActivity() {


    companion object {
        val listOfLicenses = mutableListOf<LicenseContent>().apply {
            add(
                LicenseContent(
                    "Rotary Knob",
                    "ahmmedrejowan",
                    Licenses.APACHE_2_0,
                    "2024",
                    "https://github.com/ahmmedrejowan/RotaryKnob"
                )
            )
            add(
                LicenseContent(
                    "AndroidBatteryView",
                    "ahmmedrejowan",
                    Licenses.APACHE_2_0,
                    "2024",
                    "https://github.com/ahmmedrejowan/AndroidBatteryView"
                )
            )
            add(
                LicenseContent(
                    "retrofit",
                    "square",
                    Licenses.APACHE_2_0,
                    null,
                    "https://github.com/square/retrofit"
                )
            )
            add(
                LicenseContent(
                    "test", "test author", Licenses.BSD_2_CLAUSE, null, null
                )
            )
            add(
                LicenseContent(
                    "test 2", "test author 2", Licenses.CC_0, null, null
                )
            )
            add(
                LicenseContent(
                    "test 3", "test author 3", Licenses.MIT, null, null
                )
            )
            add(
                LicenseContent(
                    "test 4", "test author 4", Licenses.MPL_2_0, null, null
                )
            )
            add(
                LicenseContent(
                    "test 5", "test author 5", Licenses.GPL_3_0, null, null
                )
            )
            add(
                LicenseContent(
                    "test 6", "test author 6", Licenses.EPL_2_0, null, null
                )
            )
            add(
                LicenseContent(
                    "test 7", "test author 7", Licenses.GPL_2_0, null, null
                )
            )
            add(
                LicenseContent(
                    "test 8", "test author 8", Licenses.LGPL_LESS_2_1, null, null
                )
            )
        }


    }

    private val binding: ActivityCreditBinding by lazy {
        ActivityCreditBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val licensyView = binding.licensyView


        licensyView.setLicenses(listOfLicenses) // set the licenses to the view


    }

}