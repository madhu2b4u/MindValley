package com.demo.mindvalley.di

import android.app.Application
import com.demo.mindvalley.MindValleyApp
import com.demo.mindvalley.main.di.MainDomainModule
import com.demo.mindvalley.main.di.MainLocalModule
import com.demo.mindvalley.main.di.MainPresentationModule
import com.demo.mindvalley.main.di.MainRemoteModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        NetworkModule::class,
        FragmentBuilderModule::class,
        ActivityBuilderModule::class,
        AppModule::class, MainDomainModule::class, MainRemoteModule::class, MainPresentationModule::class, MainLocalModule::class
    ]
)
interface AppComponent : AndroidInjector<MindValleyApp> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: Application): Builder
        fun build(): AppComponent
    }

    override fun inject(app: MindValleyApp)
}