package com.amotrade.trendtenhub.data.models.schemas

import com.google.gson.annotations.SerializedName

data class BuiltBySchema(
    @SerializedName("username") val username: String? = null,
    @SerializedName("href") val href: String? = null,
    @SerializedName("avatar") val avatar: String? = null
)
