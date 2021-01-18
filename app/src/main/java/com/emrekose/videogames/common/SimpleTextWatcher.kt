package com.emrekose.videogames.common

import android.text.Editable
import android.text.TextWatcher

abstract class SimpleTextWatcher: TextWatcher {
    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }
    override fun afterTextChanged(s: Editable?) { }
}