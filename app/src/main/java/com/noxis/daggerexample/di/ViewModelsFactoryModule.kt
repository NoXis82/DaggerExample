package com.noxis.daggerexample.di

import androidx.lifecycle.ViewModelProvider
import com.noxis.daggerexample.viewmodels.ViewModelProviderFactory
import dagger.Binds
import dagger.Module

@Module
interface ViewModelsFactoryModule {

    @Binds
    fun bindViewModelsFactory(viewModelProviderFactory: ViewModelProviderFactory): ViewModelProvider.Factory

}