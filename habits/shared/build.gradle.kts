import org.jetbrains.compose.compose
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val compose_version: String by project
val ktor_version: String by project
val kotlin_version: String by project
val store_version: String by project
val sqldelight_version: String by project

plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")

    id("com.android.library")
    id("com.squareup.sqldelight")
    id("org.jetbrains.compose")
}

kotlin {
    android()
    jvm()

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
        }
    }

    sourceSets {
        all {
            languageSettings.optIn("kotlin.RequiresOptIn")
            languageSettings.optIn("kotlinx.coroutines.ExperimentalCoroutinesApi")
        }

        val commonMain by getting {
            dependencies {
                implementation("com.benasher44:uuid:0.5.0")

                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.0")

                implementation("io.ktor:ktor-client-core:$ktor_version")
                implementation("io.ktor:ktor-client-cio:$ktor_version")
                implementation("io.ktor:ktor-client-resources:$ktor_version")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.4")
            }
        }
        val composeMain by creating {
            dependencies {
                implementation(compose.desktop.currentOs)
                implementation(compose.foundation)
                implementation(compose.material)
                implementation(compose.runtime)
                implementation(compose.ui)
            }
        }
        val androidMain by getting {
            dependencies {
                implementation("androidx.compose.material:material:$compose_version")
                implementation("androidx.compose.ui:ui-tooling-preview:$compose_version")
                implementation("androidx.compose.ui:ui-tooling:$compose_version")

                implementation("com.dropbox.mobile.store:store4:$store_version")
                implementation("com.squareup.sqldelight:android-driver:$sqldelight_version")
            }
        }
        val androidTest by getting {
            dependencies {
                implementation("com.squareup.okhttp3:mockwebserver:4.10.0")
            }
        }

        val jvmMain by getting {
            dependencies {
                dependsOn(composeMain)

                implementation("com.squareup.sqldelight:sqlite-driver:$sqldelight_version")
            }
        }

        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)

            dependencies {
                implementation("com.squareup.sqldelight:native-driver:$sqldelight_version")
            }
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            iosSimulatorArm64Test.dependsOn(this)
        }
    }
}

android {
    compileSdk = 32
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = 28
        targetSdk = 32
    }
    buildFeatures {
        compose = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    composeOptions {
        kotlinCompilerExtensionVersion = compose_version
    }
}

compose.desktop {
    application.mainClass = "MainKt"
}

sqldelight {
    database("Database") {
        packageName = "com.example.habits"
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "11"
}
