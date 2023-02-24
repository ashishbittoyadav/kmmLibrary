package com.example.kmmlibrary

class Greeting {
    private val platform: Platform = getPlatform()

    fun greet(): String {
        return "Hello from common function, ${platform.name}!"
    }
}