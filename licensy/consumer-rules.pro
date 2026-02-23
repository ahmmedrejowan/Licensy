# Licensy - Consumer ProGuard Rules
# These rules are applied to consumer apps that use this library

# Keep public API classes
-keep class com.rejowan.licensy.LicensyView { *; }
-keep class com.rejowan.licensy.LicenseContent { *; }
-keep class com.rejowan.licensy.LicensyCustomization { *; }
-keep class com.rejowan.licensy.LicensyStyle { *; }
-keep class com.rejowan.licensy.LicensyInteractionMode { *; }
-keep class com.rejowan.licensy.Licenses { *; }
-keep class com.rejowan.licensy.License { *; }
-keep class com.rejowan.licensy.LicenseDetailPresenter { *; }
-keep interface com.rejowan.licensy.OnLicenseClickListener { *; }

# Keep enum members
-keepclassmembers enum com.rejowan.licensy.LicensyStyle {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

-keepclassmembers enum com.rejowan.licensy.LicensyInteractionMode {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

# Keep data class members for serialization
-keepclassmembers class com.rejowan.licensy.LicenseContent {
    <fields>;
    <init>(...);
}

-keepclassmembers class com.rejowan.licensy.License {
    <fields>;
    <init>(...);
}
