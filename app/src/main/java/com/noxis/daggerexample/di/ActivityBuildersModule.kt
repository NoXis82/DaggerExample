package com.noxis.daggerexample.di

import com.noxis.daggerexample.AuthActivity
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import javax.inject.Inject

@Module
abstract class ActivityBuildersModule {
    private val str: String = "text test"
    @ContributesAndroidInjector
    abstract fun contributesAuthActivity(): AuthActivity
}

