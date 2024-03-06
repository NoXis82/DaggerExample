package com.noxis.daggerexample.di.main

import androidx.lifecycle.ViewModel
import com.noxis.daggerexample.di.ViewModelKey
import com.noxis.daggerexample.ui.main.profile.ProfileViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface MainViewModelsModule {
    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel::class)
    fun bindAuthViewModel(profileViewModel: ProfileViewModel): ViewModel
}