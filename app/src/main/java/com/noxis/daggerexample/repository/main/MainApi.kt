package com.noxis.daggerexample.repository.main

import com.noxis.daggerexample.models.Post
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface MainApi {
    @GET("posts")
    fun getPostsFromUser(@Query("userid") id: Int): Flowable<List<Post>>
}