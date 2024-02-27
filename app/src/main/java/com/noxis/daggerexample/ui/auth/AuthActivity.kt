package com.noxis.daggerexample.ui.auth

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.RequestManager
import com.noxis.daggerexample.R
import com.noxis.daggerexample.viewmodels.ViewModelProviderFactory
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class AuthActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory

    private var viewModel: AuthViewModel? = null

    @Inject
    lateinit var logo: Drawable

    @Inject
    lateinit var requestManager: RequestManager
    private var userId: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        viewModel = ViewModelProvider(this, providerFactory)[AuthViewModel::class.java]
        userId = findViewById(R.id.user_id_input)
        findViewById<Button>(R.id.login_button).setOnClickListener {
            when (it.id) {
                R.id.login_button -> {
                    attemptLogin()
                }
            }
        }
        setLogo()
        subscribeObservers()
    }

    private fun subscribeObservers() {
        viewModel?.observerUser()?.observe(this) {
            if (it != null) Log.d(TAG, "User: $it")
        }
    }

    private fun attemptLogin() {
        if (TextUtils.isEmpty(userId?.text.toString())) return
        viewModel?.authenticateWithId(userId?.text.toString().toInt())
    }

    private fun setLogo() {
        requestManager.load(logo).into((findViewById<ImageView>(R.id.login_logo)))
    }

    companion object {
        private const val TAG = "AuthActivity"
    }
}