val ktor_version: String by project
val kotlin_version: String by project

buildscript {
    repositories {
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        gradlePluginPortal()
        google()
        mavenCentral()
    }

    dependencies {
        classpath("com.android.tools.build:gradle:7.2.2")
        classpath("com.squareup.sqldelight:gradle-plugin:1.5.3")

        val gettingStarted = "https://github.com/JetBrains/compose-jb/blob/master/tutorials/Getting_Started/README.md"

        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.10")?.because(gettingStarted)
        classpath("org.jetbrains.kotlin:kotlin-serialization:1.6.10")?.because(gettingStarted)
        classpath("org.jetbrains.compose:compose-gradle-plugin:1.1.0")?.because(gettingStarted)
    }
}

allprojects {
    repositories {
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        google()
        mavenCentral()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
