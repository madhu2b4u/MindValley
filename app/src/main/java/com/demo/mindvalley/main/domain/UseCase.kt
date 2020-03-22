package com.demo.mindvalley.main.domain

import androidx.lifecycle.LiveData
import com.demo.mindvalley.common.Result
import com.demo.mindvalley.main.data.models.categoriesmodel.Category
import com.demo.mindvalley.main.data.models.channelmodel.Channel
import com.demo.mindvalley.main.data.models.episodemodel.Media

interface UseCase {

    suspend fun getEpisodes(mustFetchFromNetwork: Boolean = false): LiveData<Result<List<Media>>>
    suspend fun getChannels(mustFetchFromNetwork: Boolean = false): LiveData<Result<List<Channel>>>
    suspend fun getCategories(mustFetchFromNetwork: Boolean = false): LiveData<Result<List<Category>>>



}