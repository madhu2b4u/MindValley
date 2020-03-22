package com.demo.mindvalley.main.data.repository

import androidx.lifecycle.liveData
import com.demo.mindvalley.common.Result
import com.demo.mindvalley.main.data.local.source.LocalDataSource
import com.demo.mindvalley.main.data.models.categoriesmodel.Category
import com.demo.mindvalley.main.data.models.channelmodel.Channel
import com.demo.mindvalley.main.data.models.episodemodel.Media
import com.demo.mindvalley.main.data.remote.source.MainRemoteDataSource
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val remoteDataSource: MainRemoteDataSource,
    private val localDataSource: LocalDataSource
) : MainRepository {

    override suspend fun getCategories(mustFetchFromNetwork: Boolean) = liveData {
        emit(Result.loading())
        try {
            var categories: List<Category>? = null
            if (!mustFetchFromNetwork)
                categories = localDataSource.getCategories()

            if (categories == null) {
                categories = remoteDataSource.getCategories()
                localDataSource.saveCategories(categories)
            }
            emit(Result.success(categories))
        } catch (exception: Exception) {
            emit(Result.error(exception.message ?: ""))
        }
    }


    override suspend fun getChannels(mustFetchFromNetwork: Boolean) = liveData {
        emit(Result.loading())
        try {
            var channels: List<Channel>? = null
            if (!mustFetchFromNetwork)
                channels = localDataSource.getChannels()

            if (channels == null) {
                channels = remoteDataSource.getChannels()
                localDataSource.saveChannels(channels)
            }
            emit(Result.success(channels))
        } catch (exception: Exception) {
            emit(Result.error(exception.message ?: ""))
        }
    }

    override suspend fun getEpisodes(mustFetchFromNetwork: Boolean) = liveData {
        emit(Result.loading())
        try {
            var episodes: List<Media>? = null
            if (!mustFetchFromNetwork)
                episodes = localDataSource.getEpisodes()

            if (episodes == null) {
                episodes = remoteDataSource.getEpisodes()
                localDataSource.saveEpisodes(episodes)
            }
            emit(Result.success(episodes))
        } catch (exception: Exception) {
            emit(Result.error(exception.message ?: ""))
        }
    }



}