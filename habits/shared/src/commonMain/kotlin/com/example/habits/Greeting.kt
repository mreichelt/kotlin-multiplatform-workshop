package com.example.habits

class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}