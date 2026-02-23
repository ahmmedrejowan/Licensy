plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    `maven-publish`
    signing
}

android {
    namespace = "com.rejowan.licensy"
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
        viewBinding = true
    }

    publishing {
        singleVariant("release") {
            withSourcesJar()
            withJavadocJar()
        }
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}

publishing {
    publications {
        register<MavenPublication>("release") {
            groupId = "com.rejowan"
            artifactId = "licensy"
            version = "1.0"

            afterEvaluate {
                from(components["release"])
            }

            pom {
                name.set("Licensy")
                description.set("A flexible Android library for displaying open source licenses with multiple styles and interaction modes")
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

                issueManagement {
                    system.set("GitHub Issues")
                    url.set("https://github.com/ahmmedrejowan/Licensy/issues")
                }
            }
        }
    }

    // Publish to local staging directory, then upload manually to Central Portal
    repositories {
        maven {
            name = "staging"
            url = uri(layout.buildDirectory.dir("staging-deploy"))
        }
    }
}

// Signing
the<SigningExtension>().apply {
    sign(the<PublishingExtension>().publications)
}

// Task to create a bundle zip for Central Portal upload
tasks.register<Zip>("createCentralBundle") {
    dependsOn("publishReleasePublicationToStagingRepository")

    from(layout.buildDirectory.dir("staging-deploy"))
    archiveFileName.set("licensy-1.0-bundle.zip")
    destinationDirectory.set(layout.buildDirectory.dir("central-bundle"))
}
