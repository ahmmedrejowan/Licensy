package com.rejowan.licensysample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rejowan.licensy.LicenseContent
import com.rejowan.licensy.Licenses
import com.rejowan.licensysample.databinding.ActivityCreditBinding

class Credit : AppCompatActivity() {

    private val binding: ActivityCreditBinding by lazy {
        ActivityCreditBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val listOfLicenses = mutableListOf<LicenseContent>()
        listOfLicenses.add(
            LicenseContent(
                "Rotary Knob",
                "ahmmedrejowan",
                Licenses.APACHE_2_0,
                2024,
                "https://github.com/ahmmedrejowan/RotaryKnob"
            )
        )
        listOfLicenses.add(
            LicenseContent(
                "AndroidBatteryView",
                "ahmmedrejowan",
                Licenses.APACHE_2_0,
                2024,
                "https://github.com/ahmmedrejowan/AndroidBatteryView"
            )
        )
        listOfLicenses.add(
            LicenseContent(
                "retrofit",
                "square",
                Licenses.APACHE_2_0,
                null,
                "https://github.com/square/retrofit"
            )
        )
        listOfLicenses.add(
            LicenseContent(
                "test", "test author", Licenses.BSD_2_CLAUSE, null, null
            )
        )
        listOfLicenses.add(
            LicenseContent(
                "test 2", "test author 2", Licenses.CC_0, null, null
            )
        )



        binding.licensyView.setLicenses(listOfLicenses)



    }

}