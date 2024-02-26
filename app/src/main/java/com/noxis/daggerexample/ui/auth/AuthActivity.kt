package com.noxis.daggerexample.ui.auth

import android.graphics.drawable.Drawable
import android.os.Bundle
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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        viewModel = ViewModelProvider(this, providerFactory)[AuthViewModel::class.java]
        setLogo()
    }

    private fun setLogo() {
        requestManager.load(logo).into((findViewById<ImageView>(R.id.login_logo)))
    }

    companion object {
        private const val TAG = "AuthActivity"
    }
}