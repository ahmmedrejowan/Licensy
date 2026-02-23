package com.rejowan.licensy

/**
 * Defines the visual style for displaying license items in [LicensyView].
 */
enum class LicensyStyle {
    /**
     * Standard expandable list style (default).
     * Shows title, author, and license in a vertical layout with expandable details.
     */
    STANDARD,

    /**
     * Compact single-row style.
     * Shows title, author, and license badge in a minimal horizontal layout.
     */
    COMPACT,

    /**
     * Material CardView style with elevation and rounded corners.
     * Each item is displayed as an elevated card.
     */
    CARD,

    /**
     * Detailed style with full description visible.
     * All license information is shown without requiring expansion.
     */
    DETAILED
}
