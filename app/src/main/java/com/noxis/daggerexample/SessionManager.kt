package com.noxis.daggerexample

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.noxis.daggerexample.models.User
import com.noxis.daggerexample.until.Resource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SessionManager @Inject constructor() {
    private val cachedUser = MediatorLiveData<Resource<User>>()

    fun authenticateWithId(source: LiveData<Resource<User>>) {
        if (cachedUser.isInitialized) {
            cachedUser.value = Resource.Loading()
            cachedUser.addSource(source) {
                cachedUser.value = it
                cachedUser.removeSource(source)
            }
        }
    }

    fun logOut() {
        Log.d(TAG, "logOut: log out...")
        cachedUser.value = Resource.Logout()
    }

    fun getAuthUser(): LiveData<Resource<User>> = cachedUser

    companion object {
        private const val TAG = "SessionManager"
    }
}