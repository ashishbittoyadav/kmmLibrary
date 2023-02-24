package com.example.kmmlibrary

class JvmClass : Platform{
    override val name: String
        get() = "hello from jvm"

}

actual fun getPlatform(): Platform {
    return JvmClass()
}