package com.rejowan.licensy.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.rejowan.licensy.LicenseContent

/**
 * Dialog that displays full license details.
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
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = colors.backgroundColor)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                LicenseDetailContent(
                    license = license,
                    colors = colors,
                    dimensions = dimensions,
                    onUrlClick = onUrlClick
                )

                Spacer(modifier = Modifier.height(16.dp))

                TextButton(
                    onClick = onDismiss,
                    modifier = Modifier.align(Alignment.End)
                ) {
                    Text(
                        text = "Close",
                        color = colors.linkColor
                    )
                }
            }
        }
    }
}

/**
 * Shared content for both Dialog and BottomSheet.
 */
@Composable
internal fun LicenseDetailContent(
    license: LicenseContent,
    colors: LicensyColors,
    dimensions: LicensyDimensions,
    onUrlClick: ((String) -> Unit)?
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 4.dp, vertical = 16.dp)
    ) {
        // Title
        Text(
            text = license.title,
            color = colors.primaryColor,
            fontSize = dimensions.titleTextSize,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Author
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
        }

        // Copyright
        license.copyrightYear?.let { year ->
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "© $year",
                color = colors.secondaryColor,
                fontSize = dimensions.smallTextSize
            )
        }

        HorizontalDivider(
            modifier = Modifier.padding(vertical = 16.dp),
            thickness = dimensions.dividerThickness,
            color = colors.dividerColor
        )

        // License name
        Text(
            text = license.license.fullName,
            color = colors.primaryColor,
            fontSize = dimensions.bodyTextSize,
            fontWeight = FontWeight.Medium
        )

        Spacer(modifier = Modifier.height(12.dp))

        // License description
        Text(
            text = license.license.description,
            color = colors.secondaryColor,
            fontSize = dimensions.smallTextSize
        )

        Spacer(modifier = Modifier.height(16.dp))

        // License URL
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

        // Repository URL
        if (license.url != null) {
            Spacer(modifier = Modifier.height(8.dp))
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
    }
}
