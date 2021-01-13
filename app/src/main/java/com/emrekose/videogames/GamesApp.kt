package com.emrekose.videogames

import android.app.Application
import com.emrekose.videogames.utils.debug
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class GamesApp: Application() {
    override fun onCreate() {
        super.onCreate()
        debug { Timber.plant(Timber.DebugTree()) }
    }
}