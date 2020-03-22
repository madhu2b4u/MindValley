package com.demo.mindvalley.main.di

import android.app.Application
import com.demo.mindvalley.main.data.local.database.MediaDatabase
import com.demo.mindvalley.main.data.local.source.LocalDataSource
import com.demo.mindvalley.main.data.local.source.LocalDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module(includes = [MainLocalModule.Binders::class])
class MainLocalModule {

    @Module
    interface Binders {
        @Binds
        fun bindsLocalDataSource(
            localDataSourceImpl: LocalDataSourceImpl
        ): LocalDataSource
    }

    @Provides
    @Singleton
    fun providesDatabase(
        application: Application
    ) = MediaDatabase.getInstance(application.applicationContext)

    @Provides
    @Singleton
    fun providesUserDao(
        mediaDatabase: MediaDatabase
    ) = mediaDatabase.getMediaDao()


}