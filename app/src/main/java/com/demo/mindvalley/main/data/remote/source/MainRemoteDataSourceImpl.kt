package com.demo.mindvalley.main.data.remote.source

import com.demo.mindvalley.di.qualifiers.IO
import com.demo.mindvalley.main.data.remote.services.MainService
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext


class MainRemoteDataSourceImpl @Inject constructor(
    private val service: MainService,
    @IO private val context: CoroutineContext
) : MainRemoteDataSource {
    override suspend fun getCategories() = withContext(context) {
        val response = service.getCategoriesAsync().await()

        if (response.isSuccessful)
            response.body()?.data?.categories ?: throw Exception("no categories")
        else
            throw Exception("invalid request with code ${response.code()}")

    }

    override suspend fun getChannels() = withContext(context) {
        val response = service.getChannelsAsync().await()

        if (response.isSuccessful)
            response.body()?.data?.channels ?: throw Exception("no channels")
        else
            throw Exception("invalid request with code ${response.code()}")

    }

    override suspend fun getEpisodes() = withContext(context) {
        val response = service.getEpisodesAsync().await()

        if (response.isSuccessful)
            response.body()?.data?.media ?: throw Exception("no media")
        else
            throw Exception("invalid request with code ${response.code()}")

    }
}
