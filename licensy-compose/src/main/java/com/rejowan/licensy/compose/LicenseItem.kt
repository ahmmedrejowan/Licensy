package com.rejowan.licensy.compose

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.rejowan.licensy.LicenseContent
import com.rejowan.licensy.LicensyInteractionMode
import com.rejowan.licensy.LicensyStyle
import com.rejowan.licensy.R

/**
 * Displays a single license item with the specified style.
 */
@Composable
internal fun LicenseItem(
    license: LicenseContent,
    style: LicensyStyle,
    interactionMode: LicensyInteractionMode,
    colors: LicensyColors,
    dimensions: LicensyDimensions,
    onClick: () -> Unit,
    onUrlClick: ((String) -> Unit)?
) {
    when (style) {
        LicensyStyle.STANDARD -> StandardLicenseItem(
            license = license,
            interactionMode = interactionMode,
            colors = colors,
            dimensions = dimensions,
            onClick = onClick,
            onUrlClick = onUrlClick
        )
        LicensyStyle.COMPACT -> CompactLicenseItem(
            license = license,
            colors = colors,
            dimensions = dimensions,
            onClick = onClick,
            onUrlClick = onUrlClick
        )
        LicensyStyle.CARD -> CardLicenseItem(
            license = license,
            colors = colors,
            dimensions = dimensions,
            onClick = onClick,
            onUrlClick = onUrlClick
        )
        LicensyStyle.DETAILED -> DetailedLicenseItem(
            license = license,
            colors = colors,
            dimensions = dimensions,
            onClick = onClick,
            onUrlClick = onUrlClick
        )
    }
}

/**
 * Standard expandable license item.
 */
@Composable
private fun StandardLicenseItem(
    license: LicenseContent,
    interactionMode: LicensyInteractionMode,
    colors: LicensyColors,
    dimensions: LicensyDimensions,
    onClick: () -> Unit,
    onUrlClick: ((String) -> Unit)?
) {
    var isExpanded by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxWidth()) {
        // Main content
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(colors.backgroundColor)
                .clickable {
                    onClick()
                    if (interactionMode == LicensyInteractionMode.EXPAND_INLINE) {
                        isExpanded = !isExpanded
                    }
                }
                .padding(dimensions.itemPadding),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = license.title,
                    color = colors.primaryColor,
                    fontSize = dimensions.titleTextSize,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(4.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "Created by",
                        color = colors.secondaryColor,
                        fontSize = dimensions.smallTextSize
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = license.author,
                        color = colors.primaryColor,
                        fontSize = dimensions.smallTextSize,
                        fontWeight = FontWeight.Medium
                    )
                    license.copyrightYear?.let { year ->
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "© $year",
                            color = colors.secondaryColor,
                            fontSize = dimensions.smallTextSize
                        )
                    }
                }
                Spacer(modifier = Modifier.height(4.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "Licensed under",
                        color = colors.secondaryColor,
                        fontSize = dimensions.smallTextSize
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = license.license.shortName,
                        color = colors.primaryColor,
                        fontSize = dimensions.smallTextSize,
                        fontWeight = FontWeight.Medium
                    )
                }
            }

            if (license.url != null) {
                IconButton(onClick = {
                    onUrlClick?.invoke(license.url!!)
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_licensy_open),
                        contentDescription = "Open link",
                        tint = colors.iconTint,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }

        HorizontalDivider(
            thickness = dimensions.dividerThickness,
            color = colors.dividerColor
        )

        // Expandable content
        AnimatedVisibility(
            visible = isExpanded && interactionMode == LicensyInteractionMode.EXPAND_INLINE,
            enter = expandVertically(),
            exit = shrinkVertically()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(colors.backgroundColorExpanded)
                    .padding(dimensions.itemPadding)
            ) {
                Text(
                    text = license.license.fullName,
                    color = colors.primaryColor,
                    fontSize = dimensions.bodyTextSize,
                    fontWeight = FontWeight.Medium
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = license.license.description,
                    color = colors.secondaryColor,
                    fontSize = dimensions.smallTextSize
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "Full License:",
                        color = colors.secondaryColor,
                        fontSize = dimensions.smallTextSize
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = license.license.url,
                        color = colors.linkColor,
                        fontSize = dimensions.smallTextSize,
                        textDecoration = TextDecoration.Underline,
                        modifier = Modifier.clickable {
                            onUrlClick?.invoke(license.license.url)
                        }
                    )
                }
                HorizontalDivider(
                    modifier = Modifier.padding(top = 12.dp),
                    thickness = dimensions.dividerThickness,
                    color = colors.dividerColor
                )
            }
        }
    }
}

/**
 * Compact single-row license item.
 */
