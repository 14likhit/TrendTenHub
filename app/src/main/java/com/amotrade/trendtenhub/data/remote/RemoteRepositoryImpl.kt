package com.amotrade.trendtenhub.data.remote

import com.amotrade.trendtenhub.base.data.BaseResponse
import com.amotrade.trendtenhub.data.models.schemas.GithubRepositorySchema
import com.amotrade.trendtenhub.data.network.ApiService

class RemoteRepositoryImpl(private val service: ApiService) : RemoteRepository{

    companion object{
        fun getInstance(apiService: ApiService): RemoteRepositoryImpl? {
            if (mInstance == null) {
                mInstance = RemoteRepositoryImpl(apiService)
            }
            return mInstance
        }

        private var mInstance: RemoteRepositoryImpl? = null
    }


    override suspend fun getTrendingGitRepositories(): BaseResponse<List<GithubRepositorySchema>> {
        return BaseResponse()
    }
}