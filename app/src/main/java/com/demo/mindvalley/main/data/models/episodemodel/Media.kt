package com.demo.mindvalley.main.data.models.episodemodel

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Media(
    @Expose
    @SerializedName("channel")
    var channel: Channel,
    @Expose
    @SerializedName("coverAsset")
    var coverAsset: CoverAsset,
    @Expose
    @SerializedName("title")
    var title: String,
    @Expose
    @SerializedName("type")
    var type: String
) : Parcelable