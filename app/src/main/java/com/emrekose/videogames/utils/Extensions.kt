package com.emrekose.videogames.utils

import com.emrekose.videogames.BuildConfig

fun debug(body: () -> Unit) {
    if (BuildConfig.DEBUG) body()
}