package com.demo.mindvalley.main.data.mapper

import com.demo.mindvalley.common.Mapper
import com.demo.mindvalley.main.data.local.entities.DbChannels
import com.demo.mindvalley.main.data.models.channelmodel.Channel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import javax.inject.Inject

class ChannelMapper @Inject constructor(val gson: Gson) : Mapper<DbChannels, List<Channel>> {
    override fun from(e: List<Channel>) = DbChannels(1, gson.toJson(e))
    override fun to(t: DbChannels): List<Channel> {
        return gson.fromJson(
            t.channels, TypeToken.getParameterized(ArrayList::class.java, Channel::class.java).type
        )
    }
}