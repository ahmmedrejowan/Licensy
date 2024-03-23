<p align="center"><img src="https://raw.githubusercontent.com/ahmmedrejowan/Licensy/master/files/logo.svg" width="100px" align="center"/></p>
<h1 align="center">Licensy</h1> 
<h3 align="center">A lightweight License/Credit Sharing Library for Android<b></b></h3>

<p align="center"> <a href="https://www.android.com"><img src="https://img.shields.io/badge/platform-Android-yellow.svg" alt="platform"></a>
 <a href="https://android-arsenal.com/api?level=21"><img src="https://img.shields.io/badge/API-24%2B-brightgreen.svg?style=flat" alt="API"></a> <a href="https://jitpack.io/#ahmmedrejowan/Licensy/"><img src="https://jitpack.io/v/ahmmedrejowan/Licensy.svg" alt="JitPack"></a> <a href="https://github.com/ahmmedrejowan/Licensy/blob/master/LICENSE"><img src="https://img.shields.io/github/license/ahmmedrejowan/Licensy" alt="GitHub license"></a> </p>

 <p align="center"> <a href="https://github.com/ahmmedrejowan/Licensy/issues"><img src="https://img.shields.io/github/issues/ahmmedrejowan/Licensy" alt="GitHub issues"></a> <a href="https://github.com/ahmmedrejowan/Licensy/network"><img src="https://img.shields.io/github/forks/ahmmedrejowan/Licensy" alt="GitHub forks"></a> <a href="https://github.com/ahmmedrejowan/Licensy/stargazers"><img src="https://img.shields.io/github/stars/ahmmedrejowan/Licensy" alt="GitHub stars"></a> <a href="https://github.com/ahmmedrejowan/Licensy/graphs/contributors"> <img src="https://img.shields.io/github/contributors/ahmmedrejowan/Licensy" alt="GitHub contributors"></a>   </p>

## Table of Contents

