package com.amotrade.trendtenhub.ui.home

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.amotrade.trendtenhub.R
import com.amotrade.trendtenhub.base.BaseActivity
import com.amotrade.trendtenhub.data.models.GithubRepositoryData
import com.amotrade.trendtenhub.databinding.ActivityHomeBinding
import com.amotrade.trendtenhub.ui.home.adapters.GithubTrendingRepositoriesRecyclerAdapter

class HomeActivity : BaseActivity(), SwipeRefreshLayout.OnRefreshListener {

    private lateinit var activityHomeBinding: ActivityHomeBinding

    private val githubTrendingRepositoriesRecyclerAdapter by lazy { GithubTrendingRepositoriesRecyclerAdapter() }
    private var gitRepoList: ArrayList<GithubRepositoryData>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityHomeBinding = DataBindingUtil.setContentView(this, R.layout.activity_home)

        initViews()
    }

    private fun initViews() {
        activityHomeBinding.swipeRefresh.setOnRefreshListener(this)

        if (gitRepoList == null) {
            gitRepoList = ArrayList()
        }

        activityHomeBinding.gitRepoListRecyclerView.layoutManager =
            LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        activityHomeBinding.gitRepoListRecyclerView.adapter = githubTrendingRepositoriesRecyclerAdapter
    }

    override fun onRefresh() {
        activityHomeBinding.swipeRefresh.isRefreshing = true
        loadView()
    }

    /**
     * View while loading data
     */
    private fun loadView() {
        activityHomeBinding.gitRepoListRecyclerView.visibility = View.GONE
        activityHomeBinding.errorLayout.errorLinearLayout.visibility = View.GONE
        activityHomeBinding.errorLayout.buttonRetry.visibility = View.GONE
        activityHomeBinding.shimmerListLayout.viewContainerShimmer.startShimmer()
        activityHomeBinding.shimmerListLayout.viewContainerShimmer.visibility = View.VISIBLE
        activityHomeBinding.swipeRefresh.isRefreshing = false
    }

    /**
     * View when data loaded
     */
    private fun listView() {
        activityHomeBinding.shimmerListLayout.viewContainerShimmer.stopShimmer()
        activityHomeBinding.shimmerListLayout.viewContainerShimmer.visibility = View.GONE
        activityHomeBinding.errorLayout.errorLinearLayout.visibility = View.GONE
        activityHomeBinding.errorLayout.buttonRetry.visibility = View.GONE
        activityHomeBinding.gitRepoListRecyclerView.visibility = View.VISIBLE
    }

    /**
     * View when errorString occurred.
     */
    private fun errorView() {
        activityHomeBinding.shimmerListLayout.viewContainerShimmer.stopShimmer()
        activityHomeBinding.shimmerListLayout.viewContainerShimmer.visibility = View.GONE
        activityHomeBinding.gitRepoListRecyclerView.visibility = View.GONE
        activityHomeBinding.errorLayout.errorLinearLayout.visibility = View.VISIBLE
        activityHomeBinding.errorLayout.buttonRetry.visibility = View.VISIBLE
    }
}