package com.noxis.daggerexample.di.main

import com.noxis.daggerexample.repository.main.MainApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class MainNetworkModule {
    @Provides
    fun provideMainApi(retrofit: Retrofit): MainApi {
        return retrofit.create(MainApi::class.java)
    }

}