package com.noxis.daggerexample.di.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.noxis.daggerexample.di.ViewModelKey
import com.noxis.daggerexample.ui.auth.AuthViewModel
import com.noxis.daggerexample.viewmodels.ViewModelProviderFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface AuthViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel::class)
    fun bindAuthViewModel(authViewModel: AuthViewModel): ViewModel

}