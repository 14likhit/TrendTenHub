package com.amotrade.trendtenhub.data.models.schemas

import com.google.gson.annotations.SerializedName

data class GithubRepositorySchema(
    @SerializedName("author") val author: String? = null,
    @SerializedName("name") val name: String? = null,
    @SerializedName("avatar") val avatar: String? = null,
    @SerializedName("url") val url: String? = null,
    @SerializedName("description") val description: String? = null,
    @SerializedName("language") val language: String? = null,
    @SerializedName("languageColor") val languageColor: String? = null,
    @SerializedName("stars") val stars: Int? = null,
    @SerializedName("forks") val forks: Int? = null,
    @SerializedName("currentPeriodStars") val currentPeriodStars: Int? = null,
    @SerializedName("builtBy") val builtBy: List<BuiltBySchema>? = null
)
