package com.demo.mindvalley.main.data.models.channelmodel

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Sery(
    @Expose
    @SerializedName("coverAsset")
    var coverAsset: CoverAssetXX,
    @Expose
    @SerializedName("id")
    var id: String,
    @Expose
    @SerializedName("title")
    var title: String
) : Parcelable