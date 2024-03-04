package com.noxis.daggerexample

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.noxis.daggerexample.ui.auth.AuthActivity
import com.noxis.daggerexample.until.AuthStatus
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

abstract class BaseActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        subscribeObserver()
    }

    private fun subscribeObserver() {
        sessionManager.getAuthUser().observe(this) {
            it?.let { resourcesUser ->
                when (resourcesUser.status) {
                    AuthStatus.LOADING -> {}

                    AuthStatus.ERROR -> {
                        Log.e(TAG, "Error: ${resourcesUser.message}")
                    }

                    AuthStatus.AUTHENTICATED -> {
                        Log.d(TAG, "AUTHENTICATED: ${resourcesUser.data}")
                    }

                    AuthStatus.NOT_AUTHENTICATED -> navLoginScreen()
                }
            }
        }
    }

    private fun navLoginScreen() {
        startActivity(Intent(this, AuthActivity::class.java))
        finish()
    }

    companion object {
        private const val TAG = "BaseActivity"
    }
}