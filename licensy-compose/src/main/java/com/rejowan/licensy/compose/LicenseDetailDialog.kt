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
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
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
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = colors.backgroundColor),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
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
            .padding(start = 20.dp, end = 8.dp, top = 8.dp, bottom = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            color = colors.primaryColor,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.weight(1f)
        )

        IconButton(
            onClick = onClose,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
        ) {
            Icon(
                imageVector = Icons.Filled.Close,
                contentDescription = "Close",
                tint = colors.secondaryColor,
                modifier = Modifier.size(20.dp)
            )
        }
    }
}

/**
 * Shared content for both Dialog and BottomSheet with improved layout.
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
            .padding(20.dp)
    ) {
        // Title (only for bottom sheet)
        if (showTitle) {
            Text(
                text = license.title,
                color = colors.primaryColor,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(16.dp))
        }

        // Author Section
        InfoRow(
            icon = Icons.Filled.Person,
            label = "Author",
            value = license.author,
            colors = colors
        )

        // Copyright Year
        license.copyrightYear?.let { year ->
            Spacer(modifier = Modifier.height(12.dp))
            InfoRow(
                icon = Icons.Filled.DateRange,
                label = "Copyright",
                value = "© $year",
                colors = colors
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        // License Card
        LicenseInfoCard(
            license = license,
            colors = colors
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Action Buttons
        ActionButtonsRow(
            license = license,
            colors = colors,
            onUrlClick = onUrlClick
        )
    }
}

/**
 * Row displaying an icon, label, and value.
 */
@Composable
private fun InfoRow(
    icon: ImageVector,
    label: String,
    value: String,
    colors: LicensyColors
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .size(36.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(colors.backgroundColorExpanded),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = colors.iconTint,
                modifier = Modifier.size(18.dp)
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        Column {
            Text(
                text = label,
                color = colors.secondaryColor,
                fontSize = 11.sp,
                fontWeight = FontWeight.Medium,
                letterSpacing = 0.5.sp
            )
            Text(
                text = value,
                color = colors.primaryColor,
                fontSize = 15.sp,
                fontWeight = FontWeight.Medium
            )
        }
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
        shape = RoundedCornerShape(16.dp),
        color = colors.backgroundColorExpanded
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            // License Badge
            LicenseBadge(
                licenseName = license.license.shortName,
                colors = colors
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Full License Name
            Text(
                text = license.license.fullName,
                color = colors.primaryColor,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Description
            Text(
                text = license.license.description,
                color = colors.secondaryColor,
                fontSize = 13.sp,
                lineHeight = 20.sp
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
        shape = RoundedCornerShape(8.dp),
        color = colors.linkColor.copy(alpha = 0.15f)
    ) {
        Text(
            text = licenseName,
            color = colors.linkColor,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 0.5.sp,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
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
    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        // View License Button
        ActionButton(
            text = "View Full License",
            icon = Icons.AutoMirrored.Filled.ArrowForward,
            colors = colors,
            isPrimary = true,
            onClick = { onUrlClick?.invoke(license.license.url) }
        )

        // View Repository Button (if available)
        license.url?.let { repoUrl ->
            ActionButton(
                text = "View Repository",
                icon = Icons.AutoMirrored.Filled.ArrowForward,
                colors = colors,
                isPrimary = false,
                onClick = { onUrlClick?.invoke(repoUrl) }
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
    onClick: () -> Unit
) {
    val backgroundColor = if (isPrimary) colors.linkColor else colors.backgroundColorExpanded
    val contentColor = if (isPrimary) Color.White else colors.primaryColor

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(12.dp),
        color = backgroundColor
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 14.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = text,
                color = contentColor,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.width(8.dp))
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = contentColor,
                modifier = Modifier.size(16.dp)
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
                .padding(vertical = 12.dp),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .width(40.dp)
                    .height(4.dp)
                    .clip(RoundedCornerShape(2.dp))
                    .background(colors.dividerColor)
            )
        }

        // Title Header
        Text(
            text = license.title,
            color = colors.primaryColor,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 20.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        HorizontalDivider(
            color = colors.dividerColor,
            thickness = 1.dp,
            modifier = Modifier.padding(horizontal = 20.dp)
        )

        // Content
        LicenseDetailContent(
            license = license,
            colors = colors,
            dimensions = dimensions,
            onUrlClick = onUrlClick,
            showTitle = false
        )

        // Bottom padding for navigation bar
        Spacer(modifier = Modifier.height(24.dp))
    }
}
