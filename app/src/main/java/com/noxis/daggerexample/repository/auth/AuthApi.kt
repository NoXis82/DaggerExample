package com.noxis.daggerexample.repository.auth

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET

interface AuthApi {
    @GET("/users")
    fun getFakeStuff(): Call<ResponseBody>

}