package com.demo.mindvalley.di

import com.demo.mindvalley.main.presentation.ui.fragments.MainFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilderModule {

    @ContributesAndroidInjector
    abstract fun contributesMainFragment(): MainFragment

}