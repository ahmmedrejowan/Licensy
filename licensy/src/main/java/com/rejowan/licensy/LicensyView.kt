package com.rejowan.licensy

import android.content.Context
import android.util.AttributeSet
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * Callback interface for license item clicks.
 */
fun interface OnLicenseClickListener {
    fun onLicenseClick(license: LicenseContent)
}

/**
 * A RecyclerView-based view for displaying open source licenses.
 *
 * Supports multiple visual styles via [style] property:
 * - [LicensyStyle.STANDARD]: Expandable list (default)
 * - [LicensyStyle.COMPACT]: Minimal single-row layout
 * - [LicensyStyle.CARD]: Material CardView with elevation
 * - [LicensyStyle.DETAILED]: Full details visible without expansion
 *
 * Supports multiple interaction modes via [interactionMode] property:
 * - [LicensyInteractionMode.EXPAND_INLINE]: Expand/collapse within list (default)
 * - [LicensyInteractionMode.DIALOG]: Show details in centered dialog
 * - [LicensyInteractionMode.BOTTOM_SHEET]: Show details in bottom sheet
 */
class LicensyView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : RecyclerView(context, attrs, defStyleAttr) {

    // Flag to prevent redundant updates during initialization
    private var isInitializing = true

    // Color customization
    @get:ColorInt
    var lvPrimaryColor: Int = ContextCompat.getColor(context, R.color.licensy_primary)
        set(value) {
            if (field != value) {
                field = value
                notifyCustomizationChanged()
            }
        }

    @get:ColorInt
    var lvSecondaryColor: Int = ContextCompat.getColor(context, R.color.licensy_secondary)
        set(value) {
            if (field != value) {
                field = value
                notifyCustomizationChanged()
            }
        }

    @get:ColorInt
    var lvLinkColor: Int = ContextCompat.getColor(context, R.color.licensy_link)
        set(value) {
            if (field != value) {
                field = value
                notifyCustomizationChanged()
            }
        }

    var lvTitleTextSize: Float = 0f
        set(value) {
            if (field != value) {
                field = value
                notifyCustomizationChanged()
            }
        }

    @get:ColorInt
    var lvBackgroundColor: Int = ContextCompat.getColor(context, R.color.licensy_background)
        set(value) {
            if (field != value) {
                field = value
                notifyCustomizationChanged()
            }
        }

    @get:ColorInt
    var lvBackgroundColorExpand: Int = ContextCompat.getColor(context, R.color.licensy_background_expand)
        set(value) {
            if (field != value) {
                field = value
                notifyCustomizationChanged()
            }
        }

    @get:DrawableRes
    var lvOpenImage: Int = R.drawable.ic_licensy_open
        set(value) {
            if (field != value) {
                field = value
                notifyCustomizationChanged()
            }
        }

    @get:ColorInt
    var imageTint: Int = ContextCompat.getColor(context, R.color.licensy_secondary)
        set(value) {
            if (field != value) {
                field = value
                notifyCustomizationChanged()
            }
        }

    @get:ColorInt
    var lvDividerColor: Int = ContextCompat.getColor(context, R.color.licensy_divider)
        set(value) {
            if (field != value) {
                field = value
                notifyCustomizationChanged()
            }
        }

    /**
     * The visual style for displaying license items.
     */
    var style: LicensyStyle = LicensyStyle.STANDARD
        set(value) {
            if (field != value) {
                field = value
                licensyAdapter.updateStyle(value)
            }
        }

    /**
     * The interaction mode for license item clicks.
     */
    var interactionMode: LicensyInteractionMode = LicensyInteractionMode.EXPAND_INLINE
        set(value) {
            if (field != value) {
                field = value
                licensyAdapter.updateInteractionMode(value)
            }
        }

    /**
     * Corner radius for CardView style (in pixels).
     */
    var cardCornerRadius: Float = 12f
        set(value) {
            if (field != value) {
                field = value
                licensyAdapter.updateCardProperties(value, cardElevation)
            }
        }

    /**
     * Elevation for CardView style (in pixels).
     */
    var cardElevation: Float = 4f
        set(value) {
            if (field != value) {
                field = value
                licensyAdapter.updateCardProperties(cardCornerRadius, value)
            }
        }

    /**
     * Listener for license item click events.
     */
    var onLicenseClickListener: OnLicenseClickListener? = null

    private val animationDuration = 200L
    private val licensyAdapter: LicensyAdapter

    init {
        layoutManager = LinearLayoutManager(context)

        // Initialize adapter with default values
        licensyAdapter = LicensyAdapter(
            style = style,
            interactionMode = interactionMode,
            customization = buildCustomization(),
            cardCornerRadius = cardCornerRadius,
            cardElevation = cardElevation,
            animationDuration = animationDuration
        )

        licensyAdapter.onItemClick = { license ->
            onLicenseClickListener?.onLicenseClick(license)
            handleItemClick(license)
        }

        adapter = licensyAdapter

        // Parse XML attributes
        initAttributes(context, attrs)

        // Set demo item for preview in Android Studio
        if (isInEditMode) {
            val demo = LicenseContent(
                "Licensy",
                "ahmmedrejowan",
                Licenses.APACHE_2_0,
                "2024",
                "https://github.com/ahmmedrejowan/Licensy"
            )
            licensyAdapter.updateList(listOf(demo))
        }

        isInitializing = false
    }

