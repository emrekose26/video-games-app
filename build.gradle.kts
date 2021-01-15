// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    val kotlin_version by extra("1.4.10")
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath(Plugins.Gradle.plugin)
        classpath(Plugins.Kotlin.plugin)
        classpath(Plugins.Hilt.plugin)
        classpath(Plugins.Navigation.safeArgs)
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version")
    }
}

plugins {
    kotlin("android") version "1.4.20" apply false
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}

tasks.register("clean", Delete::class.java) {
    delete(rootProject.buildDir)
}