package com.noxis.daggerexample.ui.main.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.noxis.daggerexample.R
import com.noxis.daggerexample.models.User
import com.noxis.daggerexample.until.AuthStatus
import com.noxis.daggerexample.viewmodels.ViewModelProviderFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class ProfileFragment : DaggerFragment() {

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory
    private var viewModel: ProfileViewModel? = null
    private var emailView: TextView? = null
    private var usernameView: TextView? = null
    private var websiteView: TextView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initDataView(view)
        viewModel = ViewModelProvider(this, providerFactory)[ProfileViewModel::class.java]
        subscribeObservers()
    }

    private fun initDataView(view: View) {
        emailView = view.findViewById(R.id.email)
        usernameView = view.findViewById(R.id.username)
        websiteView = view.findViewById(R.id.website)
    }

    private fun subscribeObservers() {
        viewModel?.getAuthenticateUser()?.removeObservers(viewLifecycleOwner)
        viewModel?.getAuthenticateUser()?.observe(viewLifecycleOwner) { userAuthResource ->
            userAuthResource?.let {
                when (it.status) {
                    AuthStatus.AUTHENTICATED -> setUserDetails(it.data)
                    AuthStatus.ERROR -> setErrorDetails(it.message)
                    else -> {}
                }
            }
        }
    }

    private fun setErrorDetails(message: String?) {
        emailView?.text = activity?.applicationContext?.getString(R.string.email, message)
        usernameView?.text = activity?.applicationContext?.getString(R.string.error)
        websiteView?.text = activity?.applicationContext?.getString(R.string.error)
    }

    private fun setUserDetails(user: User?) {
        emailView?.text = activity?.applicationContext?.getString(R.string.email, user?.email)
        usernameView?.text =
            activity?.applicationContext?.getString(R.string.username, user?.username)
        websiteView?.text = activity?.applicationContext?.getString(R.string.website, user?.website)
    }

    companion object {
        private const val TAG = "ProfileFragment"
    }
}