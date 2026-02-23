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
- [Quick Start](#quick-start)
- [View Styles](#view-styles)
- [Interaction Modes](#interaction-modes)
- [Customization](#customization)
- [Available Licenses](#available-licenses)
- [Custom Licenses](#custom-licenses)
- [XML Attributes](#xml-attributes)
- [API Reference](#api-reference)
- [Migration from 0.x](#migration-from-0x)
- [Contribute](#contribute)
- [License](#license)

## What's New in 1.0

Version 1.0 is a complete revamp with powerful new features:

| Feature | Description |
|---------|-------------|
| **4 View Styles** | Standard, Compact, Card, and Detailed layouts |
| **3 Interaction Modes** | Expand Inline, Dialog, and Bottom Sheet |
| **Unified API** | Single `LicensyView` replaces separate Dialog/BottomSheet classes |
| **Custom Licenses** | Define your own license types |
| **Material 3** | Modern styling with ripple effects |
| **Maven Central** | Official Maven Central distribution |

## Features

- Lightweight and performant
- 4 visual styles to match your app's design
- 3 interaction modes for different UX needs
- Highly customizable (colors, text sizes, icons)
- Built-in support for 15+ common licenses
- Support for custom license definitions
- Dark mode compatible
- Works with both Kotlin and Java

## Demo

| Shots                                                                                           | Shots                                                                                           | Shots                                                                                           |
|-------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------|
| ![Screenshot 1](https://raw.githubusercontent.com/ahmmedrejowan/Licensy/master/files/shot1.webp) | ![Screenshot 2](https://raw.githubusercontent.com/ahmmedrejowan/Licensy/master/files/shot2.webp) | ![Screenshot 3](https://raw.githubusercontent.com/ahmmedrejowan/Licensy/master/files/shot3.webp) |
| ![Screenshot 4](https://raw.githubusercontent.com/ahmmedrejowan/Licensy/master/files/shot4.webp) | ![Screenshot 5](https://raw.githubusercontent.com/ahmmedrejowan/Licensy/master/files/shot5.webp) | ![Screenshot 6](https://raw.githubusercontent.com/ahmmedrejowan/Licensy/master/files/shot6.webp) |


## Installation

### Gradle (Kotlin DSL)

Add to your module's `build.gradle.kts`:

```kotlin
dependencies {
    implementation("com.rejowan:licensy:1.0")
}
```

### Gradle (Groovy)

```groovy
dependencies {
    implementation 'com.rejowan:licensy:1.0'
}
```

### Version Catalog

Add to `libs.versions.toml`:

```toml
[versions]
licensy = "1.0"

[libraries]
licensy = { group = "com.rejowan", name = "licensy", version.ref = "licensy" }
```

Then in `build.gradle.kts`:

```kotlin
dependencies {
    implementation(libs.licensy)
}
```

<details>
<summary><b>JitPack (Legacy v0.x)</b></summary>

For the older 0.x versions, add JitPack repository to `settings.gradle.kts`:

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

## Quick Start

### 1. Add LicensyView to your layout

```xml
<com.rejowan.licensy.LicensyView
    android:id="@+id/licensyView"
    android:layout_width="match_parent"
    android:layout_height="match_parent" />
```

### 2. Set up licenses in your Activity/Fragment

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
    ),
    LicenseContent(
        title = "Gson",
        author = "Google",
        license = Licenses.APACHE_2_0,
        copyrightYear = "2008",
        url = "https://github.com/google/gson"
    )
)

binding.licensyView.setLicenses(licenses)
```

**Java:**

```java
List<LicenseContent> licenses = Arrays.asList(
    new LicenseContent(
        "Retrofit",
        "Square, Inc.",
        Licenses.INSTANCE.getAPACHE_2_0(),
        "2013",
        "https://github.com/square/retrofit"
    ),
    new LicenseContent(
        "OkHttp",
        "Square, Inc.",
        Licenses.INSTANCE.getAPACHE_2_0(),
        null,
        "https://github.com/square/okhttp"
    )
);

binding.licensyView.setLicenses(licenses);
```

## View Styles

Choose from 4 different visual styles:

| Style | Description | Best For |
|-------|-------------|----------|
| `STANDARD` | Expandable list with inline details | Default, feature-rich |
| `COMPACT` | Single-row with title, author, badge | Long lists, minimal UI |
| `CARD` | Material CardView with elevation | Modern, card-based designs |
| `DETAILED` | All info visible, no expansion needed | Full transparency |

### Setting Style

**Via XML:**

```xml
<com.rejowan.licensy.LicensyView
    android:id="@+id/licensyView"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    app:lv_style="card" />
```

**Via Code (Kotlin):**

```kotlin
licensyView.style = LicensyStyle.CARD

// For card style, customize corner radius and elevation
licensyView.cardCornerRadius = 16f  // in pixels
licensyView.cardElevation = 8f      // in pixels
```

**Via Code (Java):**

```java
licensyView.setStyle(LicensyStyle.CARD);
licensyView.setCardCornerRadius(16f);
licensyView.setCardElevation(8f);
```

## Interaction Modes

Choose how license details are displayed when tapped:

| Mode | Description | Best For |
|------|-------------|----------|
| `EXPAND_INLINE` | Expands within the list | Quick preview |
| `DIALOG` | Opens centered dialog | Focused reading |
| `BOTTOM_SHEET` | Opens bottom sheet | Mobile-friendly |

### Setting Interaction Mode

**Via XML:**

```xml
<com.rejowan.licensy.LicensyView
    android:id="@+id/licensyView"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    app:lv_style="compact"
    app:lv_interaction_mode="bottom_sheet" />
```

**Via Code (Kotlin):**

```kotlin
licensyView.interactionMode = LicensyInteractionMode.BOTTOM_SHEET
```

### Combining Style + Mode

Any style works with any interaction mode:

```kotlin
licensyView.apply {
    style = LicensyStyle.COMPACT
    interactionMode = LicensyInteractionMode.DIALOG
    setLicenses(licenses)
}
```

### Click Listener

Listen for item clicks (works with all modes):

```kotlin
licensyView.onLicenseClickListener = OnLicenseClickListener { license ->
    Log.d("Licensy", "Clicked: ${license.title}")
}
```

## Customization

### Individual Properties

```kotlin
licensyView.apply {
    // Text colors
    lvPrimaryColor = Color.BLACK
    lvSecondaryColor = Color.GRAY
    lvLinkColor = Color.BLUE

    // Background colors
    lvBackgroundColor = Color.WHITE
    lvBackgroundColorExpand = Color.parseColor("#F5F5F5")

    // Other
    lvDividerColor = Color.LTGRAY
    lvTitleTextSize = 18f  // in sp
    imageTint = Color.GRAY
}
```

### Using LicensyCustomization Object

Apply all customizations at once:

```kotlin
val customization = LicensyCustomization(
    lvPrimaryColor = Color.parseColor("#1A1A1A"),
    lvSecondaryColor = Color.parseColor("#666666"),
    lvLinkColor = Color.parseColor("#2196F3"),
    lvTitleTextSize = 16f,
    lvBackgroundColor = Color.WHITE,
    lvBackgroundColorExpand = Color.parseColor("#FAFAFA"),
    lvOpenImage = R.drawable.ic_open_link,
    imageTint = Color.parseColor("#666666"),
    lvDividerColor = Color.parseColor("#E0E0E0")
)

licensyView.setCustomization(customization)
```

### Dark Mode Example

```kotlin
val darkCustomization = LicensyCustomization(
    lvPrimaryColor = Color.WHITE,
    lvSecondaryColor = Color.parseColor("#B0B0B0"),
    lvLinkColor = Color.parseColor("#64B5F6"),
    lvBackgroundColor = Color.parseColor("#121212"),
    lvBackgroundColorExpand = Color.parseColor("#1E1E1E"),
    lvDividerColor = Color.parseColor("#333333")
)

licensyView.setCustomization(darkCustomization)
```

## Available Licenses

The library includes these pre-defined licenses:

| License | Constant |
|---------|----------|
| Apache License 2.0 | `Licenses.APACHE_2_0` |
| MIT License | `Licenses.MIT` |
| BSD 3-Clause | `Licenses.BSD_3_CLAUSE` |
| BSD 2-Clause | `Licenses.BSD_2_CLAUSE` |
| GNU GPL v3.0 | `Licenses.GPL_3_0` |
| GNU GPL v2.0 | `Licenses.GPL_2_0` |
| GNU LGPL v3.0 | `Licenses.LGPL_3_0` |
| GNU LGPL v2.1 | `Licenses.LGPL_2_1` |
| Mozilla Public License 2.0 | `Licenses.MPL_2_0` |
| ISC License | `Licenses.ISC` |
| The Unlicense | `Licenses.UNLICENSE` |
| WTFPL | `Licenses.WTFPL` |
| Creative Commons Zero | `Licenses.CC0_1_0` |
| Eclipse Public License 2.0 | `Licenses.EPL_2_0` |
| GNU AGPL v3.0 | `Licenses.AGPL_3_0` |

## Custom Licenses

Define your own license types:

```kotlin
val customLicense = License(
    shortName = "Proprietary",
    fullName = "My Company Proprietary License",
    description = "This software is proprietary and confidential.",
    url = "https://mycompany.com/license"
)

val license = LicenseContent(
    title = "Internal Library",
    author = "My Company",
    license = customLicense,
    copyrightYear = "2024"
)
```

## XML Attributes

| Attribute | Format | Description | Default |
|-----------|--------|-------------|---------|
| `lv_style` | enum | `standard`, `compact`, `card`, `detailed` | `standard` |
| `lv_interaction_mode` | enum | `expand_inline`, `dialog`, `bottom_sheet` | `expand_inline` |
| `lv_card_corner_radius` | dimension | Corner radius for card style | `12dp` |
| `lv_card_elevation` | dimension | Elevation for card style | `4dp` |
| `lv_text_color_primary` | color | Title and name colors | `#121211` |
| `lv_text_color_secondary` | color | Labels and descriptions | `#444444` |
| `lv_text_color_link` | color | Clickable link color | `#0077cc` |
| `lv_text_size_title` | dimension | Title text size | System default |
| `lv_background_color` | color | Item background | `#FFFFFF` |
| `lv_background_color_expand` | color | Expanded section background | `#F8F8F8` |
| `lv_open_image` | reference | Link icon drawable | Built-in icon |
| `lv_image_tint` | color | Icon tint color | `#444444` |
| `lv_divider_color` | color | Divider line color | `#E0E0E0` |

## API Reference

### LicensyView

| Method | Description |
|--------|-------------|
| `setLicenses(list)` | Set the list of licenses to display |
| `getLicenses()` | Get the current list of licenses |
| `clearLicenses()` | Remove all licenses |
| `setCustomization(customization)` | Apply a customization object |

### LicenseContent

```kotlin
data class LicenseContent(
    val title: String,           // Library name
    val author: String,          // Author/Organization
    val license: License,        // License type
    val copyrightYear: String?,  // Optional year
    val url: String?             // Optional repository URL
)
```

## Migration from 0.x

### Breaking Changes

| 0.x | 1.0 |
|-----|-----|
| `LicensyDialog` class | Removed - use `interactionMode = DIALOG` |
| `LicensyBottomSheet` class | Removed - use `interactionMode = BOTTOM_SHEET` |
| `OnDialogListener` interface | Removed |
| JitPack dependency | Maven Central dependency |

### Dependency Change

**Before (0.x):**
```kotlin
// settings.gradle.kts
maven { url = uri("https://jitpack.io") }

// build.gradle.kts
implementation("com.github.ahmmedrejowan:Licensy:0.2")
```

**After (1.0):**
```kotlin
// No repository needed - Maven Central is default

// build.gradle.kts
implementation("com.rejowan:licensy:1.0")
```

### Code Migration

**Before (0.x) - Dialog:**
```kotlin
val dialog = LicensyDialog(this)
dialog.setTitle("Open Source Licenses")
dialog.setLicenses(licenses)
dialog.setCustomization(customization)
dialog.show()
```

**After (1.0) - Dialog Mode:**
```kotlin
// In your layout XML or programmatically add LicensyView
licensyView.apply {
    interactionMode = LicensyInteractionMode.DIALOG
    setCustomization(customization)
    setLicenses(licenses)
}
```

**Before (0.x) - BottomSheet:**
```kotlin
val bottomSheet = LicensyBottomSheet(this)
bottomSheet.setLicenses(licenses)
bottomSheet.show()
```

**After (1.0) - BottomSheet Mode:**
```kotlin
licensyView.apply {
    interactionMode = LicensyInteractionMode.BOTTOM_SHEET
    setLicenses(licenses)
}
```

### New Features to Explore

After migrating, try these new features:

```kotlin
// Try different styles
licensyView.style = LicensyStyle.CARD

// Customize card appearance
licensyView.cardCornerRadius = 16f
licensyView.cardElevation = 8f

// Listen for clicks
licensyView.onLicenseClickListener = OnLicenseClickListener { license ->
    // Handle click
}
```

## Contribute

Please fork this repository and contribute back using [pull requests](https://github.com/ahmmedrejowan/Licensy/pulls).

Any contributions, large or small, major features, bug fixes, are welcomed and appreciated.

Let me know which features you want in the future in the [Issues](https://github.com/ahmmedrejowan/Licensy/issues) tab.

If this project helps you, give it a Star!

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
