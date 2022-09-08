package com.example.habits

actual class Platform actual constructor() {
    actual val platform: String = System.getProperty("java.version")
}
