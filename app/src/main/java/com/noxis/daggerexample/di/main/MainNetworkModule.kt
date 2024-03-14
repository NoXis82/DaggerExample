package com.noxis.daggerexample.di.main

import com.noxis.daggerexample.repository.main.MainApi
import com.noxis.daggerexample.ui.main.posts.PostsRecyclerAdapter
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class MainNetworkModule {
    @MainScope
    @Provides
    fun provideMainApi(retrofit: Retrofit): MainApi {
        return retrofit.create(MainApi::class.java)
    }

    @MainScope
    @Provides
    fun provideAdapter(): PostsRecyclerAdapter {
        return PostsRecyclerAdapter()
    }

}