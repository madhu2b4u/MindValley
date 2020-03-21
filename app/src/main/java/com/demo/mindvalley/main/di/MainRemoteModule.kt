package com.demo.mindvalley.main.di


import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import com.demo.mindvalley.main.data.remote.source.MainRemoteDataSource
import com.demo.mindvalley.main.data.remote.source.MainRemoteDataSourceImpl
import com.demo.mindvalley.main.data.remote.services.MainService


@Module(includes = [MainRemoteModule.Binders::class])
class MainRemoteModule {
    @Module
    interface Binders {
        @Binds
        fun bindsRemoteSource(
            remoteDataSourceImpl: MainRemoteDataSourceImpl
        ): MainRemoteDataSource
    }
    @Provides
    fun providesMainService(retrofit: Retrofit): MainService =
        retrofit.create(MainService::class.java)
}