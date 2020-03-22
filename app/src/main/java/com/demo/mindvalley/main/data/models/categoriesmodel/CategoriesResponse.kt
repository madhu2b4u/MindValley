package com.demo.mindvalley.main.data.models.categoriesmodel

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CategoriesResponse(
    @Expose
    @SerializedName("data")
    var data: Data
) : Parcelable