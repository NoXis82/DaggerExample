package com.noxis.daggerexample.repository.auth

import com.noxis.daggerexample.models.User
import io.reactivex.Flowable
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface AuthApi {
    @GET("users/{id}")
    fun getFakeStuff(@Path("id") id: Int): Flowable<User>

}