package com.demo.mindvalley.main.data.local.database

import android.content.Context
import androidx.annotation.NonNull
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.demo.mindvalley.main.data.local.entities.DbCategories
import com.demo.mindvalley.main.data.local.entities.DbChannels
import com.demo.mindvalley.main.data.local.entities.DbEpisodes


@Database(
    entities = [DbCategories::class, DbChannels::class, DbEpisodes::class],
    version = 1,
    exportSchema = false
)
abstract class MediaDatabase : RoomDatabase() {

    companion object {
        private val LOCK = Any()
        private const val DATABASE_NAME = "media.db"
        @Volatile
        private var INSTANCE: MediaDatabase? = null

        fun getInstance(@NonNull context: Context): MediaDatabase {
            if (INSTANCE == null) {
                synchronized(LOCK) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context,
                            MediaDatabase::class.java,
                            DATABASE_NAME
                        ).build()
                    }
                }
            }
            return INSTANCE!!
        }
    }

    abstract fun getMediaDao(): MediaDao

}