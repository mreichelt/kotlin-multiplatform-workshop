plugins {
    kotlin("multiplatform")
}

kotlin {
    jvm()
    js {
        browser()
    }

    iosX64()
    iosArm64()
    iosSimulatorArm64()
}
