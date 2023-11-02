package com.auro.application.core.platform

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
abstract class BaseFragment : Fragment() {

    private var backPressedTime = 0L

    internal fun firstTimeCreated(savedInstanceState: Bundle?) = savedInstanceState == null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleOnBackPressed()
    }

    private fun handleOnBackPressed() {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                onFragmentBackPressed()
            }
        })
    }

    override fun onResume() {
        super.onResume()
        backPressedTime = 0L
    }

    open fun onFragmentBackPressed() {
        val currentTime = System.currentTimeMillis()
        if (currentTime - backPressedTime > 2000) {
            backPressedTime = currentTime
        } else {
            findNavController().navigateUp()
        }
    }

}