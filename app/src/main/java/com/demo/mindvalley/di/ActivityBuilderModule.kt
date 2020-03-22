package com.demo.mindvalley.di

import com.demo.mindvalley.MainActivity
import com.demo.mindvalley.main.presentation.ui.activity.HomeActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivityBuilderModule {
    @ContributesAndroidInjector
    internal abstract fun contributesMainActivity(): MainActivity

    @ContributesAndroidInjector
    internal abstract fun contributesHomeActivity(): HomeActivity
}