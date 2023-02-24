package com.example.kmmlibrary

class iosSimulatorArm64Main : Platform{
    override val name: String
        get() = "hello from iosSimulatorArm64Main"

}

actual fun getPlatform(): Platform {
    return iosSimulatorArm64Main()
}