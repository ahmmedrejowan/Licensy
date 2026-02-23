package com.rejowan.licensy.compose

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Default colors and dimensions for Licensy Compose components.
 */
object LicensyDefaults {

    /**
     * Creates default colors for Licensy components.
     */
    @Composable
    fun colors(
        primaryColor: Color = MaterialTheme.colorScheme.onSurface,
        secondaryColor: Color = MaterialTheme.colorScheme.onSurfaceVariant,
        linkColor: Color = MaterialTheme.colorScheme.primary,
        backgroundColor: Color = MaterialTheme.colorScheme.surface,
        backgroundColorExpanded: Color = MaterialTheme.colorScheme.surfaceVariant,
        dividerColor: Color = MaterialTheme.colorScheme.outlineVariant,
        iconTint: Color = MaterialTheme.colorScheme.onSurfaceVariant
    ): LicensyColors = LicensyColors(
        primaryColor = primaryColor,
        secondaryColor = secondaryColor,
        linkColor = linkColor,
        backgroundColor = backgroundColor,
        backgroundColorExpanded = backgroundColorExpanded,
        dividerColor = dividerColor,
        iconTint = iconTint
    )

    /**
     * Creates default dimensions for Licensy components.
     */
    fun dimensions(
        titleTextSize: TextUnit = 16.sp,
        bodyTextSize: TextUnit = 14.sp,
        smallTextSize: TextUnit = 12.sp,
        itemPadding: Dp = 16.dp,
        cardCornerRadius: Dp = 12.dp,
        cardElevation: Dp = 2.dp,
        dividerThickness: Dp = 0.5.dp
    ): LicensyDimensions = LicensyDimensions(
        titleTextSize = titleTextSize,
        bodyTextSize = bodyTextSize,
        smallTextSize = smallTextSize,
        itemPadding = itemPadding,
        cardCornerRadius = cardCornerRadius,
        cardElevation = cardElevation,
        dividerThickness = dividerThickness
    )
}

/**
 * Color configuration for Licensy components.
 */
data class LicensyColors(
    val primaryColor: Color,
    val secondaryColor: Color,
    val linkColor: Color,
    val backgroundColor: Color,
    val backgroundColorExpanded: Color,
    val dividerColor: Color,
    val iconTint: Color,
    val buttonPrimaryBackground: Color = Color.Unspecified,
    val buttonPrimaryContent: Color = Color.White,
    val buttonSecondaryBackground: Color = Color.Unspecified,
    val buttonSecondaryContent: Color = Color.Unspecified
) {
    /** Resolved primary button background (uses linkColor if not set) */
    val resolvedButtonPrimaryBackground: Color
        get() = if (buttonPrimaryBackground == Color.Unspecified) linkColor else buttonPrimaryBackground

    /** Resolved secondary button background (uses backgroundColorExpanded if not set) */
    val resolvedButtonSecondaryBackground: Color
        get() = if (buttonSecondaryBackground == Color.Unspecified) backgroundColorExpanded else buttonSecondaryBackground

    /** Resolved secondary button content color (uses primaryColor if not set) */
    val resolvedButtonSecondaryContent: Color
        get() = if (buttonSecondaryContent == Color.Unspecified) primaryColor else buttonSecondaryContent
}

/**
 * Dimension configuration for Licensy components.
 */
data class LicensyDimensions(
    val titleTextSize: TextUnit,
    val bodyTextSize: TextUnit,
    val smallTextSize: TextUnit,
    val itemPadding: Dp,
    val cardCornerRadius: Dp,
    val cardElevation: Dp,
    val dividerThickness: Dp
)
