import com.android.build.gradle.internal.scope.publishArtifactToConfiguration

plugins {
    kotlin("multiplatform")
    id("com.android.library")
    `maven-publish`
}
afterEvaluate {
    publishing{
        publications{
            create<MavenPublication>("kmmSharedLib"){
                groupId = "com.ashish.test.kmm"
                version = "1.0.0-beta01"
                from(components["release"])
            }
        }
        repositories{
            maven{
                name = "GithubPackages"
//                url = uri("https://maven.pkg.github.com/${System.getenv("OWNER")}/kmm-library")
                url = uri("https://maven.pkg.github.com/AshishYadav/kmm-library")
            }
        }
    }
}
//afterEvaluate {
//    publishing {
//        publications {
//            create<MavenPublication>(namespace) {
//                groupId = rootProject.ext.get("groupId").toString()
//                artifactId = namespace
//                version = rootProject.ext.get("version").toString()
//                from(components["release"])
//            }
//        }
//        repositories {
//            maven {
//                name = "GitHubPackages"
//                url =
//                    uri("https://maven.pkg.github.com/${System.getenv("OWNER")}/kmm-library")
//            }
//        }
//    }
//}

kotlin {
    android {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
    jvm{

    }
    js(IR){

    }
    ios()
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
        val commonMain by getting
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting
        val androidUnitTest by getting
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val ios by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
//        val iosTest by creating {
//            dependsOn(commonTest)
//            iosX64Test.dependsOn(this)
//            iosArm64Test.dependsOn(this)
//            iosSimulatorArm64Test.dependsOn(this)
//        }
    }
}

android {
    namespace = "com.example.kmmlibrary"
    compileSdk = 33
    defaultConfig {
        minSdk = 24
        targetSdk = 33
    }
}