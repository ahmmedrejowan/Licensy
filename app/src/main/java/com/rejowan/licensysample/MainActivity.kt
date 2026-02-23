package com.rejowan.licensysample

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.rejowan.licensy.LicensyInteractionMode
import com.rejowan.licensy.LicensyStyle
import com.rejowan.licensysample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }



        // Set initial licenses
        binding.licensyView.setLicenses(Credit.listOfLicenses)

        // Style buttons
        binding.btnStandard.setOnClickListener {
            binding.licensyView.style = LicensyStyle.STANDARD
            updateStyleButtons(LicensyStyle.STANDARD)
        }

        binding.btnCompact.setOnClickListener {
            binding.licensyView.style = LicensyStyle.COMPACT
            updateStyleButtons(LicensyStyle.COMPACT)
        }

        binding.btnCard.setOnClickListener {
            binding.licensyView.style = LicensyStyle.CARD
            updateStyleButtons(LicensyStyle.CARD)
        }

        binding.btnDetailed.setOnClickListener {
            binding.licensyView.style = LicensyStyle.DETAILED
            updateStyleButtons(LicensyStyle.DETAILED)
        }

        // Interaction mode buttons
        binding.btnExpandInline.setOnClickListener {
            binding.licensyView.interactionMode = LicensyInteractionMode.EXPAND_INLINE
            updateModeButtons(LicensyInteractionMode.EXPAND_INLINE)
        }

        binding.btnDialog.setOnClickListener {
            binding.licensyView.interactionMode = LicensyInteractionMode.DIALOG
            updateModeButtons(LicensyInteractionMode.DIALOG)
        }

        binding.btnBottomSheet.setOnClickListener {
            binding.licensyView.interactionMode = LicensyInteractionMode.BOTTOM_SHEET
            updateModeButtons(LicensyInteractionMode.BOTTOM_SHEET)
        }

        // Activity navigation
        binding.activityCardView.setOnClickListener {
            startActivity(Intent(this, Credit::class.java))
        }

        // Set initial selection states
        updateStyleButtons(LicensyStyle.STANDARD)
        updateModeButtons(LicensyInteractionMode.EXPAND_INLINE)
    }

    private fun updateStyleButtons(selected: LicensyStyle) {
        binding.btnStandard.isChecked = selected == LicensyStyle.STANDARD
        binding.btnCompact.isChecked = selected == LicensyStyle.COMPACT
        binding.btnCard.isChecked = selected == LicensyStyle.CARD
        binding.btnDetailed.isChecked = selected == LicensyStyle.DETAILED
    }

    private fun updateModeButtons(selected: LicensyInteractionMode) {
        binding.btnExpandInline.isChecked = selected == LicensyInteractionMode.EXPAND_INLINE
        binding.btnDialog.isChecked = selected == LicensyInteractionMode.DIALOG
        binding.btnBottomSheet.isChecked = selected == LicensyInteractionMode.BOTTOM_SHEET
    }
}
