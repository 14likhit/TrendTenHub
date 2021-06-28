package com.amotrade.trendtenhub.ui.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.amotrade.trendtenhub.R
import com.amotrade.trendtenhub.data.models.GithubRepositoryData
import com.amotrade.trendtenhub.databinding.ItemGitRepoBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class GithubTrendingRepositoriesRecyclerAdapter :
    RecyclerView.Adapter<GithubTrendingRepositoriesRecyclerAdapter.GitRepoListViewHolder>(){

    private var gitRepoList: List<GithubRepositoryData>? = null

    private var inflater: LayoutInflater? = null

    fun setGitRepoList(gitRepoList: List<GithubRepositoryData>?) {
        this.gitRepoList = gitRepoList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GitRepoListViewHolder {
        if (inflater == null) {
            inflater = LayoutInflater.from(parent.context)
        }
        return GitRepoListViewHolder(
            ItemGitRepoBinding.inflate(
                inflater!!,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: GitRepoListViewHolder, position: Int) {
        val gitRepo = gitRepoList!![position]

        holder.binding.repoAuthorTextView.text = gitRepo.author
        holder.binding.repoNameTextView.text = gitRepo.name


        Glide.with(holder.itemView)
            .load(gitRepo.avatar)
            .placeholder(R.drawable.circle)
            .error(R.drawable.circle)
            .fallback(R.drawable.circle)
            .apply(RequestOptions.circleCropTransform())
            .into(holder.binding.repoImageView)
    }

    override fun getItemCount(): Int {
        return if (gitRepoList != null && gitRepoList!!.isNotEmpty()) {
            gitRepoList!!.size
        } else 0
    }

    class GitRepoListViewHolder(val binding: ItemGitRepoBinding) :
        RecyclerView.ViewHolder(binding.root)
}