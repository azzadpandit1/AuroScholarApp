package com.auro.application.core.platform

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import com.auro.application.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        handleOnBackPressed()
    }
    private fun handleOnBackPressed() {
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    onActivityBackPressed()
                }
            }
        onBackPressedDispatcher.addCallback(this, callback)
    }

    open fun onActivityBackPressed() {
        super.onBackPressed()
    }

}