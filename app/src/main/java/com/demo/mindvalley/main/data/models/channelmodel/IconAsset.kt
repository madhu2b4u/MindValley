package com.demo.mindvalley.main.data.models.channelmodel


import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class IconAsset(
    @Expose
    @SerializedName("thumbnailUrl")
    var thumbnailUrl: String,
    @Expose
    @SerializedName("url")
    var url: String
) : Parcelable