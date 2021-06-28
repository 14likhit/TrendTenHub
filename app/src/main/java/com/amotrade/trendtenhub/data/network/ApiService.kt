package com.amotrade.trendtenhub.data.network

import com.amotrade.trendtenhub.data.models.schemas.GithubRepositorySchema
import retrofit2.http.GET

/**
 * Service to implement all Api endpoints
 */
interface ApiService {

    @GET("/repositories")
    suspend fun getTrendingGitRepositories(): List<GithubRepositorySchema>

}