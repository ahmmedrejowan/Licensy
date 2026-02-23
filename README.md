<p align="center"><img src="https://raw.githubusercontent.com/ahmmedrejowan/Licensy/master/files/logo.svg" width="100px" align="center"/></p>
<h1 align="center">Licensy</h1>
<h3 align="center">A flexible Android library for displaying open source licenses</h3>

<p align="center">
<a href="https://www.android.com"><img src="https://img.shields.io/badge/platform-Android-yellow.svg" alt="platform"></a>
<a href="https://android-arsenal.com/api?level=24"><img src="https://img.shields.io/badge/API-24%2B-brightgreen.svg?style=flat" alt="API"></a>
<a href="https://central.sonatype.com/artifact/com.rejowan/licensy"><img src="https://img.shields.io/maven-central/v/com.rejowan/licensy" alt="Maven Central"></a>
<a href="https://github.com/ahmmedrejowan/Licensy/blob/master/LICENSE"><img src="https://img.shields.io/github/license/ahmmedrejowan/Licensy" alt="GitHub license"></a>
</p>

<p align="center">
<a href="https://github.com/ahmmedrejowan/Licensy/issues"><img src="https://img.shields.io/github/issues/ahmmedrejowan/Licensy" alt="GitHub issues"></a>
<a href="https://github.com/ahmmedrejowan/Licensy/network"><img src="https://img.shields.io/github/forks/ahmmedrejowan/Licensy" alt="GitHub forks"></a>
<a href="https://github.com/ahmmedrejowan/Licensy/stargazers"><img src="https://img.shields.io/github/stars/ahmmedrejowan/Licensy" alt="GitHub stars"></a>
<a href="https://github.com/ahmmedrejowan/Licensy/graphs/contributors"><img src="https://img.shields.io/github/contributors/ahmmedrejowan/Licensy" alt="GitHub contributors"></a>
</p>

## Table of Contents

