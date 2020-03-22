package com.demo.mindvalley.main.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "db_channels")
data class DbChannels(
    @PrimaryKey val id: Int,
    val channels: String
)