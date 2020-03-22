package com.demo.mindvalley.main.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "db_episodes")
data class DbEpisodes(
    @PrimaryKey val id: Int,
    val episodes: String
)