- [Purpose](#purpose)
- [Features](#features)
- [Demo](#demo)
- [Prerequisites](#prerequisites)
- [Dependency](#dependency)
- [Usage](#usage)
- [Customization](#customization)
- [Attributes](#attribute)
- [Notes](#notes)
- [Contribute](#contribute)
- [License](#license)

## Purpose
Licensy is a handy Android library crafted in Kotlin. I always use opensouce libraries in all of my projects and I believe everyone does the same. In my projects I always try to put credits for the libraries or resources I use in my code. I looked for this type of library which will allow me to give the credits or add the licenses of the library in a proper manner for a long time. Guess what? I decided to create one.

## Features
- Lightweight
- Highly customizable
- Supports both Kotlin and Java
- Three different styles, a View, a Dialog and a BottomSheet.

## Demo

- 1 for Test app home
- 2 for View
- 3 for Dialog
- 4 for BottomSheet

**Shots**

|  1 |  2  |         3                    |  4 |
|-------|--------------|-----------------------------------------------------------------------------------------------------|-----------------|
|  ![Shot1](https://raw.githubusercontent.com/ahmmedrejowan/Licensy/master/files/shot1.png)  |  ![Shot2](https://raw.githubusercontent.com/ahmmedrejowan/Licensy/master/files/shot2.png) | ![Shot3](https://raw.githubusercontent.com/ahmmedrejowan/Licensy/master/files/shot3.png) | ![Shot4](https://raw.githubusercontent.com/ahmmedrejowan/Licensy/master/files/shot4.png) |


**Animation showing the view in details**

|  1 |  2  |         3                    |  4 |
|-------|--------------|-----------------------------------------------------------------------------------------------------|-----------------|
|  ![Anim1](https://raw.githubusercontent.com/ahmmedrejowan/Licensy/master/files/anim1.gif)  |  ![Anim2](https://raw.githubusercontent.com/ahmmedrejowan/Licensy/master/files/anim2.gif) | ![Anim3](https://raw.githubusercontent.com/ahmmedrejowan/Licensy/master/files/anim3.gif) | ![Anim4](https://raw.githubusercontent.com/ahmmedrejowan/Licensy/master/files/anim4.gif) |

You can download the test apk to try out the features of this library - [Download](https://github.com/ahmmedrejowan/Licensy/raw/master/app/release/app-release.apk)

## Prerequisites

### Kotlin DSL


``` Kotlin
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven{
            url = uri("https://jitpack.io")
        }
    }
}
```


``` groovy
dependencyResolutionManagement {
		repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
		repositories {
			mavenCentral()
			maven { url 'https://jitpack.io' }
		}
	}
```



## Dependency
Add this to your module's `build.gradle.kts` file (latest version <a href="https://jitpack.io/#ahmmedrejowan/Licensy"><img src="https://jitpack.io/v/ahmmedrejowan/Licensy.svg" alt="JitPack"></a>):


``` kotlin
dependencies {
    ...
    implementation("com.github.ahmmedrejowan:Licensy:0.1")
}
```


``` groovy
dependencies {
    ...
    implementation 'com.github.ahmmedrejowan:Licensy:0.1'
}
```

## Usage

There are 3 different usages of this library.
1. Use it as a View `LicensyView`
2. Use it as a Dialog `LicensyDialog`
3. Use it as a BottomSheet `LicensyBottomSheet`

### LicensyView
This can be used in anywhere, in any `Activity` or `Fragment`.

#### XML

``` XML 
    <com.rejowan.licensy.LicensyView
        android:id="@+id/licensyView"
        android:layout_width="match_parent"
        android:layout_height="wrap_content" />
```
This can be customized with attributes as well. Those attributes will take effect to all elements inside the view.

``` XML
    <com.rejowan.licensy.LicensyView
        android:id="@+id/licensyView"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginBottom="50dp"
        app:lv_background_color=""
        app:lv_background_color_expand=""
        app:lv_divider_color=""
        app:lv_image_tint=""
        app:lv_open_image=""
        app:lv_text_color_link=""
        app:lv_text_color_primary=""
        app:lv_text_color_secondary=""
        app:lv_text_size_title="" />
```

#### Kotlin
All the attributes in XML will also work here. But the main function is the `setLicense(listOfLicenses: List<LicenseContent>)` method.

``` Kotlin
    licensyView.setLicenses(listOfLicenses) // set the licenses to the view
```
The `LicenseContent` is this-

```Kotlin
data class LicenseContent(
    val title: String,
    val author: String,
    val licenses: Licenses,
    val copyrightYear: Int? = null,
    val url: String? = null
)
```

### LicenseDialog
This one has it's predefined layout. I kept it simple but added some spice as well. As always, it's also customizable.

#### Simple Usage

``` Kotlin
val licensyDialog = LicensyDialog(this)
licensyDialog.setLicenses(list)
licensyDialog.show()
```

#### Full Usage
``` Kotlin
val licensyDialog = LicensyDialog(this)
licensyDialog.setTitle("Licenses")
licensyDialog.setCloseText("Dismiss")
licensyDialog.setAccentColor(Color.GREEN)
licensyDialog.setBackgroundColor(Color.DKGRAY)
licensyDialog.setLicenses(list)
licensyDialog.setOnDialogListener(object : OnDialogListener {
    override fun onShow() {
        Log.e("Licensy", "Dialog shown")
    }

    override fun onDismiss() {
        Log.e("Licensy", "Dialog dismissed")
    }
})
licensyDialog.setCustomization()
licensyDialog.show()
```

#### setCustomization()
You need to pass the LicensyCustomization to customize the items inside the list of licenses. Like this - `setCustomization(customization: LicensyCustomization)`

This is the `LicensyCustomization` data class
``` Kotlin
    data class LicensyCustomization(
        val lvPrimaryColor : Int = Color.parseColor("#121211"),
        val lvSecondaryColor : Int = Color.parseColor("#444444"),
        val lvLinkColor : Int = Color.parseColor("#0077cc"),
        val lvTitleTextSize : Float = 0f,
        val lvBackgroundColor : Int = Color.WHITE,
        val lvBackgroundColorExpand : Int = Color.parseColor("#f8f8f8"),
        val lvOpenImage : Int = R.drawable.ic_licensy_open,
        val imageTint : Int = Color.parseColor("#444444"),
        val lvDividerColor : Int = Color.parseColor("#e0e0e0")

    )
```

### LicensyBottomSheet
This also has the same level of customization as the `LicensyDialog`. Here is a simple example-

``` Kotlin
val licensyBottomSheet = LicensyBottomSheet(this)
licensyBottomSheet.setLicenses(list)
licensyBottomSheet.show()
```
## Attribute

### XML

| Attribute                  | Format       | Description                                   | Example         |
|----------------------------|--------------|-----------------------------------------------|-----------------|
| `lv_text_color_primary`    | color        | Primary text color                            | `#FF0000`       |
| `lv_text_color_secondary`  | color        | Secondary text color                          | `#00FF00`       |
| `lv_text_color_link`       | color        | Link text color                               | `#0000FF`       |
| `lv_text_size_title`       | dimension    | Title text size                               | `16sp`          |
| `lv_background_color`      | color        | Background color                              | `#FFFFFF`       |
| `lv_background_color_expand`| color        | Background color when expanded                | `#F0F0F0`       |
| `lv_open_image`            | reference    | Reference to the open image                   | `@drawable/ic_open` |
| `lv_image_tint`            | color        | Tint color for the image                      | `#CCCCCC`       |
| `lv_divider_color`         | color        | Color of divider between sections             | `#888888`       |

## Notes
- The library is in its early stages, so there may be some bugs.
- If you find any bugs, please report them in the `Issues` tab.
- Sample app is available in the [app](https://github.com/ahmmedrejowan/Licensy/tree/master/app) directory.
- Right now, it only supports a few Licences, I'll be adding more in the future. Feel free to add using pull request.

## Inspiration and Credit
- Inspired by [LicensesDialog](https://github.com/PSDev/LicensesDialog) by  [PSDev](https://github.com/PSDev/)
- Inspired by [Licenser](https://github.com/marcoscgdev/Licenser) by  [marcoscgdev](https://github.com/marcoscgdev)
- Inspired by [LicensesDialog](https://github.com/colinrtwhite/LicensesDialog) by  [colinrtwhite](https://github.com/colinrtwhite/)


## Contribute
Please fork this repository and contribute back using [pull requests](https://github.com/ahmmedrejowan/Licensy/pulls).

Any contributions, large or small, major features, bug fixes, are welcomed and appreciated.

Let me know which features you want in the future in `Request Feature` tab.

If this project helps you a little bit, then give a to Star ⭐ the Repo.

## License
* [Apache Version 2.0](http://www.apache.org/licenses/LICENSE-2.0.html)

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
