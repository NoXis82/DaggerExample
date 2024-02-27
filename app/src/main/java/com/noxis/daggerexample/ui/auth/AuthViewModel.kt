package com.noxis.daggerexample.ui.auth

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.noxis.daggerexample.models.User
import com.noxis.daggerexample.repository.auth.AuthApi
import com.noxis.daggerexample.until.Resource
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@SuppressLint("CheckResult")
class AuthViewModel @Inject constructor(private val authApi: AuthApi) : ViewModel() {

    private val authUser = MediatorLiveData<Resource<User>>()
    fun authenticateWithId(userId: Int) {
        authUser.postValue(Resource.Loading())
        authApi.getFakeStuff(userId)
            .subscribeOn(Schedulers.io())
            .toObservable()
            .subscribe(
                { user ->
                    user?.let {
                        authUser.postValue(Resource.Authenticated(data = it))
                    } ?: authUser.postValue(Resource.Error(message = "User not found"))
                },
                {
                    authUser.postValue(Resource.Error(message = "Could not authenticate"))
                }
            )
    }

    fun observerUser(): LiveData<Resource<User>> = authUser

    companion object {
        private const val TAG = "AuthViewModel"
    }
}