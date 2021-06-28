package com.amotrade.trendtenhub.data.remote

import com.amotrade.trendtenhub.base.data.BaseResponse
import com.amotrade.trendtenhub.base.data.baseApiCall
import com.amotrade.trendtenhub.data.models.schemas.GithubRepositorySchema
import com.amotrade.trendtenhub.data.network.ApiService
import kotlinx.coroutines.CoroutineDispatcher

class RemoteRepositoryImpl(
    private val service: ApiService,
    private val ioDispatcher: CoroutineDispatcher
) : RemoteRepository {

    companion object {
        fun getInstance(
            apiService: ApiService,
            ioDispatcher: CoroutineDispatcher
        ): RemoteRepositoryImpl? {
            if (mInstance == null) {
                mInstance = RemoteRepositoryImpl(apiService, ioDispatcher)
            }
            return mInstance
        }

        private var mInstance: RemoteRepositoryImpl? = null
    }


    override suspend fun getTrendingGitRepositories(): BaseResponse<List<GithubRepositorySchema>> {
        return baseApiCall(ioDispatcher) {
            service.getTrendingGitRepositories()
        }
    }
}