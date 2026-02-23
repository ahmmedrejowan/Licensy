package com.rejowan.licensy

/**
 * Sealed interface representing a license type.
 * Can be either a predefined [Licenses] enum value or a [CustomLicense].
 */
sealed interface LicenseType {
    val shortName: String
    val fullName: String
    val description: String
    val url: String
}

/**
 * Data class for defining custom licenses not included in the predefined [Licenses] enum.
 */
data class CustomLicense(
    override val shortName: String,
    override val fullName: String,
    override val description: String,
    override val url: String
) : LicenseType

/**
 * Data class representing a single license entry in the list.
 *
 * @param title The name of the library/project
 * @param author The author of the library/project
 * @param license The license type (either a [Licenses] enum value or a [CustomLicense])
 * @param copyrightYear Optional copyright year
 * @param url Optional URL to the repository
 */
data class LicenseContent(
    val title: String,
    val author: String,
    val license: LicenseType,
    val copyrightYear: String? = null,
    val url: String? = null
)
