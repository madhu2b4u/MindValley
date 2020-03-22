package com.demo.mindvalley.main.data.models.categoriesmodel


import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Data(
    @Expose
    @SerializedName("categories")
    var categories: List<Category>
) : Parcelable