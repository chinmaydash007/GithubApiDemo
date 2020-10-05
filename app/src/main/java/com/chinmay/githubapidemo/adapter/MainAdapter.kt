package com.chinmay.githubapidemo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chinmay.githubapidemo.databinding.RepoItemBinding
import com.chinmay.githubapidemo.model.ReposItem

class MainAdapter(private val repoItemList: ArrayList<ReposItem>) :
    RecyclerView.Adapter<MainAdapter.RepoViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        var binding = RepoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RepoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        holder.binding.repo = repoItemList[position]
    }

    override fun getItemCount(): Int {
        return repoItemList.size
    }

    fun addRepoItem(repoItem: ReposItem) {
        repoItemList.add(repoItem)
    }

    inner class RepoViewHolder(var binding: RepoItemBinding) : RecyclerView.ViewHolder(binding.root)
}
