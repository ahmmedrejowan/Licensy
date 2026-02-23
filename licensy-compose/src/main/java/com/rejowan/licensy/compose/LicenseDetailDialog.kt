package com.rejowan.licensy.compose

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.rejowan.licensy.LicenseContent

/**
 * Dialog that displays full license details with improved UI.
 */
@Composable
internal fun LicenseDetailDialog(
    license: LicenseContent,
    colors: LicensyColors,
    dimensions: LicensyDimensions,
    onDismiss: () -> Unit,
    onUrlClick: ((String) -> Unit)?
) {
    Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = colors.backgroundColor),
            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                // Header with close button
                DialogHeader(
                    title = license.title,
                    colors = colors,
                    onClose = onDismiss
                )

                // Content
                LicenseDetailContent(
                    license = license,
                    colors = colors,
                    dimensions = dimensions,
                    onUrlClick = onUrlClick,
                    showTitle = false
                )
            }
        }
    }
}

/**
 * Dialog header with title and close button.
 */
@Composable
private fun DialogHeader(
    title: String,
    colors: LicensyColors,
    onClose: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(colors.backgroundColorExpanded)
            .padding(start = 16.dp, end = 4.dp, top = 4.dp, bottom = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            color = colors.primaryColor,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.weight(1f)
        )

        IconButton(
            onClick = onClose,
            modifier = Modifier.size(36.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.Close,
                contentDescription = "Close",
                tint = colors.secondaryColor,
                modifier = Modifier.size(18.dp)
            )
        }
    }
}

/**
 * Shared content for both Dialog and BottomSheet with compact layout.
 */
@Composable
internal fun LicenseDetailContent(
    license: LicenseContent,
    colors: LicensyColors,
    dimensions: LicensyDimensions,
    onUrlClick: ((String) -> Unit)?,
    showTitle: Boolean = true
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        // Title (only for bottom sheet)
        if (showTitle) {
            Text(
                text = license.title,
                color = colors.primaryColor,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(10.dp))
        }

        // Author & Copyright in one row
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "by ${license.author}",
                color = colors.secondaryColor,
                fontSize = 13.sp
            )
            license.copyrightYear?.let { year ->
                Text(
                    text = "© $year",
                    color = colors.secondaryColor,
                    fontSize = 13.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // License Card
        LicenseInfoCard(
            license = license,
            colors = colors
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Action Buttons
        ActionButtonsRow(
            license = license,
            colors = colors,
            onUrlClick = onUrlClick
        )
    }
}

/**
 * Card displaying license type and description.
 */
@Composable
private fun LicenseInfoCard(
    license: LicenseContent,
    colors: LicensyColors
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        color = colors.backgroundColorExpanded
    ) {
        Column(
            modifier = Modifier.padding(12.dp)
        ) {
            // Badge and Full Name in one row
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                LicenseBadge(
                    licenseName = license.license.shortName,
                    colors = colors
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = license.license.fullName,
                    color = colors.primaryColor,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Description
            Text(
                text = license.license.description,
                color = colors.secondaryColor,
                fontSize = 12.sp,
                lineHeight = 18.sp
            )
        }
    }
}

/**
 * License type badge.
 */
@Composable
private fun LicenseBadge(
    licenseName: String,
    colors: LicensyColors
) {
    Surface(
        shape = RoundedCornerShape(6.dp),
        color = colors.linkColor.copy(alpha = 0.15f)
    ) {
        Text(
            text = licenseName,
            color = colors.linkColor,
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
        )
    }
}

/**
 * Row of action buttons for viewing license and repository.
 */
@Composable
private fun ActionButtonsRow(
    license: LicenseContent,
    colors: LicensyColors,
    onUrlClick: ((String) -> Unit)?
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        // View License Button
        ActionButton(
            text = "License",
            icon = Icons.AutoMirrored.Filled.ArrowForward,
            colors = colors,
            isPrimary = true,
            onClick = { onUrlClick?.invoke(license.license.url) },
            modifier = Modifier.weight(1f)
        )

        // View Repository Button (if available)
        license.url?.let { repoUrl ->
            ActionButton(
                text = "Repository",
                icon = Icons.AutoMirrored.Filled.ArrowForward,
                colors = colors,
                isPrimary = false,
                onClick = { onUrlClick?.invoke(repoUrl) },
                modifier = Modifier.weight(1f)
            )
        }
    }
}

/**
 * Styled action button.
 */
@Composable
private fun ActionButton(
    text: String,
    icon: ImageVector,
    colors: LicensyColors,
    isPrimary: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val backgroundColor = if (isPrimary) colors.linkColor else colors.backgroundColorExpanded
    val contentColor = if (isPrimary) Color.White else colors.primaryColor

    Surface(
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(10.dp),
        color = backgroundColor
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 10.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = text,
                color = contentColor,
                fontSize = 13.sp,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.width(4.dp))
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = contentColor,
                modifier = Modifier.size(14.dp)
            )
        }
    }
}

/**
 * Content for bottom sheet with drag handle.
 */
@Composable
internal fun BottomSheetContent(
    license: LicenseContent,
    colors: LicensyColors,
    dimensions: LicensyDimensions,
    onUrlClick: ((String) -> Unit)?
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        // Drag Handle
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .width(36.dp)
                    .height(4.dp)
                    .clip(RoundedCornerShape(2.dp))
                    .background(colors.dividerColor)
            )
        }

        // Content
        LicenseDetailContent(
            license = license,
            colors = colors,
            dimensions = dimensions,
            onUrlClick = onUrlClick,
            showTitle = true
        )

        // Bottom padding for navigation bar
        Spacer(modifier = Modifier.height(8.dp))
    }
}
