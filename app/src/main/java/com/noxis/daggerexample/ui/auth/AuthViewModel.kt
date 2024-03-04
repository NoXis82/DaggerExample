package com.noxis.daggerexample.ui.auth

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.noxis.daggerexample.SessionManager
import com.noxis.daggerexample.models.User
import com.noxis.daggerexample.repository.auth.AuthApi
import com.noxis.daggerexample.until.Resource
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@SuppressLint("CheckResult")
class AuthViewModel @Inject constructor(
    private val authApi: AuthApi,
    private val sessionManager: SessionManager
) : ViewModel() {

    private val authUser = MediatorLiveData<Resource<User>>()
    fun authenticateWithId(userId: Int) {
        Log.d(TAG, "authenticateWithId: attempting to login")
        sessionManager.authenticateWithId(queryUserId(userId))
    }

    private fun queryUserId(userId: Int): LiveData<Resource<User>> {
        authApi.getFakeStuff(userId)
            .subscribeOn(Schedulers.io())
            .toObservable()
            .subscribe(
                { user ->
                    authUser.postValue(user?.let {
                        Resource.Authenticated(data = it)
                    } ?: Resource.Error(message = "User not found"))
                },
                {
                    authUser.postValue(Resource.Error(message = "Could not authenticate"))
                }
            )
        return authUser
    }

    fun observerAuthState(): LiveData<Resource<User>> = sessionManager.getAuthUser()

    companion object {
        private const val TAG = "AuthViewModel"
    }
}