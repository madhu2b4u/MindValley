package com.demo.mindvalley.main.data.repository

import androidx.lifecycle.LiveData
import com.demo.mindvalley.common.Result
import com.demo.mindvalley.main.data.models.categoriesmodel.Category
import com.demo.mindvalley.main.data.models.channelmodel.Channel
import com.demo.mindvalley.main.data.models.episodemodel.Media


interface MainRepository {

    suspend fun getCategories(mustFetchFromNetwork: Boolean): LiveData<Result<List<Category>>>
    suspend fun getChannels(mustFetchFromNetwork: Boolean): LiveData<Result<List<Channel>>>
    suspend fun getEpisodes(mustFetchFromNetwork: Boolean): LiveData<Result<List<Media>>>

}