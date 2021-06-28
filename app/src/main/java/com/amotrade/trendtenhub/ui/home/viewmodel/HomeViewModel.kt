package com.amotrade.trendtenhub.ui.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amotrade.trendtenhub.base.data.BaseResponse
import com.amotrade.trendtenhub.base.data.ErrorData
import com.amotrade.trendtenhub.data.models.GithubRepositoryData
import com.amotrade.trendtenhub.data.remote.RemoteRepositoryImpl
import com.amotrade.trendtenhub.utils.getTrendingGithubRepositoryDataList
import kotlinx.coroutines.launch

class HomeViewModel(private val remoteRepositoryImpl: RemoteRepositoryImpl) : ViewModel() {

    private val githubRepositoryDataList: MutableLiveData<List<GithubRepositoryData>> =
        MutableLiveData()
    private val errorGithubRepositoryDataList: MutableLiveData<ErrorData> = MutableLiveData()
    private val loadingGithubRepositoryData: MutableLiveData<Boolean> = MutableLiveData()

    fun getGithubRepositoryDataList(): LiveData<List<GithubRepositoryData>> {
        return githubRepositoryDataList
    }

    fun getErrorGithubRepositoryDataList(): LiveData<ErrorData> {
        return errorGithubRepositoryDataList
    }

    fun getLoadingGithubRepositoryData(): LiveData<Boolean> {
        return loadingGithubRepositoryData
    }

    fun getTrendingGithubRepositories() {
        loadingGithubRepositoryData.value = true
        viewModelScope.launch {
            when (val response = remoteRepositoryImpl.getTrendingGitRepositories()) {
                is BaseResponse.Success -> {
                    githubRepositoryDataList.value =
                        getTrendingGithubRepositoryDataList(response.data)
                    loadingGithubRepositoryData.value = false
                }

                is BaseResponse.Error -> {
                    errorGithubRepositoryDataList.value = response.errorData
                    loadingGithubRepositoryData.value = false
                }
            }
        }
    }

}