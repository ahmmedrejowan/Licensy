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
 * @param lvButtonPrimaryBgColor Background color for primary action button (defaults to linkColor)
 * @param lvButtonPrimaryTextColor Text color for primary action button
 * @param lvButtonSecondaryBgColor Background color for secondary action button (defaults to backgroundColorExpand)
 * @param lvButtonSecondaryTextColor Text color for secondary action button (defaults to primaryColor)
 */
data class LicensyCustomization(
    @param:ColorInt val lvPrimaryColor: Int = "#1A1A1A".toColorInt(),
    @param:ColorInt val lvSecondaryColor: Int = "#666666".toColorInt(),
    @param:ColorInt val lvLinkColor: Int = "#1976D2".toColorInt(),
    val lvTitleTextSize: Float = 0f,
    @param:ColorInt val lvBackgroundColor: Int = Color.WHITE,
    @param:ColorInt val lvBackgroundColorExpand: Int = Color.WHITE,
    val lvOpenImage: Int = R.drawable.ic_licensy_open,
    @param:ColorInt val imageTint: Int = "#666666".toColorInt(),
    @param:ColorInt val lvDividerColor: Int = "#E0E0E0".toColorInt(),
    @param:ColorInt val lvButtonPrimaryBgColor: Int = 0,
    @param:ColorInt val lvButtonPrimaryTextColor: Int = Color.WHITE,
    @param:ColorInt val lvButtonSecondaryBgColor: Int = "#F5F5F5".toColorInt(),
    @param:ColorInt val lvButtonSecondaryTextColor: Int = 0
) {
    /** Resolved primary button background (uses linkColor if not set) */
    val resolvedButtonPrimaryBgColor: Int
        get() = if (lvButtonPrimaryBgColor == 0) lvLinkColor else lvButtonPrimaryBgColor

    /** Resolved secondary button background (uses backgroundColorExpand if not set) */
    val resolvedButtonSecondaryBgColor: Int
        get() = if (lvButtonSecondaryBgColor == 0) lvBackgroundColorExpand else lvButtonSecondaryBgColor

    /** Resolved secondary button text color (uses primaryColor if not set) */
    val resolvedButtonSecondaryTextColor: Int
        get() = if (lvButtonSecondaryTextColor == 0) lvPrimaryColor else lvButtonSecondaryTextColor
}