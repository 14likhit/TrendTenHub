package com.amotrade.trendtenhub.utils

import com.amotrade.trendtenhub.data.models.BuiltByData
import com.amotrade.trendtenhub.data.models.GithubRepositoryData
import com.amotrade.trendtenhub.data.models.schemas.BuiltBySchema
import com.amotrade.trendtenhub.data.models.schemas.GithubRepositorySchema

fun getTrendingGithubRepositoryDataList(githubTrendingRepositorySchemaList: List<GithubRepositorySchema>): List<GithubRepositoryData> {
    return emptyList()
}

fun getTrendingGithubRepositoryData(githubRepositorySchema: GithubRepositorySchema): GithubRepositoryData {
    return GithubRepositoryData()
}

fun getTrendingGithubRepositoryEmptyData(): GithubRepositoryData {
    return GithubRepositoryData()
}

fun getBuiltByData(builtBySchema: BuiltBySchema): BuiltByData {
    return BuiltByData()
}

fun getBuiltByEmptyData(): BuiltByData {
    return BuiltByData()
}