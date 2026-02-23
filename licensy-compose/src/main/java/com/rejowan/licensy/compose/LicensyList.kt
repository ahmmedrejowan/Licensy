package com.rejowan.licensy.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.rejowan.licensy.LicenseContent
import com.rejowan.licensy.LicensyInteractionMode
import com.rejowan.licensy.LicensyStyle

/**
 * A Composable that displays a list of open source licenses.
 *
 * @param licenses The list of licenses to display
 * @param modifier Modifier for the list
 * @param style The visual style for license items
 * @param interactionMode How license details are shown when clicked
 * @param colors Color configuration
 * @param dimensions Dimension configuration
 * @param listState LazyList state for scroll control
 * @param onLicenseClick Callback when a license item is clicked
 * @param onUrlClick Callback when a URL is clicked
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LicensyList(
    licenses: List<LicenseContent>,
    modifier: Modifier = Modifier,
    style: LicensyStyle = LicensyStyle.STANDARD,
    interactionMode: LicensyInteractionMode = LicensyInteractionMode.EXPAND_INLINE,
    colors: LicensyColors = LicensyDefaults.colors(),
    dimensions: LicensyDimensions = LicensyDefaults.dimensions(),
    listState: LazyListState = rememberLazyListState(),
    onLicenseClick: ((LicenseContent) -> Unit)? = null,
    onUrlClick: ((String) -> Unit)? = null
) {
    var selectedLicense by remember { mutableStateOf<LicenseContent?>(null) }
    var showDialog by remember { mutableStateOf(false) }
    var showBottomSheet by remember { mutableStateOf(false) }
    val bottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    val handleItemClick: (LicenseContent) -> Unit = { license ->
        onLicenseClick?.invoke(license)
        when (interactionMode) {
            LicensyInteractionMode.DIALOG -> {
                selectedLicense = license
                showDialog = true
            }
            LicensyInteractionMode.BOTTOM_SHEET -> {
                selectedLicense = license
                showBottomSheet = true
            }
            LicensyInteractionMode.EXPAND_INLINE -> {
                // Handled by the item itself
            }
        }
    }

    val contentPadding = when (style) {
        LicensyStyle.CARD -> PaddingValues(horizontal = 12.dp, vertical = 8.dp)
        else -> PaddingValues(0.dp)
    }

    val verticalArrangement = when (style) {
        LicensyStyle.CARD -> Arrangement.spacedBy(8.dp)
        else -> Arrangement.spacedBy(0.dp)
    }

    LazyColumn(
        modifier = modifier.fillMaxWidth(),
        state = listState,
        contentPadding = contentPadding,
        verticalArrangement = verticalArrangement
    ) {
        items(
            items = licenses,
            key = { "${it.title}_${it.author}" }
        ) { license ->
            LicenseItem(
                license = license,
                style = style,
                interactionMode = interactionMode,
                colors = colors,
                dimensions = dimensions,
                onClick = { handleItemClick(license) },
                onUrlClick = onUrlClick
            )
        }
    }

    // Dialog
    if (showDialog && selectedLicense != null) {
        LicenseDetailDialog(
            license = selectedLicense!!,
            colors = colors,
            dimensions = dimensions,
            onDismiss = {
                showDialog = false
                selectedLicense = null
            },
            onUrlClick = onUrlClick
        )
    }

    // Bottom Sheet
    if (showBottomSheet && selectedLicense != null) {
        ModalBottomSheet(
            onDismissRequest = {
                showBottomSheet = false
                selectedLicense = null
            },
            sheetState = bottomSheetState,
            containerColor = colors.backgroundColor
        ) {
            LicenseDetailContent(
                license = selectedLicense!!,
                colors = colors,
                dimensions = dimensions,
                onUrlClick = onUrlClick
            )
        }
    }
}
