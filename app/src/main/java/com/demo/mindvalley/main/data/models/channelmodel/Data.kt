package com.demo.mindvalley.main.data.models.channelmodel


import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Data(
    @Expose
    @SerializedName("channels")
    var channels: List<Channel>
) : Parcelable