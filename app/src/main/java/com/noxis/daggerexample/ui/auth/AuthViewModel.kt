package com.noxis.daggerexample.ui.auth

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.ViewModel
import com.noxis.daggerexample.repository.auth.AuthApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@SuppressLint("CheckResult")
class AuthViewModel @Inject constructor(authApi: AuthApi) : ViewModel() {

    init {
        Log.d(TAG, "AuthViewModel init")
        authApi.getFakeStuff(1)
            .toObservable()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    Log.d(TAG, "onNext: $it")
                },
                {
                    Log.e(TAG, "onError: ", it)
                },
                {
                    Log.i(TAG, "onComplete")
                }
            )
    }

    companion object {
        private const val TAG = "AuthViewModel"
    }
}