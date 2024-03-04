package com.noxis.daggerexample.ui.auth

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.RequestManager
import com.noxis.daggerexample.MainActivity
import com.noxis.daggerexample.R
import com.noxis.daggerexample.until.AuthStatus
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
    private var progressBar: ProgressBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        viewModel = ViewModelProvider(this, providerFactory)[AuthViewModel::class.java]
        userId = findViewById(R.id.user_id_input)
        progressBar = findViewById(R.id.progress_bar)
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
        viewModel?.observerAuthState()?.observe(this) {
            it?.let { resourcesUser ->
                when (resourcesUser.status) {
                    AuthStatus.LOADING -> progressBar?.visibility = View.VISIBLE

                    AuthStatus.ERROR -> {
                        Log.e(TAG, "Error: ${resourcesUser.message}")
                        progressBar?.visibility = View.GONE
                        Toast.makeText(
                            this,
                            "${resourcesUser.message}. Did you enter a number between 0 and 10?",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    AuthStatus.AUTHENTICATED -> {
                        progressBar?.visibility = View.GONE
                        Log.d(TAG, "AUTHENTICATED: ${resourcesUser.data}")
                        onLoginSuccess()
                    }

                    AuthStatus.NOT_AUTHENTICATED -> {
                        progressBar?.visibility = View.GONE
                    }
                }
            }
        }
    }

    private fun attemptLogin() {
        if (TextUtils.isEmpty(userId?.text.toString())) return
        viewModel?.authenticateWithId(userId?.text.toString().toInt())
    }

    private fun setLogo() {
        requestManager.load(logo).into((findViewById<ImageView>(R.id.login_logo)))
    }

    private fun onLoginSuccess() {
        startActivity(Intent(this, MainActivity::class.java))
    }

    companion object {
        private const val TAG = "AuthActivity"
    }
}