package com.demo.mindvalley.main.data.remote.source

import com.demo.mindvalley.main.data.models.categoriesmodel.Category
import com.demo.mindvalley.main.data.models.channelmodel.Channel
import com.demo.mindvalley.main.data.models.episodemodel.Media


interface MainRemoteDataSource {
    suspend fun getCategories(): List<Category>
    suspend fun getChannels(): List<Channel>
    suspend fun getEpisodes(): List<Media>

}