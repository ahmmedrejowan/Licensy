package com.rejowan.licensysample

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rejowan.licensy.LicensyInteractionMode
import com.rejowan.licensy.LicensyStyle
import com.rejowan.licensy.compose.LicensyColors
import com.rejowan.licensy.compose.LicensyDefaults
import com.rejowan.licensy.compose.LicensyList

class ComposeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LicensyComposeDemo(
                        onUrlClick = { url ->
                            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
                        },
                        onBack = { finish() }
                    )
                }
            }
        }
    }
}

data class ThemeOption(
    val name: String,
    val colors: List<Color>,
    val licensyColors: LicensyColors,
    val isDark: Boolean = false
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LicensyComposeDemo(
    onUrlClick: (String) -> Unit,
    onBack: () -> Unit
) {
    var selectedStyle by remember { mutableStateOf(LicensyStyle.STANDARD) }
    var selectedMode by remember { mutableStateOf(LicensyInteractionMode.EXPAND_INLINE) }
    var selectedTheme by remember { mutableStateOf(0) }

    val themes = listOf(
        ThemeOption(
            name = "Default",
            colors = listOf(Color(0xFF1A1A1A), Color(0xFF1976D2), Color.White),
            licensyColors = LicensyColors(
                primaryColor = Color(0xFF1A1A1A),
                secondaryColor = Color(0xFF666666),
                linkColor = Color(0xFF1976D2),
                backgroundColor = Color.White,
                backgroundColorExpanded = Color(0xFFF8F8F8),
                dividerColor = Color(0xFFE0E0E0),
                iconTint = Color(0xFF666666)
            )
        ),
        ThemeOption(
            name = "Purple",
            colors = listOf(Color(0xFF4A148C), Color(0xFF7C4DFF), Color(0xFFF3E5F5)),
            licensyColors = LicensyColors(
                primaryColor = Color(0xFF4A148C),
                secondaryColor = Color(0xFF7B1FA2),
                linkColor = Color(0xFF7C4DFF),
                backgroundColor = Color(0xFFF3E5F5),
                backgroundColorExpanded = Color(0xFFE1BEE7),
                dividerColor = Color(0xFFCE93D8),
                iconTint = Color(0xFF7B1FA2)
            )
        ),
        ThemeOption(
            name = "Teal",
            colors = listOf(Color(0xFF00695C), Color(0xFF00BFA5), Color(0xFFE0F2F1)),
            licensyColors = LicensyColors(
                primaryColor = Color(0xFF00695C),
                secondaryColor = Color(0xFF00897B),
                linkColor = Color(0xFF00BFA5),
                backgroundColor = Color(0xFFE0F2F1),
                backgroundColorExpanded = Color(0xFFB2DFDB),
                dividerColor = Color(0xFF80CBC4),
                iconTint = Color(0xFF00897B)
            )
        ),
        ThemeOption(
            name = "Dark",
            colors = listOf(Color(0xFFEEEEEE), Color(0xFF64B5F6), Color(0xFF212121)),
            licensyColors = LicensyColors(
                primaryColor = Color(0xFFEEEEEE),
                secondaryColor = Color(0xFFBDBDBD),
                linkColor = Color(0xFF64B5F6),
                backgroundColor = Color(0xFF212121),
                backgroundColorExpanded = Color(0xFF303030),
                dividerColor = Color(0xFF424242),
                iconTint = Color(0xFFBDBDBD)
            ),
            isDark = true
        )
    )

    val currentTheme = themes[selectedTheme]
    val backgroundColor by animateColorAsState(
        targetValue = if (currentTheme.isDark) Color(0xFF121212) else Color(0xFFFAFAFA),
        label = "background"
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Compose Demo",
                        fontWeight = FontWeight.SemiBold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF6C63FF),
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(backgroundColor)
        ) {
            // Controls Card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = if (currentTheme.isDark) Color(0xFF1E1E1E) else Color.White
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    // Style Section
                    SectionTitle(
                        title = "STYLE",
                        isDark = currentTheme.isDark
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(LicensyStyle.entries) { style ->
                            SelectableChip(
                                text = style.name.lowercase().replaceFirstChar { it.uppercase() },
                                selected = selectedStyle == style,
                                onClick = { selectedStyle = style }
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    // Mode Section
                    SectionTitle(
                        title = "INTERACTION",
                        isDark = currentTheme.isDark
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(LicensyInteractionMode.entries) { mode ->
                            val displayName = when (mode) {
                                LicensyInteractionMode.EXPAND_INLINE -> "Expand"
                                LicensyInteractionMode.DIALOG -> "Dialog"
                                LicensyInteractionMode.BOTTOM_SHEET -> "Sheet"
                            }
                            SelectableChip(
                                text = displayName,
                                selected = selectedMode == mode,
                                onClick = { selectedMode = mode }
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    // Theme Section
                    SectionTitle(
                        title = "THEME",
                        isDark = currentTheme.isDark
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(themes.size) { index ->
                            ThemeSelector(
                                theme = themes[index],
                                selected = selectedTheme == index,
                                onClick = { selectedTheme = index }
                            )
                        }
                    }
                }
            }

            // License List Card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 16.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = currentTheme.licensyColors.backgroundColor
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                LicensyList(
                    licenses = Credit.listOfLicenses,
                    modifier = Modifier.fillMaxSize(),
                    style = selectedStyle,
                    interactionMode = selectedMode,
                    colors = currentTheme.licensyColors,
                    onUrlClick = onUrlClick
                )
            }
        }
    }
}

@Composable
private fun SectionTitle(
    title: String,
    isDark: Boolean
) {
    Text(
        text = title,
        fontSize = 11.sp,
        fontWeight = FontWeight.SemiBold,
        letterSpacing = 1.sp,
        color = if (isDark) Color(0xFF888888) else Color(0xFF888888)
    )
}

@Composable
private fun SelectableChip(
    text: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    val backgroundColor by animateColorAsState(
        targetValue = if (selected) Color(0xFF6C63FF) else Color(0xFFF0F0F0),
        label = "chipBg"
    )
    val textColor by animateColorAsState(
        targetValue = if (selected) Color.White else Color(0xFF666666),
        label = "chipText"
    )

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(backgroundColor)
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Text(
            text = text,
            fontSize = 13.sp,
            fontWeight = if (selected) FontWeight.SemiBold else FontWeight.Normal,
            color = textColor
        )
    }
}

@Composable
private fun ThemeSelector(
    theme: ThemeOption,
    selected: Boolean,
    onClick: () -> Unit
) {
    val borderColor by animateColorAsState(
        targetValue = if (selected) Color(0xFF6C63FF) else Color.Transparent,
        label = "border"
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable(onClick = onClick)
    ) {
        Box(
            modifier = Modifier
                .border(
                    width = 2.dp,
                    color = borderColor,
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(3.dp)
        ) {
            Row(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color(0xFFF5F5F5))
                    .padding(6.dp),
                horizontalArrangement = Arrangement.spacedBy(3.dp)
            ) {
                theme.colors.forEach { color ->
                    Box(
                        modifier = Modifier
                            .size(20.dp)
                            .clip(RoundedCornerShape(4.dp))
                            .background(color)
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = theme.name,
            fontSize = 11.sp,
            color = if (selected) Color(0xFF6C63FF) else Color(0xFF888888),
            fontWeight = if (selected) FontWeight.SemiBold else FontWeight.Normal
        )
    }
}
