package com.amotrade.trendtenhub.ui.home

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.amotrade.trendtenhub.R
import com.amotrade.trendtenhub.base.BaseActivity
import com.amotrade.trendtenhub.data.models.GithubRepositoryData
import com.amotrade.trendtenhub.data.network.ApiClient
import com.amotrade.trendtenhub.data.network.ApiService
import com.amotrade.trendtenhub.data.remote.RemoteRepositoryImpl
import com.amotrade.trendtenhub.databinding.ActivityHomeBinding
import com.amotrade.trendtenhub.ui.home.adapters.GithubTrendingRepositoriesRecyclerAdapter
import com.amotrade.trendtenhub.ui.home.viewmodel.HomeViewModel
import com.amotrade.trendtenhub.ui.home.viewmodelfactory.HomeViewModelFactory
import kotlinx.coroutines.Dispatchers

class HomeActivity : BaseActivity(), SwipeRefreshLayout.OnRefreshListener {

    private lateinit var activityHomeBinding: ActivityHomeBinding

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var homeViewModelFactory: HomeViewModelFactory


    private val githubTrendingRepositoriesRecyclerAdapter by lazy { GithubTrendingRepositoriesRecyclerAdapter() }
    private var gitRepoList: ArrayList<GithubRepositoryData>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityHomeBinding = DataBindingUtil.setContentView(this, R.layout.activity_home)

        homeViewModelFactory = HomeViewModelFactory(
            RemoteRepositoryImpl.getInstance(ApiClient.getRetrofitInstance()!!.create(ApiService::class.java),
                Dispatchers.IO
        )!!)

        homeViewModel = ViewModelProvider(this,homeViewModelFactory).get(HomeViewModel::class.java)

        setObservers()
        initViews()
    }

    private fun setObservers() {
        homeViewModel.getGithubRepositoryDataList().observe(this,{
            if(it!=null){
                onRepoListReceived(it as ArrayList<GithubRepositoryData>)
            }
        })

        homeViewModel.getErrorGithubRepositoryDataList().observe(this,{
            errorView()
        })

        homeViewModel.getLoadingGithubRepositoryData().observe(this,{

        })
    }

    private fun initViews() {
        activityHomeBinding.swipeRefresh.setOnRefreshListener(this)

        if (gitRepoList == null) {
            gitRepoList = ArrayList()
        }

        activityHomeBinding.gitRepoListRecyclerView.layoutManager =
            LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        activityHomeBinding.gitRepoListRecyclerView.adapter = githubTrendingRepositoriesRecyclerAdapter

        activityHomeBinding.errorLayout.buttonRetry.setOnClickListener {
            loadView()
            homeViewModel.getTrendingGithubRepositories()
        }

        homeViewModel.getTrendingGithubRepositories()
    }

    override fun onRefresh() {
        activityHomeBinding.swipeRefresh.isRefreshing = true
        loadView()
        homeViewModel.getTrendingGithubRepositories()
    }

    private fun onRepoListReceived(repoList: ArrayList<GithubRepositoryData>) {
        if (gitRepoList != null) {
            this.gitRepoList = repoList
            githubTrendingRepositoriesRecyclerAdapter.setGitRepoList(gitRepoList)
            githubTrendingRepositoriesRecyclerAdapter.notifyDataSetChanged()
            listView()
        } else {
            errorView()
        }
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