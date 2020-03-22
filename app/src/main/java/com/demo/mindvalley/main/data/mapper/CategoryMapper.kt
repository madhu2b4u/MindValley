package com.demo.mindvalley.main.data.mapper

import com.demo.mindvalley.common.Mapper
import com.demo.mindvalley.main.data.local.entities.DbCategories
import com.demo.mindvalley.main.data.models.categoriesmodel.Category
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import javax.inject.Inject

class CategoryMapper @Inject constructor(val gson: Gson) : Mapper<DbCategories, List<Category>> {
    override fun from(e: List<Category>) = DbCategories(1, gson.toJson(e))
    override fun to(t: DbCategories): List<Category> {
        return gson.fromJson(
            t.categories,
            TypeToken.getParameterized(ArrayList::class.java, Category::class.java).type
        )
    }
}