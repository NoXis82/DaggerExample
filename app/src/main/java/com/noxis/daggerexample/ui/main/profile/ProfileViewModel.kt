package com.noxis.daggerexample.ui.main.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.noxis.daggerexample.SessionManager
import com.noxis.daggerexample.models.User
import com.noxis.daggerexample.until.Resource
import javax.inject.Inject

class ProfileViewModel @Inject constructor(
    private val sessionManager: SessionManager
) : ViewModel() {

    fun getAuthenticateUser(): LiveData<Resource<User>> = sessionManager.getAuthUser()

    companion object {
        private const val TAG = "ProfileViewMode"
    }
}