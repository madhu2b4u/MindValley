package com.demo.mindvalley.main.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "db_categories")
data class DbCategories(
    @PrimaryKey val id: Int,
    val categories: String
)