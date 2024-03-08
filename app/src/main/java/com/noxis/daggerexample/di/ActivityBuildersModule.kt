package com.noxis.daggerexample.di

import com.noxis.daggerexample.MainActivity
import com.noxis.daggerexample.di.auth.AuthNetworkModule
import com.noxis.daggerexample.di.auth.AuthViewModelsModule
import com.noxis.daggerexample.di.main.MainFragmentBuildersModule
import com.noxis.daggerexample.di.main.MainNetworkModule
import com.noxis.daggerexample.di.main.MainViewModelsModule
import com.noxis.daggerexample.ui.auth.AuthActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(
        modules = [
            AuthViewModelsModule::class,
            AuthNetworkModule::class
        ]
    )
    abstract fun contributesAuthActivity(): AuthActivity

    @ContributesAndroidInjector(
        modules = [
            MainFragmentBuildersModule::class,
            MainViewModelsModule::class,
            MainNetworkModule::class
        ]
    )
    abstract fun contributesMainActivity(): MainActivity
}