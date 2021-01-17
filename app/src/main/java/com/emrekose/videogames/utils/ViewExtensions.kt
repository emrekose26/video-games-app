package com.emrekose.videogames.utils

import android.content.Context
import android.view.View
import android.widget.Toast

fun View.visible() { this.visibility = View.VISIBLE }

fun View.gone() { this.visibility = View.GONE }

fun visibleAll(vararg views: View) = views.forEach { it.visible() }

fun goneAll(vararg views: View) = views.forEach { it.gone() }

fun Context.toast(message: CharSequence?, duration: Int = Toast.LENGTH_SHORT) =
    Toast.makeText(this, message, duration).show()