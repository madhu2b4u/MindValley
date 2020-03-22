package com.demo.mindvalley.main.data.models.episodemodel


import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class EpisodesResponse(
    @Expose
    @SerializedName("data")
    var data: Data
) : Parcelable