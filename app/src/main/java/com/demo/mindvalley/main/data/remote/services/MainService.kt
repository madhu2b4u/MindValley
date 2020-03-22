package com.demo.mindvalley.main.data.remote.services

import com.demo.mindvalley.main.data.models.categoriesmodel.CategoriesResponse
import com.demo.mindvalley.main.data.models.channelmodel.ChannelsResponse
import com.demo.mindvalley.main.data.models.episodemodel.EpisodesResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface MainService {

    //Episodes
    @GET("z5AExTtw")
    fun getEpisodesAsync(): Deferred<Response<EpisodesResponse>>

    //Channels
    @GET("Xt12uVhM")
    fun getChannelsAsync(): Deferred<Response<ChannelsResponse>>

    //Channels
    @GET("A0CgArX3")
    fun getCategoriesAsync(): Deferred<Response<CategoriesResponse>>

}