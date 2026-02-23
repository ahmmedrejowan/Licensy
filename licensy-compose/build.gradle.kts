plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    `maven-publish`
    signing
}

android {
    namespace = "com.rejowan.licensy.compose"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlin {
        compilerOptions {
            jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17)
        }
    }

    buildFeatures {
        compose = true
    }

    publishing {
        singleVariant("release") {
            withSourcesJar()
            withJavadocJar()
        }
    }
}

dependencies {
    // Reuse data classes from the view-based module
    implementation(project(":licensy"))

    // Compose
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)

    // For ModalBottomSheet
    implementation(libs.androidx.compose.material3)

    debugImplementation(libs.androidx.compose.ui.tooling)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
}

publishing {
    publications {
        register<MavenPublication>("release") {
            groupId = "com.rejowan"
            artifactId = "licensy-compose"
            version = "1.0"

            afterEvaluate {
                from(components["release"])
            }

            pom {
                name.set("Licensy Compose")
                description.set("Jetpack Compose components for displaying open source licenses")
                url.set("https://github.com/ahmmedrejowan/Licensy")
                inceptionYear.set("2024")

                licenses {
                    license {
                        name.set("Apache License 2.0")
                        url.set("https://www.apache.org/licenses/LICENSE-2.0.txt")
                        distribution.set("repo")
                    }
                }

                developers {
                    developer {
                        id.set("ahmmedrejowan")
                        name.set("K M Rejowan Ahmmed")
                        email.set("ahmmedrejowan@gmail.com")
                        url.set("https://github.com/ahmmedrejowan")
                    }
                }

                scm {
                    url.set("https://github.com/ahmmedrejowan/Licensy")
                    connection.set("scm:git:git://github.com/ahmmedrejowan/Licensy.git")
                    developerConnection.set("scm:git:ssh://git@github.com/ahmmedrejowan/Licensy.git")
                }
            }
        }
    }

    repositories {
        maven {
            name = "staging"
            url = uri(layout.buildDirectory.dir("staging-deploy"))
        }
    }
}

the<SigningExtension>().apply {
    sign(the<PublishingExtension>().publications)
}

tasks.register<Zip>("createCentralBundle") {
    dependsOn("publishReleasePublicationToStagingRepository")

    from(layout.buildDirectory.dir("staging-deploy"))
    archiveFileName.set("licensy-compose-1.0-bundle.zip")
    destinationDirectory.set(layout.buildDirectory.dir("central-bundle"))
}
