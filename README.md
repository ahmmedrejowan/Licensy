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

### XML

``` XML 
    <com.rejowan.Licensy.Licensy
        android:id="@+id/Licensy"
        android:layout_width="match_parent"
        android:layout_height="250dp"
        android:layout_marginTop="20dp" />

```

### Kotlin

``` Kotlin

        binding.Licensy.knobEnableListener = object : Licensy.OnKnobEnableListener {
            override fun onKnobEnableChanged(isEnable: Boolean, progress: Int) {
                
            }
        }
        
        binding.Licensy.progressChangeListener = object : Licensy.OnProgressChangeListener {
            override fun onProgressChanged(progress: Int) {
                
            }
        }
        

```

## Customization

### XML


``` XML 

<com.rejowan.Licensy.Licensy
    android:id="@+id/Licensy2"
    android:layout_width="0dp"
    android:layout_height="wrap_content"
    android:layout_gravity="center"
    android:layout_weight="1"
    app:big_progress_diff="5"
    app:big_progress_multiplier="3"
    app:border_color="#dddddd"
    app:border_width="6dp"
    app:circle_color="#ffffff"
    app:circle_style="solid"
    app:current_progress="10"
    app:double_touch_to_enable="true"
    app:indicator_color="#8062D6"
    app:indicator_style="line"
    app:label_text="Rotary"
    app:label_text_color="#8062D6"
    app:label_text_font="@font/ubuntu_normal"
    app:label_text_size="12sp"
    app:max="20"
    app:min="0"
    app:progress_color="#777777"
    app:progress_filled_color="#8062D6"
    app:progress_filled_multiplier="1.3"
    app:progress_style="line"
    app:progress_text_color="#8062D6"
    app:progress_text_font="@font/ubuntu_normal"
    app:progress_text_size="26sp"
    app:progress_text_style="bold"
    app:show_big_progress="true"
    app:show_border="true"
    app:show_label="true"
    app:show_progress_text="true"
    app:show_suffix_text="true"
    app:suffix_text="db"
    app:suffix_text_color="#8062D6"
    app:suffix_text_size="10sp"
    app:suffix_text_style="normal"
    app:touch_to_enable="false"
    />
```

All of this can be set programmatically using Kotlin or Java as well.

### Kotlin


``` Kotlin

        // listener for knob enable/disable
        binding.Licensy.knobEnableListener = object : Licensy.OnKnobEnableListener {
            override fun onKnobEnableChanged(isEnable: Boolean, progress: Int) {

            }
        }

        // listener for knob progress change
        binding.Licensy.progressChangeListener = object : Licensy.OnProgressChangeListener {
            override fun onProgressChanged(progress: Int) {

            }
        }
        
        // set knob min, max and current progress
        binding.Licensy.currentProgress = 50
        binding.Licensy.min = 0
        binding.Licensy.max = 100

```


## Attribute

Full list of attributes available

