package com.demo.mindvalley.main.data.mapper

import com.demo.mindvalley.common.Mapper
import com.demo.mindvalley.main.data.local.entities.DbEpisodes
import com.demo.mindvalley.main.data.models.episodemodel.Media
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import javax.inject.Inject

class EpisodeMapper @Inject constructor(val gson: Gson) : Mapper<DbEpisodes, List<Media>> {
    override fun from(e: List<Media>) = DbEpisodes(1, gson.toJson(e))
    override fun to(t: DbEpisodes): List<Media> {
        return gson.fromJson(
            t.episodes, TypeToken.getParameterized(ArrayList::class.java, Media::class.java).type
        )
    }
}