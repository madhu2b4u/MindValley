package com.demo.mindvalley.main.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.demo.mindvalley.common.ViewModelFactory
import com.demo.mindvalley.di.ViewModelKey
import com.demo.mindvalley.main.presentation.viewmodel.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainPresentationModule {
    @Binds
    abstract fun bindsViewModelFactory(
        factory: ViewModelFactory
    ): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindsHomeViewModel(mMainViewModel: MainViewModel): ViewModel
}