package com.noxis.daggerexample.until

sealed class PostsResource<T>(
    val status: Status,
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T?) : PostsResource<T>(Status.SUCCESS, data)
    class Error<T>(message: String, data: T? = null) : PostsResource<T>(Status.ERROR, data, message)

    class Loading<T>() : PostsResource<T>(Status.LOADING)
}