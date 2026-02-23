package com.rejowan.licensy

/**
 * Defines how license item interactions are handled in [LicensyView].
 */
enum class LicensyInteractionMode {
    /**
     * Expands/collapses details inline within the list item (default).
     */
    EXPAND_INLINE,

    /**
     * Opens a centered dialog with full license details when clicked.
     */
    DIALOG,

    /**
     * Opens a bottom sheet with full license details when clicked.
     */
    BOTTOM_SHEET
}
