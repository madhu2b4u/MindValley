package com.demo.mindvalley.main.data.repository


import com.demo.mindvalley.main.data.remote.source.MainRemoteDataSource
import javax.inject.Inject


class MainRepositoryImpl @Inject constructor(
    private val remoteDataSource: MainRemoteDataSource
) : MainRepository {



}