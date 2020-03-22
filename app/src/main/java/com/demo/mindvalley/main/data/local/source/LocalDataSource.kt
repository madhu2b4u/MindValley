package com.demo.mindvalley.main.data.local.source

import com.demo.mindvalley.main.data.models.categoriesmodel.Category
import com.demo.mindvalley.main.data.models.channelmodel.Channel
import com.demo.mindvalley.main.data.models.episodemodel.Media

interface LocalDataSource {
    suspend fun getCategories(): List<Category>?
    suspend fun saveCategories(category: List<Category>)

    suspend fun getEpisodes(): List<Media>?
    suspend fun saveEpisodes(episodes: List<Media>)

    suspend fun getChannels(): List<Channel>?
    suspend fun saveChannels(channels: List<Channel>)
}