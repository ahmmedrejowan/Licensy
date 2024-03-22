package com.rejowan.licensy

data class LicenseContent(
    val title: String,
    val author: String,
    val licenses: Licenses,
    val copyrightYear: Int? = null,
    val url: String? = null
)