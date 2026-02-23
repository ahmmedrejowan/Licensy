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

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Set initial licenses
        binding.licensyView.setLicenses(Credit.listOfLicenses)

        // Style chip listeners
        binding.styleChipGroup.setOnCheckedStateChangeListener { _, checkedIds ->
            if (checkedIds.isNotEmpty()) {
                when (checkedIds.first()) {
                    binding.chipStandard.id -> binding.licensyView.style = LicensyStyle.STANDARD
                    binding.chipCompact.id -> binding.licensyView.style = LicensyStyle.COMPACT
                    binding.chipCard.id -> binding.licensyView.style = LicensyStyle.CARD
                    binding.chipDetailed.id -> binding.licensyView.style = LicensyStyle.DETAILED
                }
            }
        }

        // Interaction mode chip listeners
        binding.modeChipGroup.setOnCheckedStateChangeListener { _, checkedIds ->
            if (checkedIds.isNotEmpty()) {
                when (checkedIds.first()) {
                    binding.chipExpand.id -> binding.licensyView.interactionMode = LicensyInteractionMode.EXPAND_INLINE
                    binding.chipDialog.id -> binding.licensyView.interactionMode = LicensyInteractionMode.DIALOG
                    binding.chipBottomSheet.id -> binding.licensyView.interactionMode = LicensyInteractionMode.BOTTOM_SHEET
                }
            }
        }

        // Activity navigation
        binding.activityCardView.setOnClickListener {
            startActivity(Intent(this, Credit::class.java))
        }
    }
}
