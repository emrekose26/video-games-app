plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("kotlin-android")
    id("dagger.hilt.android.plugin")
    id("androidx.navigation.safeargs.kotlin")
    id("kotlin-parcelize")
}

android {
    compileSdkVersion(Configs.compileSdkVersion)
    buildToolsVersion(Configs.buildToolVersion)

    defaultConfig {
        applicationId = Configs.applicationId
        minSdkVersion(Configs.minSdkVersion)
        targetSdkVersion(Configs.targetSdkVersion)
        versionCode = Configs.versionCode
        versionName = Configs.versionName

        testInstrumentationRunner = Configs.testInstrumentationRunner
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    // Kotlin
    implementation(Dependencies.Kotlin.kotlin)
    implementation(Dependencies.Kotlin.Coroutines.core)
    implementation(Dependencies.Kotlin.Coroutines.android)

    // AndroidX
    implementation(Dependencies.AndroidX.AppCompat.appcompat)
    implementation(Dependencies.AndroidX.RecyclerView.recyclerview)
    implementation(Dependencies.AndroidX.ConstraintLayout.constraintLayout)
    implementation(Dependencies.AndroidX.Fragment.fragment)
    implementation(Dependencies.AndroidX.Core.ktx)
    implementation(Dependencies.AndroidX.Lifecycle.viewModel)
    implementation(Dependencies.AndroidX.Lifecycle.liveData)
    implementation(Dependencies.AndroidX.Lifecycle.runtime)
    implementation(Dependencies.AndroidX.Hilt.viewModel)
    kapt(Dependencies.AndroidX.Hilt.compiler)

    // Material Design
    implementation(Dependencies.Material.material)

    // Dagger-Hilt
    implementation(Dependencies.Dagger.hiltAndroid)
    kapt(Dependencies.Dagger.hiltAndroidCompiler)

    // AAC
    implementation(Dependencies.AndroidX.Room.runtime)
    implementation(Dependencies.AndroidX.Room.ktx)
    kapt(Dependencies.AndroidX.Room.compiler)
    implementation(Dependencies.AndroidX.Navigation.fragment)
    implementation(Dependencies.AndroidX.Navigation.ui)

    // Networking
    implementation(Dependencies.Retrofit.retrofit)
    implementation(Dependencies.OkHttp.okhttp)
    implementation(Dependencies.OkHttp.loggingInterceptor)

    // Moshi
    implementation(Dependencies.Moshi.moshi)
    implementation(Dependencies.Retrofit.moshiConverter)
    kapt(Dependencies.Moshi.moshiCodegen)

    // Glide
    implementation(Dependencies.Glide.glide)
    kapt(Dependencies.Glide.compiler)

    // Timber
    implementation(Dependencies.Timber.timber)

    // Testing
    testImplementation(Dependencies.Test.Junit.junit)
    testImplementation(Dependencies.Test.AndroidX.Core.test)
    testImplementation(Dependencies.Test.Mockk.unit)
    testImplementation(Dependencies.Test.Truth.truth)
    testImplementation(Dependencies.Test.AndroidX.Arch.core)
    testImplementation(Dependencies.Kotlin.Coroutines.test)
    testImplementation(Dependencies.OkHttp.mockWebServer)
    testImplementation(Dependencies.AndroidX.Room.testing)

    androidTestImplementation(Dependencies.Test.AndroidX.Junit.ktx)
    androidTestImplementation(Dependencies.Test.AndroidX.Arch.core)
    androidTestImplementation(Dependencies.Test.AndroidX.Espresso.espresso)
}