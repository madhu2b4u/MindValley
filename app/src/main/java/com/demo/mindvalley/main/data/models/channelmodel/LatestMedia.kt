package com.demo.mindvalley.main.data.models.channelmodel


import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class LatestMedia(
    @Expose
    @SerializedName("coverAsset")
    var coverAsset: CoverAssetX,
    @Expose
    @SerializedName("title")
    var title: String,
    @Expose
    @SerializedName("type")
    var type: String
) : Parcelable