    private fun initAttributes(context: Context, attrs: AttributeSet?) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.LicensyView)

        try {
            lvPrimaryColor = typedArray.getColor(
                R.styleable.LicensyView_lv_text_color_primary,
                lvPrimaryColor
            )
            lvSecondaryColor = typedArray.getColor(
                R.styleable.LicensyView_lv_text_color_secondary,
                lvSecondaryColor
            )
            lvLinkColor = typedArray.getColor(
                R.styleable.LicensyView_lv_text_color_link,
                lvLinkColor
            )
            lvTitleTextSize = typedArray.getDimension(
                R.styleable.LicensyView_lv_text_size_title,
                lvTitleTextSize
            )
            lvBackgroundColor = typedArray.getColor(
                R.styleable.LicensyView_lv_background_color,
                lvBackgroundColor
            )
            lvBackgroundColorExpand = typedArray.getColor(
                R.styleable.LicensyView_lv_background_color_expand,
                lvBackgroundColorExpand
            )
            lvOpenImage = typedArray.getResourceId(
                R.styleable.LicensyView_lv_open_image,
                lvOpenImage
            )
            imageTint = typedArray.getColor(
                R.styleable.LicensyView_lv_image_tint,
                imageTint
            )
            lvDividerColor = typedArray.getColor(
                R.styleable.LicensyView_lv_divider_color,
                lvDividerColor
            )

            // Style attributes
            val styleOrdinal = typedArray.getInt(
                R.styleable.LicensyView_lv_style,
                LicensyStyle.STANDARD.ordinal
            )
            style = LicensyStyle.entries[styleOrdinal]

            val modeOrdinal = typedArray.getInt(
                R.styleable.LicensyView_lv_interaction_mode,
                LicensyInteractionMode.EXPAND_INLINE.ordinal
            )
            interactionMode = LicensyInteractionMode.entries[modeOrdinal]

            cardCornerRadius = typedArray.getDimension(
                R.styleable.LicensyView_lv_card_corner_radius,
                cardCornerRadius
            )
            cardElevation = typedArray.getDimension(
                R.styleable.LicensyView_lv_card_elevation,
                cardElevation
            )

        } finally {
            typedArray.recycle()
        }

        // Apply initial customization after all attributes are parsed
        licensyAdapter.updateCustomization(buildCustomization())
    }

    /**
     * Sets the list of licenses to display.
     */
    fun setLicenses(listOfLicenses: List<LicenseContent>) {
        licensyAdapter.updateList(listOfLicenses)
    }

    /**
     * Applies a [LicensyCustomization] object to configure all styling properties.
     */
    fun setCustomization(customization: LicensyCustomization) {
        // Temporarily disable notifications
        isInitializing = true

        lvPrimaryColor = customization.lvPrimaryColor
        lvSecondaryColor = customization.lvSecondaryColor
        lvLinkColor = customization.lvLinkColor
        lvTitleTextSize = customization.lvTitleTextSize
        lvBackgroundColor = customization.lvBackgroundColor
        lvBackgroundColorExpand = customization.lvBackgroundColorExpand
        lvOpenImage = customization.lvOpenImage
        imageTint = customization.imageTint
        lvDividerColor = customization.lvDividerColor

        isInitializing = false

        // Single update after all properties are set
        licensyAdapter.updateCustomization(buildCustomization())
    }

    private fun buildCustomization(): LicensyCustomization {
        return LicensyCustomization(
            lvPrimaryColor = lvPrimaryColor,
            lvSecondaryColor = lvSecondaryColor,
            lvLinkColor = lvLinkColor,
            lvTitleTextSize = lvTitleTextSize,
            lvBackgroundColor = lvBackgroundColor,
            lvBackgroundColorExpand = lvBackgroundColorExpand,
            lvOpenImage = lvOpenImage,
            imageTint = imageTint,
            lvDividerColor = lvDividerColor
        )
    }

    private fun notifyCustomizationChanged() {
        if (!isInitializing) {
            licensyAdapter.updateCustomization(buildCustomization())
        }
    }

    private fun handleItemClick(license: LicenseContent) {
        when (interactionMode) {
            LicensyInteractionMode.EXPAND_INLINE -> {
                // Handled by the ViewHolder
            }
            LicensyInteractionMode.DIALOG -> {
                LicenseDetailPresenter.showDialog(context, license, buildCustomization())
            }
            LicensyInteractionMode.BOTTOM_SHEET -> {
                LicenseDetailPresenter.showBottomSheet(context, license, buildCustomization())
            }
        }
    }
}
