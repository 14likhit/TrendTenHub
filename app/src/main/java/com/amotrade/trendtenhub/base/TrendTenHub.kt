package com.amotrade.trendtenhub.base

import android.app.Application
import com.amotrade.trendtenhub.data.network.ApiClient

class TrendTenHub : Application() {

    override fun onCreate() {
        super.onCreate()
        ApiClient.initialiseRetrofitInstance(this)
    }
}