package com.noxis.daggerexample.ui.main.profile

import android.util.Log
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class ProfileViewModel @Inject constructor() : ViewModel() {

    init {
        Log.d(TAG, "ProfileViewModel init")
    }

    companion object {
        private const val TAG = "ProfileViewMode"
    }
}