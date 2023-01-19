buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.0")
        classpath("com.android.tools.build:gradle:7.2.2")
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven {
            url = uri("https://jitpack.io")
        }
        maven {
            val mavenUsername: String by project
            val mavenPassword: String by project
            val mavenUrl: String by project
            credentials {
                username = mavenUsername
                password = mavenPassword
            }
            url = uri(mavenUrl)
        }
        maven { url = uri("https://dl.bintray.com/badoo/maven") }
        maven { url = uri("https://oss.sonatype.org/content/repositories/snapshots/") }
        jcenter()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}