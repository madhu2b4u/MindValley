package com.demo.mindvalley.main.data.local.source

import com.demo.mindvalley.di.qualifiers.IO
import com.demo.mindvalley.main.data.local.database.MediaDao
import com.demo.mindvalley.main.data.mapper.CategoryMapper
import com.demo.mindvalley.main.data.mapper.ChannelMapper
import com.demo.mindvalley.main.data.mapper.EpisodeMapper
import com.demo.mindvalley.main.data.models.categoriesmodel.Category
import com.demo.mindvalley.main.data.models.channelmodel.Channel
import com.demo.mindvalley.main.data.models.episodemodel.Media
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class LocalDataSourceImpl @Inject constructor(
    private val mediaDao: MediaDao,
    private val categoryMapper: CategoryMapper,
    private val channelMapper: ChannelMapper,
    private val episodeMapper: EpisodeMapper,
    @IO private val context: CoroutineContext
) : LocalDataSource {

    override suspend fun getCategories() = withContext(context) {
        val categoryEntity = mediaDao.getCategories()
        if (categoryEntity != null)
            categoryMapper.to(categoryEntity)
        else
            null
    }

    override suspend fun saveCategories(list: List<Category>) = withContext(context) {
        val category = categoryMapper.from(list)
        mediaDao.saveCategory(category)
    }

    override suspend fun getEpisodes() = withContext(context) {
        val episodeEntity = mediaDao.getEpisodes()
        if (episodeEntity != null)
            episodeMapper.to(episodeEntity)
        else
            null
    }

    override suspend fun saveEpisodes(episodes: List<Media>) = withContext(context) {
        val episode = episodeMapper.from(episodes)
        mediaDao.saveEpisodes(episode)
    }

    override suspend fun getChannels(): List<Channel>? = withContext(context) {
        val channelEntity = mediaDao.getChannels()
        if (channelEntity != null)
            channelMapper.to(channelEntity)
        else
            null
    }

    override suspend fun saveChannels(channels: List<Channel>) = withContext(context) {
        val channel = channelMapper.from(channels)
        mediaDao.saveChannels(channel)
    }

}