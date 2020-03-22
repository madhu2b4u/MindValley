package com.demo.mindvalley.main.data.models.channelmodel


import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Channel(
    @Expose
    @SerializedName("coverAsset")
    var coverAsset: CoverAsset,
    @Expose
    @SerializedName("iconAsset")
    var iconAsset: IconAsset,
    @Expose
    @SerializedName("id")
    var id: String,
    @Expose
    @SerializedName("latestMedia")
    var latestMedia: List<LatestMedia>,
    @Expose
    @SerializedName("mediaCount")
    var mediaCount: Int,
    @Expose
    @SerializedName("series")
    var series: List<Sery>,
    @Expose
    @SerializedName("slug")
    var slug: String,
    @Expose
    @SerializedName("title")
    var title: String
) : Parcelable