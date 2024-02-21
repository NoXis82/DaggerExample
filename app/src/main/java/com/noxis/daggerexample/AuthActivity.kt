package com.noxis.daggerexample

import android.os.Bundle
import dagger.android.support.DaggerAppCompatActivity

class AuthActivity : DaggerAppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
    }

    companion object {
        private const val TAG = "AuthActivity"
    }
}