- [What's New in 1.0](#whats-new-in-10)
- [Features](#features)
- [Demo](#demo)
- [Installation](#installation)
- [Usage](#usage)
- [View Styles](#view-styles)
- [Interaction Modes](#interaction-modes)
- [Customization](#customization)
- [Attributes](#attributes)
- [Migration from 0.x](#migration-from-0x)
- [Contribute](#contribute)
- [License](#license)

## What's New in 1.0

Version 1.0 is a complete revamp with new features and a cleaner API:

- **4 View Styles**: Standard, Compact, Card, and Detailed
- **3 Interaction Modes**: Expand Inline, Dialog, and Bottom Sheet
- **Unified API**: Single `LicensyView` with configurable styles and modes
- **Custom Licenses**: Support for custom license definitions
- **Material 3**: Updated design with ripple effects and modern styling
- **Maven Central**: Now published on Maven Central

## Features

- Lightweight and fast
- Multiple visual styles to match your app's design
- Flexible interaction modes (inline, dialog, bottom sheet)
- Highly customizable colors, text sizes, and more
- Support for custom licenses
- Works with both Kotlin and Java

## Demo

| Standard | Compact | Card | Detailed |
|----------|---------|------|----------|
| ![Standard](https://raw.githubusercontent.com/ahmmedrejowan/Licensy/master/files/shot2.png) | ![Compact](https://raw.githubusercontent.com/ahmmedrejowan/Licensy/master/files/shot1.png) | ![Card](https://raw.githubusercontent.com/ahmmedrejowan/Licensy/master/files/shot3.png) | ![Detailed](https://raw.githubusercontent.com/ahmmedrejowan/Licensy/master/files/shot4.png) |

## Installation

Add the dependency to your module's `build.gradle.kts`:

```kotlin
dependencies {
    implementation("com.rejowan:licensy:1.0")
}
```

<details>
<summary>Groovy</summary>

```groovy
dependencies {
    implementation 'com.rejowan:licensy:1.0'
}
```
</details>

<details>
<summary>Using JitPack (legacy)</summary>

Add JitPack repository to `settings.gradle.kts`:
```kotlin
dependencyResolutionManagement {
    repositories {
        maven { url = uri("https://jitpack.io") }
    }
}
```

Then add dependency:
```kotlin
implementation("com.github.ahmmedrejowan:Licensy:0.2")
```
</details>

## Usage

### Basic Setup

**XML Layout:**
```xml
<com.rejowan.licensy.LicensyView
    android:id="@+id/licensyView"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    app:lv_style="standard"
    app:lv_interaction_mode="expand_inline" />
```

**Kotlin:**
```kotlin
val licenses = listOf(
    LicenseContent(
        title = "Retrofit",
        author = "Square, Inc.",
        license = Licenses.APACHE_2_0,
        copyrightYear = "2013",
        url = "https://github.com/square/retrofit"
    ),
    LicenseContent(
        title = "OkHttp",
        author = "Square, Inc.",
        license = Licenses.APACHE_2_0,
        url = "https://github.com/square/okhttp"
    )
)

licensyView.setLicenses(licenses)
```

### With Style and Interaction Mode

```kotlin
licensyView.apply {
    style = LicensyStyle.CARD
    interactionMode = LicensyInteractionMode.BOTTOM_SHEET
    cardCornerRadius = 16f
    cardElevation = 8f
    setLicenses(licenses)
}
```

### Click Listener

```kotlin
licensyView.onLicenseClickListener = OnLicenseClickListener { license ->
    Log.d("Licensy", "Clicked: ${license.title}")
}
```

## View Styles

| Style | Description |
|-------|-------------|
| `STANDARD` | Expandable list with license details (default) |
| `COMPACT` | Minimal single-row layout with title, author, and badge |
| `CARD` | Material CardView with elevation and rounded corners |
| `DETAILED` | Full details visible without expansion |

```kotlin
// Set via code
licensyView.style = LicensyStyle.CARD

// Set via XML
app:lv_style="card"
```

## Interaction Modes

| Mode | Description |
|------|-------------|
| `EXPAND_INLINE` | Expands license details within the list (default) |
| `DIALOG` | Opens a centered dialog with full license details |
| `BOTTOM_SHEET` | Opens a bottom sheet with full license details |

```kotlin
// Set via code
licensyView.interactionMode = LicensyInteractionMode.DIALOG

// Set via XML
app:lv_interaction_mode="dialog"
```

## Customization

### Using Properties

```kotlin
licensyView.apply {
    lvPrimaryColor = Color.BLACK
    lvSecondaryColor = Color.GRAY
    lvLinkColor = Color.BLUE
    lvBackgroundColor = Color.WHITE
    lvDividerColor = Color.LTGRAY
}
```

### Using LicensyCustomization

```kotlin
val customization = LicensyCustomization(
    lvPrimaryColor = Color.parseColor("#121211"),
    lvSecondaryColor = Color.parseColor("#444444"),
    lvLinkColor = Color.parseColor("#0077cc"),
    lvBackgroundColor = Color.WHITE,
    lvBackgroundColorExpand = Color.parseColor("#f8f8f8"),
    lvDividerColor = Color.parseColor("#e0e0e0")
)

licensyView.setCustomization(customization)
```

### Custom Licenses

```kotlin
val customLicense = License(
    shortName = "Custom",
    fullName = "My Custom License",
    description = "This is a custom license.",
    url = "https://example.com/license"
)

val license = LicenseContent(
    title = "My Library",
    author = "Me",
    license = customLicense
)
```

## Attributes

### XML Attributes

| Attribute | Format | Description |
|-----------|--------|-------------|
| `lv_style` | enum | View style: `standard`, `compact`, `card`, `detailed` |
| `lv_interaction_mode` | enum | Interaction: `expand_inline`, `dialog`, `bottom_sheet` |
| `lv_card_corner_radius` | dimension | Corner radius for card style |
| `lv_card_elevation` | dimension | Elevation for card style |
| `lv_text_color_primary` | color | Primary text color |
| `lv_text_color_secondary` | color | Secondary text color |
| `lv_text_color_link` | color | Link text color |
| `lv_text_size_title` | dimension | Title text size |
| `lv_background_color` | color | Background color |
| `lv_background_color_expand` | color | Expanded section background |
| `lv_open_image` | reference | Icon for open link button |
| `lv_image_tint` | color | Tint for icon |
| `lv_divider_color` | color | Divider color |

### Available Licenses

The library includes these pre-defined licenses:

- `Licenses.APACHE_2_0`
- `Licenses.MIT`
- `Licenses.BSD_3_CLAUSE`
- `Licenses.BSD_2_CLAUSE`
- `Licenses.GPL_3_0`
- `Licenses.GPL_2_0`
- `Licenses.LGPL_3_0`
- `Licenses.LGPL_2_1`
- `Licenses.MPL_2_0`
- `Licenses.ISC`
- `Licenses.UNLICENSE`
- `Licenses.WTFPL`
- And more...

## Migration from 0.x

### Breaking Changes

- `LicensyDialog` class removed - use `interactionMode = DIALOG` instead
- `LicensyBottomSheet` class removed - use `interactionMode = BOTTOM_SHEET` instead
- `OnDialogListener` removed

### Before (0.x)

```kotlin
// Dialog
val dialog = LicensyDialog(this)
dialog.setLicenses(licenses)
dialog.show()

// BottomSheet
val bottomSheet = LicensyBottomSheet(this)
bottomSheet.setLicenses(licenses)
bottomSheet.show()
```

### After (1.0)

```kotlin
// Dialog mode
licensyView.interactionMode = LicensyInteractionMode.DIALOG
licensyView.setLicenses(licenses)

// BottomSheet mode
licensyView.interactionMode = LicensyInteractionMode.BOTTOM_SHEET
licensyView.setLicenses(licenses)
```

## Contribute

Please fork this repository and contribute back using [pull requests](https://github.com/ahmmedrejowan/Licensy/pulls).

Any contributions, large or small, major features, bug fixes, are welcomed and appreciated.

If this project helps you, give a Star to the Repo.

## License

[Apache License 2.0](http://www.apache.org/licenses/LICENSE-2.0.html)

```
Copyright 2024 ahmmedrejowan

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
