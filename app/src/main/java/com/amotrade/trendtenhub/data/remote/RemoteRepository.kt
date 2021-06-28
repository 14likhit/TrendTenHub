package com.amotrade.trendtenhub.data.remote

import com.amotrade.trendtenhub.base.data.BaseResponse
import com.amotrade.trendtenhub.data.models.schemas.GithubRepositorySchema

interface RemoteRepository {

    suspend fun getTrendingGitRepositories(): BaseResponse<List<GithubRepositorySchema>>
}