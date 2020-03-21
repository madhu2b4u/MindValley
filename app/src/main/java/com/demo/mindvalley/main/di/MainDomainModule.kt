package com.demo.mindvalley.main.di

import com.demo.mindvalley.main.data.repository.MainRepository
import com.demo.mindvalley.main.data.repository.MainRepositoryImpl
import com.demo.mindvalley.main.domain.UseCaseImpl
import com.demo.mindvalley.main.domain.UseCase
import dagger.Binds
import dagger.Module

@Module
abstract class MainDomainModule {
    @Binds
    abstract fun bindsRepository(
        repoImpl: MainRepositoryImpl
    ): MainRepository
    @Binds
    abstract fun bindsHomeUseCase(
        mUseCase: UseCaseImpl
    ): UseCase


}