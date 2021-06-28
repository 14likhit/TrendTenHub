package com.amotrade.trendtenhub.ui.home

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.amotrade.trendtenhub.R
import com.amotrade.trendtenhub.base.BaseActivity
import com.amotrade.trendtenhub.databinding.ActivityHomeBinding

class HomeActivity : BaseActivity() {

    private lateinit var activityHomeBinding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityHomeBinding = DataBindingUtil.setContentView(this, R.layout.activity_home)
    }
}