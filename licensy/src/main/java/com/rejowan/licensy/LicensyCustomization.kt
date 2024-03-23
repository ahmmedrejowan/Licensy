package com.rejowan.licensy

import android.graphics.Color

data class LicensyCustomization(
    val lvPrimaryColor : Int = Color.parseColor("#121211"),
    val lvSecondaryColor : Int = Color.parseColor("#444444"),
    val lvLinkColor : Int = Color.parseColor("#0077cc"),
    val lvTitleTextSize : Float = 0f,
    val lvBackgroundColor : Int = Color.WHITE,
    val lvBackgroundColorExpand : Int = Color.parseColor("#f8f8f8"),
    val lvOpenImage : Int = R.drawable.ic_licensy_open,
    val imageTint : Int = Color.parseColor("#444444"),
    val lvDividerColor : Int = Color.parseColor("#e0e0e0")

)