| Attribute                   | Format       | Description                                   | Example                 |
|-----------------------------|--------------|-----------------------------------------------|-------------------------|
| `circle_style`              | enum         | Style of the circle (`solid` or `gradient`)   | `circle_style="solid"` |
| `circle_color`              | color        | Color of the circle                           | `circle_color="#FF0000"`|
| `circle_gradient_center_color` | color     | Center color of the circle gradient           | `circle_gradient_center_color="#FF0000"`|
| `circle_gradient_outer_color`  | color     | Outer color of the circle gradient            | `circle_gradient_outer_color="#00FF00"`|
| `show_border`               | boolean      | Indicates whether to show border             | `show_border="true"`    |
| `border_color`              | color        | Color of the border                           | `border_color="#000000"`|
| `border_width`              | dimension    | Width of the border                           | `border_width="2dp"`    |
| `progress_style`            | enum         | Style of the progress (`circle` or `line`)    | `progress_style="circle"`|
| `progress_color`            | color        | Color of the progress                         | `progress_color="#00FF00"`|
| `show_big_progress`         | boolean      | Indicates whether to show big progress       | `show_big_progress="true"`|
| `big_progress_multiplier`   | float        | Multiplier for big progress                   | `big_progress_multiplier="1.5"`|
| `big_progress_diff`         | integer      | Difference for big progress                   | `big_progress_diff="20"`|
| `progress_filled_color`     | color        | Color of filled progress                      | `progress_filled_color="#FF0000"`|
| `progress_filled_multiplier`| float        | Multiplier for filled progress                | `progress_filled_multiplier="0.8"`|
| `indicator_style`           | enum         | Style of the indicator (`circle` or `line`)   | `indicator_style="circle"`|
| `indicator_color`           | color        | Color of the indicator                        | `indicator_color="#0000FF"`|
| `indicator_size`            | dimension    | Size of the indicator                         | `indicator_size="8dp"`   |
| `show_progress_text`        | boolean      | Indicates whether to show progress text       | `show_progress_text="true"`|
| `progress_text`             | string       | Text for progress                             | `progress_text="Volume"`|
| `progress_text_color`       | color        | Color of progress text                        | `progress_text_color="#000000"`|
| `progress_text_size`        | dimension    | Size of progress text                         | `progress_text_size="14sp"`|
| `progress_text_style`       | enum         | Style of progress text (`normal`, `bold`, `italic`, `bold_italic`) | `progress_text_style="bold"`|
| `progress_text_font`        | reference    | Font for progress text                        | `progress_text_font="@font/my_font"`|
| `show_suffix_text`          | boolean      | Indicates whether to show suffix text         | `show_suffix_text="true"`|
| `suffix_text`               | string       | Text for suffix                               | `suffix_text="dB"`      |
| `suffix_text_color`         | color        | Color of suffix text                          | `suffix_text_color="#00FF00"`|
| `suffix_text_size`          | dimension    | Size of suffix text                           | `suffix_text_size="12sp"`|
| `suffix_text_style`         | enum         | Style of suffix text (`normal`, `bold`, `italic`, `bold_italic`) | `suffix_text_style="italic"`|
| `suffix_text_font`          | reference    | Font for suffix text                          | `suffix_text_font="@font/my_font"`|
| `show_label`                | boolean      | Indicates whether to show label               | `show_label="true"`     |
| `label_text`                | string       | Text for label                                | `label_text="Volume"`   |
| `label_text_color`          | color        | Color of label text                           | `label_text_color="#000000"`|
| `label_text_size`           | dimension    | Size of label text                            | `label_text_size="16sp"`|
| `label_text_style`          | enum         | Style of label text (`normal`, `bold`, `italic`, `bold_italic`) | `label_text_style="bold"`|
| `label_text_font`           | reference    | Font for label text                           | `label_text_font="@font/my_font"`|
| `label_margin`              | dimension    | Margin for label                              | `label_margin="8dp"`    |
| `knob_enable`               | boolean      | Indicates whether knob is enabled            | `knob_enable="true"`    |
| `touch_to_enable`           | boolean      | Indicates whether touch enables knob          | `touch_to_enable="true"`|
| `double_touch_to_enable`    | boolean      | Indicates whether double touch enables knob   | `double_touch_to_enable="true"`|
| `d_circle_color`            | color        | Default color of the circle                   | `d_circle_color="#FFFFFF"`|
| `d_circle_gradient_center_color` | color  | Default center color of the circle gradient  | `d_circle_gradient_center_color="#FFFFFF"`|
| `d_circle_gradient_outer_color`  | color  | Default outer color of the circle gradient   | `d_circle_gradient_outer_color="#CCCCCC"`|
| `d_border_color`            | color        | Default color of the border                   | `d_border_color="#000000"`|
| `d_progress_color`          | color        | Default color of the progress                 | `d_progress_color="#00FF00"`|
| `d_big_progress_color`      | color        | Default color of the big progress             | `d_big_progress_color="#FF0000"`|
| `d_progress_filled_color`   | color        | Default color of filled progress              | `d_progress_filled_color="#FF0000"`|
| `d_indicator_color`         | color        | Default color of the indicator                | `d_indicator_color="#0000FF"`|
| `d_progress_text_color`     | color        | Default color of progress text                | `d_progress_text_color="#000000"`|
| `d_suffix_text_color`       | color        | Default color of suffix text                  | `d_suffix_text_color="#00FF00"`|
| `d_label_text_color`        | color        | Default color of label text                   | `d_label_text_color="#000000"`|
| `min`                       | integer      | Minimum value                                 | `min="0"`               |
| `max`                       | integer      | Maximum value                                 | `max="100"`             |
| `current_progress`          | integer      | Current progress value                        | `current_progress="50"` |


## Notes
- The library is in its early stages, so there may be some bugs.
- If you find any bugs, please report them in the `Issues` tab.
- Sample app is available in the [app](https://github.com/ahmmedrejowan/Licensy/tree/master/app) directory.
- It doesn't Support Floating Numbers for Value.

## Inspiration and Credit
- Inspired by [Knob](https://github.com/CoDevBlocks/Knob) by  [CoDevBlocks](https://github.com/CoDevBlocks)
- Inspired by [Knob](https://github.com/BeppiMenozzi/Knob) by  [BeppiMenozzi](https://github.com/BeppiMenozzi)
- Inspired by [kotlin-rotary-knob](https://github.com/o4oren/kotlin-rotary-knob) by  [o4oren](https://github.com/o4oren)
- Color Picker Inspired by [ColorPicker](https://github.com/Dhaval2404/ColorPicker) by  [Dhaval2404](https://github.com/Dhaval2404)

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
