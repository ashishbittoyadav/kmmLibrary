package com.example.kmmlibrary

class jsMain : Platform{
    override val name: String
        get() = "hello from js main"

}

actual fun getPlatform(): Platform {
    return jsMain()
}