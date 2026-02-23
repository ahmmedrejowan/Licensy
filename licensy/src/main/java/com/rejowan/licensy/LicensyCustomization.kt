package com.rejowan.licensy

import android.graphics.Color
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.core.graphics.toColorInt

/**
 * Data class for customizing the appearance of [LicensyView].
 *
 * @param lvPrimaryColor Primary text color (titles, names)
 * @param lvSecondaryColor Secondary text color (labels, descriptions)
 * @param lvLinkColor Color for clickable links
 * @param lvTitleTextSize Title text size in pixels (0 for default)
 * @param lvBackgroundColor Background color for items
 * @param lvBackgroundColorExpand Background color for expanded sections
 * @param lvOpenImage Drawable resource for the open/link icon
 * @param imageTint Tint color for the open icon
 * @param lvDividerColor Color for divider lines
 */
data class LicensyCustomization(
    @param:ColorInt val lvPrimaryColor: Int = "#121211".toColorInt(),
    @param:ColorInt val lvSecondaryColor: Int = "#444444".toColorInt(),
    @param:ColorInt val lvLinkColor: Int = "#0077cc".toColorInt(),
    val lvTitleTextSize: Float = 0f,
    @param:ColorInt val lvBackgroundColor: Int = Color.WHITE,
    @param:ColorInt val lvBackgroundColorExpand: Int = "#f8f8f8".toColorInt(),
    val lvOpenImage: Int = R.drawable.ic_licensy_open,
    @param:ColorInt val imageTint: Int = "#444444".toColorInt(),
    @param:ColorInt val lvDividerColor: Int = "#e0e0e0".toColorInt()
)