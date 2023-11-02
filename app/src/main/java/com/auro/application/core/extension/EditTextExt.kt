package com.auro.application.core.extension

import android.widget.EditText

val EditText.value
    get() = text?.toString() ?: ""
fun EditText.isEmpty() = value.isEmpty()