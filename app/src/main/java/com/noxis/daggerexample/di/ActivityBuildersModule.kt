package com.noxis.daggerexample.di

import com.noxis.daggerexample.di.auth.AuthViewModelsModule
import com.noxis.daggerexample.ui.auth.AuthActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(
        modules = [AuthViewModelsModule::class]
    )
    abstract fun contributesAuthActivity(): AuthActivity
}

