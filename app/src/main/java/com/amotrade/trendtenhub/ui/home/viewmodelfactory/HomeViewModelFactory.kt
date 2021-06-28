package com.amotrade.trendtenhub.ui.home.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.amotrade.trendtenhub.data.remote.RemoteRepositoryImpl
import com.amotrade.trendtenhub.ui.home.viewmodel.HomeViewModel

class HomeViewModelFactory(private val remoteRepositoryImpl: RemoteRepositoryImpl,) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(HomeViewModel::class.java)){
            return HomeViewModel(remoteRepositoryImpl) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}