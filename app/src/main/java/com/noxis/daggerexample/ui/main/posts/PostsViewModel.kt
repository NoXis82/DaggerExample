package com.noxis.daggerexample.ui.main.posts

import android.util.Log
import androidx.lifecycle.ViewModel
import com.noxis.daggerexample.SessionManager
import com.noxis.daggerexample.repository.main.MainApi
import javax.inject.Inject

class PostsViewModel @Inject constructor(
    private val sessionManager: SessionManager,
    private val mainApi: MainApi
) : ViewModel() {

    init {
        Log.d(TAG, "PostsViewModel init ")
    }

    companion object {
        private const val TAG = "PostsViewModel"
    }
}