@Composable
private fun CompactLicenseItem(
    license: LicenseContent,
    colors: LicensyColors,
    dimensions: LicensyDimensions,
    onClick: () -> Unit,
    onUrlClick: ((String) -> Unit)?
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(colors.backgroundColor)
                .clickable(onClick = onClick)
                .padding(horizontal = dimensions.itemPadding, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = license.title,
                    color = colors.primaryColor,
                    fontSize = dimensions.bodyTextSize,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = license.author,
                    color = colors.secondaryColor,
                    fontSize = dimensions.smallTextSize,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            // License badge
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(4.dp))
                    .background(colors.dividerColor)
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            ) {
                Text(
                    text = license.license.shortName,
                    color = colors.secondaryColor,
                    fontSize = dimensions.smallTextSize
                )
            }

            if (license.url != null) {
                IconButton(onClick = {
                    onUrlClick?.invoke(license.url!!)
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_licensy_open),
                        contentDescription = "Open link",
                        tint = colors.iconTint,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }
        }

        HorizontalDivider(
            thickness = dimensions.dividerThickness,
            color = colors.dividerColor
        )
    }
}

/**
 * Card-style license item.
 */
@Composable
private fun CardLicenseItem(
    license: LicenseContent,
    colors: LicensyColors,
    dimensions: LicensyDimensions,
    onClick: () -> Unit,
    onUrlClick: ((String) -> Unit)?
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(dimensions.cardCornerRadius),
        colors = CardDefaults.cardColors(containerColor = colors.backgroundColor),
        elevation = CardDefaults.cardElevation(defaultElevation = dimensions.cardElevation)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensions.itemPadding)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = license.title,
                    color = colors.primaryColor,
                    fontSize = dimensions.titleTextSize,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                if (license.url != null) {
                    IconButton(onClick = {
                        onUrlClick?.invoke(license.url!!)
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_licensy_open),
                            contentDescription = "Open link",
                            tint = colors.iconTint,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = license.author,
                    color = colors.secondaryColor,
                    fontSize = dimensions.smallTextSize
                )
                license.copyrightYear?.let { year ->
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "© $year",
                        color = colors.secondaryColor,
                        fontSize = dimensions.smallTextSize
                    )
                }
            }

            HorizontalDivider(
                modifier = Modifier.padding(vertical = 12.dp),
                thickness = dimensions.dividerThickness,
                color = colors.dividerColor
            )

            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(4.dp))
                        .background(colors.dividerColor)
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = license.license.shortName,
                        color = colors.secondaryColor,
                        fontSize = dimensions.smallTextSize
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = license.license.fullName,
                    color = colors.primaryColor,
                    fontSize = dimensions.smallTextSize,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

/**
 * Detailed license item with all info visible.
 */
@Composable
private fun DetailedLicenseItem(
    license: LicenseContent,
    colors: LicensyColors,
    dimensions: LicensyDimensions,
    onClick: () -> Unit,
    onUrlClick: ((String) -> Unit)?
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(colors.backgroundColor)
            .clickable(onClick = onClick)
            .padding(dimensions.itemPadding)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = license.title,
                color = colors.primaryColor,
                fontSize = dimensions.titleTextSize,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(1f)
            )
            if (license.url != null) {
                IconButton(onClick = {
                    onUrlClick?.invoke(license.url!!)
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_licensy_open),
                        contentDescription = "Open link",
                        tint = colors.iconTint,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(6.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "Created by",
                color = colors.secondaryColor,
                fontSize = dimensions.smallTextSize
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = license.author,
                color = colors.primaryColor,
                fontSize = dimensions.smallTextSize,
                fontWeight = FontWeight.Medium
            )
            license.copyrightYear?.let { year ->
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "© $year",
                    color = colors.secondaryColor,
                    fontSize = dimensions.smallTextSize
                )
            }
        }

        HorizontalDivider(
            modifier = Modifier.padding(vertical = 12.dp),
            thickness = dimensions.dividerThickness,
            color = colors.dividerColor
        )

        Text(
            text = license.license.fullName,
            color = colors.primaryColor,
            fontSize = dimensions.bodyTextSize,
            fontWeight = FontWeight.Medium
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = license.license.description,
            color = colors.secondaryColor,
            fontSize = dimensions.smallTextSize
        )

        Spacer(modifier = Modifier.height(10.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "Full License:",
                color = colors.secondaryColor,
                fontSize = dimensions.smallTextSize
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = license.license.url,
                color = colors.linkColor,
                fontSize = dimensions.smallTextSize,
                textDecoration = TextDecoration.Underline,
                modifier = Modifier.clickable {
                    onUrlClick?.invoke(license.license.url)
                }
            )
        }

        if (license.url != null) {
            Spacer(modifier = Modifier.height(6.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "Repository:",
                    color = colors.secondaryColor,
                    fontSize = dimensions.smallTextSize
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = license.url!!,
                    color = colors.linkColor,
                    fontSize = dimensions.smallTextSize,
                    textDecoration = TextDecoration.Underline,
                    modifier = Modifier.clickable {
                        onUrlClick?.invoke(license.url!!)
                    }
                )
            }
        }

        HorizontalDivider(
            modifier = Modifier.padding(top = 16.dp),
            thickness = dimensions.dividerThickness,
            color = colors.dividerColor
        )
    }
}
