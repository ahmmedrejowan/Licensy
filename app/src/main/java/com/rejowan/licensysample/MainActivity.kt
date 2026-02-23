package com.rejowan.licensysample

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.card.MaterialCardView
import com.rejowan.licensy.LicensyInteractionMode
import com.rejowan.licensy.LicensyStyle
import com.rejowan.licensysample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private var selectedThemeCard: MaterialCardView? = null

    // Theme color definitions
    data class ThemeColors(
        val primary: Int,
        val secondary: Int,
        val link: Int,
        val background: Int,
        val backgroundExpand: Int,
        val divider: Int
    )

    private val themes = mapOf(
        "default" to ThemeColors(
            primary = Color.parseColor("#1A1A1A"),
            secondary = Color.parseColor("#666666"),
            link = Color.parseColor("#1976D2"),
            background = Color.WHITE,
            backgroundExpand = Color.parseColor("#F8F8F8"),
            divider = Color.parseColor("#E0E0E0")
        ),
        "purple" to ThemeColors(
            primary = Color.parseColor("#4A148C"),
            secondary = Color.parseColor("#7B1FA2"),
            link = Color.parseColor("#7C4DFF"),
            background = Color.parseColor("#F3E5F5"),
            backgroundExpand = Color.parseColor("#E1BEE7"),
            divider = Color.parseColor("#CE93D8")
        ),
        "green" to ThemeColors(
            primary = Color.parseColor("#1B5E20"),
            secondary = Color.parseColor("#388E3C"),
            link = Color.parseColor("#00C853"),
            background = Color.parseColor("#E8F5E9"),
            backgroundExpand = Color.parseColor("#C8E6C9"),
            divider = Color.parseColor("#A5D6A7")
        ),
        "orange" to ThemeColors(
            primary = Color.parseColor("#E65100"),
            secondary = Color.parseColor("#F57C00"),
            link = Color.parseColor("#FF9100"),
            background = Color.parseColor("#FFF3E0"),
            backgroundExpand = Color.parseColor("#FFE0B2"),
            divider = Color.parseColor("#FFCC80")
        ),
        "dark" to ThemeColors(
            primary = Color.parseColor("#EEEEEE"),
            secondary = Color.parseColor("#BDBDBD"),
            link = Color.parseColor("#64B5F6"),
            background = Color.parseColor("#212121"),
            backgroundExpand = Color.parseColor("#303030"),
            divider = Color.parseColor("#424242")
        )
    )

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

        setupStyleChips()
        setupModeChips()
        setupThemeSelectors()
        setupCardSliders()

        // Activity navigation
        binding.activityCardView.setOnClickListener {
            startActivity(Intent(this, Credit::class.java))
        }

        // Set initial theme selection
        selectedThemeCard = binding.themeDefault
    }

    private fun setupStyleChips() {
        binding.styleChipGroup.setOnCheckedStateChangeListener { _, checkedIds ->
            if (checkedIds.isNotEmpty()) {
                val style = when (checkedIds.first()) {
                    binding.chipStandard.id -> LicensyStyle.STANDARD
                    binding.chipCompact.id -> LicensyStyle.COMPACT
                    binding.chipCard.id -> LicensyStyle.CARD
                    binding.chipDetailed.id -> LicensyStyle.DETAILED
                    else -> LicensyStyle.STANDARD
                }
                binding.licensyView.style = style

                // Show/hide card options
                binding.cardOptionsLayout.visibility =
                    if (style == LicensyStyle.CARD) View.VISIBLE else View.GONE
            }
        }
    }

    private fun setupModeChips() {
        binding.modeChipGroup.setOnCheckedStateChangeListener { _, checkedIds ->
            if (checkedIds.isNotEmpty()) {
                when (checkedIds.first()) {
                    binding.chipExpand.id -> binding.licensyView.interactionMode = LicensyInteractionMode.EXPAND_INLINE
                    binding.chipDialog.id -> binding.licensyView.interactionMode = LicensyInteractionMode.DIALOG
                    binding.chipBottomSheet.id -> binding.licensyView.interactionMode = LicensyInteractionMode.BOTTOM_SHEET
                }
            }
        }
    }

    private fun setupThemeSelectors() {
        val themeCards = mapOf(
            binding.themeDefault to "default",
            binding.themePurple to "purple",
            binding.themeGreen to "green",
            binding.themeOrange to "orange",
            binding.themeDark to "dark"
        )

        themeCards.forEach { (card, themeName) ->
            card.setOnClickListener {
                applyTheme(themeName)
                updateThemeSelection(card)
            }
        }
    }

    private fun applyTheme(themeName: String) {
        val theme = themes[themeName] ?: return

        binding.licensyView.apply {
            lvPrimaryColor = theme.primary
            lvSecondaryColor = theme.secondary
            lvLinkColor = theme.link
            lvBackgroundColor = theme.background
            lvBackgroundColorExpand = theme.backgroundExpand
            lvDividerColor = theme.divider
        }

        // Update container background for dark theme
        binding.licenseContainer.setCardBackgroundColor(theme.background)
    }

    private fun updateThemeSelection(selected: MaterialCardView) {
        // Reset previous selection
        selectedThemeCard?.strokeWidth = resources.getDimensionPixelSize(R.dimen.stroke_normal)
        selectedThemeCard?.strokeColor = Color.parseColor("#E0E0E0")

        // Set new selection
        selected.strokeWidth = resources.getDimensionPixelSize(R.dimen.stroke_selected)
        selected.strokeColor = Color.parseColor("#6C63FF")
        selectedThemeCard = selected
    }

    private fun setupCardSliders() {
        binding.sliderCornerRadius.addOnChangeListener { _, value, _ ->
            binding.tvCornerRadius.text = "${value.toInt()}dp"
            binding.licensyView.cardCornerRadius = value * resources.displayMetrics.density
        }

        binding.sliderElevation.addOnChangeListener { _, value, _ ->
            binding.tvElevation.text = "${value.toInt()}dp"
            binding.licensyView.cardElevation = value * resources.displayMetrics.density
        }
    }
}
