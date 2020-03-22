package com.demo.mindvalley.main.data.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.demo.mindvalley.main.data.local.entities.DbCategories
import com.demo.mindvalley.main.data.local.entities.DbChannels
import com.demo.mindvalley.main.data.local.entities.DbEpisodes

@Dao
abstract class MediaDao {
    @Query("SELECT * FROM db_categories")
    abstract suspend fun getCategories(): DbCategories?

    @Insert(onConflict = REPLACE)
    abstract fun saveCategory(categories: DbCategories)

    @Query("SELECT * FROM db_channels")
    abstract suspend fun getChannels(): DbChannels?

    @Insert(onConflict = REPLACE)
    abstract fun saveChannels(channels: DbChannels)

    @Query("SELECT * FROM db_episodes")
    abstract suspend fun getEpisodes(): DbEpisodes?

    @Insert(onConflict = REPLACE)
    abstract fun saveEpisodes(episodes: DbEpisodes)
}