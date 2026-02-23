package com.rejowan.licensy

import android.graphics.Color
import androidx.annotation.ColorInt

data class LicensyCustomization(
    @param:ColorInt val lvPrimaryColor: Int = Color.parseColor("#121211"),
    @param:ColorInt val lvSecondaryColor: Int = Color.parseColor("#444444"),
    @param:ColorInt val lvLinkColor: Int = Color.parseColor("#0077cc"),
    val lvTitleTextSize: Float = 0f,
    @param:ColorInt val lvBackgroundColor: Int = Color.WHITE,
    @param:ColorInt val lvBackgroundColorExpand: Int = Color.parseColor("#f8f8f8"),
    val lvOpenImage: Int = R.drawable.ic_licensy_open,
    @param:ColorInt val imageTint: Int = Color.parseColor("#444444"),
    @param:ColorInt val lvDividerColor: Int = Color.parseColor("#e0e0e0")
)