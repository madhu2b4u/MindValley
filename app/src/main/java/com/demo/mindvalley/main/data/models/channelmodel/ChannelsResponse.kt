package com.demo.mindvalley.main.data.models.channelmodel


import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ChannelsResponse(
    @Expose
    @SerializedName("data")
    var data: Data
) : Parcelable