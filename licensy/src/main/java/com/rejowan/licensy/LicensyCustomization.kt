package com.rejowan.licensy

import android.graphics.Color
import androidx.annotation.ColorInt

data class LicensyCustomization(
    @ColorInt val lvPrimaryColor: Int = Color.parseColor("#121211"),
    @ColorInt val lvSecondaryColor: Int = Color.parseColor("#444444"),
    @ColorInt val lvLinkColor: Int = Color.parseColor("#0077cc"),
    val lvTitleTextSize: Float = 0f,
    @ColorInt val lvBackgroundColor: Int = Color.WHITE,
    @ColorInt val lvBackgroundColorExpand: Int = Color.parseColor("#f8f8f8"),
    val lvOpenImage: Int = R.drawable.ic_licensy_open,
    @ColorInt val imageTint: Int = Color.parseColor("#444444"),
    @ColorInt val lvDividerColor: Int = Color.parseColor("#e0e0e0")

)