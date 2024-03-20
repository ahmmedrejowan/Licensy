package com.rejowan.licensysample

data class LicenseContent(
    val title: String,
    val licenses: Licenses,
    val author: String,
    val copyrightYear: Int? = null,
    val url: String
)


