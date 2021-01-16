package com.emrekose.videogames.utils

import android.view.View

fun View.visible() { this.visibility = View.VISIBLE }

fun View.gone() { this.visibility = View.GONE }

fun visibleAll(vararg views: View) = views.forEach { it.visible() }

fun goneAll(vararg views: View) = views.forEach { it.gone() }