package com.amotrade.trendtenhub.data.network

import com.amotrade.trendtenhub.data.models.schemas.GithubRepositorySchema
import retrofit2.http.GET

/**
 * Service to implement all Api endpoints
 */
interface ApiService {

    @GET("v3/7ef86b70-f1a8-40ab-854c-5d679cd51cd4")
    suspend fun getTrendingGitRepositories(): List<GithubRepositorySchema>

}