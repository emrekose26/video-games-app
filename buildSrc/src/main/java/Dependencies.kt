object Dependencies {

    object Kotlin {
        const val version = "1.4.21"
        const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib:$version"

        object Coroutines {
            private const val version = "1.4.2"
            const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version"
            const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version"
            const val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$version"
        }
    }

    object AndroidX {
        object AppCompat {
            private const val version = "1.2.0"
            const val appcompat = "androidx.appcompat:appcompat:$version"
        }

        object Core {
            private const val version = "1.3.2"
            const val ktx = "androidx.core:core-ktx:$version"
        }

        object RecyclerView {
            private const val version = "1.1.0"
            const val recyclerview = "androidx.recyclerview:recyclerview:$version"
        }

        object ConstraintLayout {
            private const val version = "2.0.4"
            const val constraintLayout = "androidx.constraintlayout:constraintlayout:$version"
        }

        object Fragment {
            private const val version = "1.2.5"
            const val fragment = "androidx.fragment:fragment-ktx:$version"
        }

        object Hilt {
            private const val version = "1.0.0-alpha01"
            const val viewModel = "androidx.hilt:hilt-lifecycle-viewmodel:$version"
            const val compiler = "androidx.hilt:hilt-compiler:$version"
        }

        object Lifecycle {
            private const val version = "2.2.0"
            const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:$version"
            const val liveData = "androidx.lifecycle:lifecycle-livedata-ktx:$version"
            const val runtime = "androidx.lifecycle:lifecycle-runtime-ktx:$version"
            const val common = "androidx.lifecycle:lifecycle-common-java8:$version"
        }

        object Navigation {
            const val version = "2.3.2"
            const val fragment = "androidx.navigation:navigation-fragment-ktx:$version"
            const val ui = "androidx.navigation:navigation-ui-ktx:$version"
        }

        object Room {
            private const val version = "2.2.6"
            const val runtime = "androidx.room:room-runtime:$version"
            const val compiler = "androidx.room:room-compiler:$version"
            const val ktx = "androidx.room:room-ktx:$version"
            const val testing = "androidx.room:room-testing:$version"
        }
    }

    object Material {
        private const val version = "1.2.1"
        const val material = "com.google.android.material:material:$version"
    }

    object Dagger {
        const val version = "2.28-alpha"
        const val hiltAndroid = "com.google.dagger:hilt-android:$version"
        const val hiltAndroidCompiler = "com.google.dagger:hilt-android-compiler:$version"
    }

    object Retrofit {
        private const val version = "2.9.0"
        const val retrofit = "com.squareup.retrofit2:retrofit:$version"
        const val moshiConverter = "com.squareup.retrofit2:converter-moshi:$version"
    }

    object OkHttp {
        private const val version = "4.8.0"
        const val okhttp = "com.squareup.okhttp3:okhttp:$version"
        const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:$version"
        const val mockWebServer = "com.squareup.okhttp3:mockwebserver:$version"
    }

    object Moshi {
        private const val version = "1.9.2"
        const val moshi = "com.squareup.moshi:moshi-kotlin:$version"
        const val moshiCodegen = "com.squareup.moshi:moshi-kotlin-codegen:$version"
    }

    object Glide {
        private const val version = "4.11.0"
        const val glide = "com.github.bumptech.glide:glide:$version"
        const val compiler = "com.github.bumptech.glide:compiler:$version"
    }

    object Timber {
        private const val version = "4.1.2"
        const val timber = "com.jakewharton.timber:timber:$version"
    }

    object Test {
        object Junit {
            private const val version = "4.13.1"
            const val junit = "junit:junit:$version"
        }

        object Mockk {
            private const val version = "1.10.4"
            const val unit = "io.mockk:mockk:$version"
            const val instrumented = "io.mockk:mockk-android:$version"
        }

        object Truth {
            private const val version = "1.1.0"
            const val truth = "com.google.truth:truth:$version"
        }

        object AndroidX {
            object Core {
                private const val version = "1.3.0"
                const val test =  "androidx.test:core:$version"
            }
            object Junit {
                private const val version = "1.1.2"
                const val ktx = "androidx.test.ext:junit-ktx:$version"
            }

            object Runner {
                private const val version = "1.3.0"
                const val runner = "androidx.test:runner:$version"
            }

            object Arch {
                private const val version = "2.1.0"
                const val core = "androidx.arch.core:core-testing:$version"
            }

            object Espresso {
                private const val version = "3.3.0"
                const val espresso = "androidx.test.espresso:espresso-core:$version"
            }
        }
    }
}