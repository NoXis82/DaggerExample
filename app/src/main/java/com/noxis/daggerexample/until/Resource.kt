package com.noxis.daggerexample.until

sealed class Resource<T>(val status: AuthStatus, val data: T? = null, val message: String? = null) {
    class Authenticated<T>(data: T?) : Resource<T>(AuthStatus.AUTHENTICATED, data)
    class Error<T>(message: String, data: T? = null) : Resource<T>(AuthStatus.ERROR, data, message)

    class Loading<T>() : Resource<T>(AuthStatus.LOADING)

    class Logout<T>() : Resource<T>(AuthStatus.NOT_AUTHENTICATED)
}