package com.amotrade.trendtenhub.utils

import com.amotrade.trendtenhub.data.models.BuiltByData
import com.amotrade.trendtenhub.data.models.GithubRepositoryData
import com.amotrade.trendtenhub.data.models.schemas.BuiltBySchema
import com.amotrade.trendtenhub.data.models.schemas.GithubRepositorySchema

fun getTrendingGithubRepositoryDataList(githubTrendingRepositorySchemaList: List<GithubRepositorySchema>): List<GithubRepositoryData> {
    val githubRepositoryDataList = mutableListOf<GithubRepositoryData>()
    for (githubTrendingRepositorySchema in githubTrendingRepositorySchemaList) {
        githubRepositoryDataList.add(getTrendingGithubRepositoryData(githubTrendingRepositorySchema))
    }
    return githubRepositoryDataList
}

fun getTrendingGithubRepositoryData(githubRepositorySchema: GithubRepositorySchema): GithubRepositoryData {
    return GithubRepositoryData(
        githubRepositorySchema.author ?: "",
        githubRepositorySchema.name ?: "",
        githubRepositorySchema.avatar ?: "",
        githubRepositorySchema.url ?: "",
        githubRepositorySchema.description ?: "",
        githubRepositorySchema.language ?: "",
        githubRepositorySchema.languageColor ?: "",
        githubRepositorySchema.stars ?: -1,
        githubRepositorySchema.forks ?: -1,
        githubRepositorySchema.currentPeriodStars ?: -1,
        getBuiltByDataList(githubRepositorySchema.builtBy),
    )
}

fun getTrendingGithubRepositoryEmptyData(): GithubRepositoryData {
    return GithubRepositoryData(
        "", "", "", "", "", "", "",
        -1, -1, -1, getBuiltByDataList(null)
    )
}

fun getBuiltByDataList(builtBySchemaList: List<BuiltBySchema>?): List<BuiltByData> {
    val builtByDataList = mutableListOf<BuiltByData>()
    return if (builtBySchemaList.isNullOrEmpty()) {
        builtByDataList
    } else {
        for (builtBySchema in builtBySchemaList) {
            builtByDataList.add(getBuiltByData(builtBySchema))
        }
        builtByDataList
    }
}


fun getBuiltByData(builtBySchema: BuiltBySchema): BuiltByData {
    return BuiltByData(
        builtBySchema.username ?: "",
        builtBySchema.href ?: "",
        builtBySchema.avatar ?: ""
    )
}

fun getBuiltByEmptyData(): BuiltByData {
    return BuiltByData("", "", "")
}