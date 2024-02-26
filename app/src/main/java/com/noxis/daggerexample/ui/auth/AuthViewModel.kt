package com.noxis.daggerexample.ui.auth

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.noxis.daggerexample.models.User
import com.noxis.daggerexample.repository.auth.AuthApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@SuppressLint("CheckResult")
class AuthViewModel @Inject constructor(private val authApi: AuthApi) : ViewModel() {

    private val authUser = MediatorLiveData<User>()
    fun authenticateWithId(userId: Int) {
        authApi.getFakeStuff(userId)
            .subscribeOn(Schedulers.io())
            .toObservable()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { authUser.value = it }
    }

    fun observerUser(): LiveData<User> = authUser

    companion object {
        private const val TAG = "AuthViewModel"
    }
}