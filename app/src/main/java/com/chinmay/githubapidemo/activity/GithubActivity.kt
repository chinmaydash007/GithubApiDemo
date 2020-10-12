package com.chinmay.githubapidemo.activity

import android.animation.AnimatorInflater
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.chinmay.githubapidemo.R
import com.chinmay.githubapidemo.adapter.MainAdapter
import com.chinmay.githubapidemo.databinding.GithubActivityBinding
import com.chinmay.githubapidemo.model.Repos
import com.chinmay.githubapidemo.model.ReposItem
import com.chinmay.githubapidemo.utils.NetWorkConnectionType
import com.chinmay.githubapidemo.utils.NetworkConnectionStatus
import com.chinmay.githubapidemo.utils.Status
import com.chinmay.githubapidemo.viewmodel.GithubViewModel
import com.chinmay.githubapidemo.viewmodel.GithubViewModelFactory
import kotlinx.android.synthetic.main.activity_github.*

class GithubActivity : AppCompatActivity() {
    private lateinit var viewModel: GithubViewModel
    lateinit var binding: GithubActivityBinding
    lateinit var adapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_github)
        setupUI()
        setupViewModel()
        setupObservers()

        val networkConnectionStatus = NetworkConnectionStatus(applicationContext)
        networkConnectionStatus.observe(this, Observer { networkConnectionType ->
            showNetworkType(networkConnectionType)
        })
        viewModel.getRepoSFromLocal().observe(this, Observer { repoList ->
            repoList.forEach { repoRoomModel ->

            }
        })
    }


    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(
            this,
            GithubViewModelFactory(application)
        ).get(GithubViewModel::class.java)
    }

    private fun setupUI() {
        adapter = MainAdapter(arrayListOf())
        binding.recyclerView.adapter = adapter
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                (recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
    }

    private fun setupObservers() {
        viewModel.getRepos("chinmaydash007").observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        recyclerView.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                        resource.data?.let { repos: Repos ->
                            for (repo in repos) {
                                addReposToRecyclerView(repo)
                            }
                        }
                    }
                    Status.ERROR -> {
                        recyclerView.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        progressBar.visibility = View.VISIBLE
                        recyclerView.visibility = View.GONE
                    }
                }
            }
        })
    }

    private fun addReposToRecyclerView(repo: ReposItem) {
        adapter.apply {
            addRepoItem(repo)
            notifyDataSetChanged()
        }
    }

    private fun showNetworkType(netWorkConnectionType: NetWorkConnectionType) {
        Log.d("mytag2", "$netWorkConnectionType")
        binding.connectionTypeTextView.text = netWorkConnectionType.name
        val loadAnimator =
            AnimatorInflater.loadAnimator(this, R.animator.connection_layout_animator)
        loadAnimator.setTarget(binding.connectionLayout)
        loadAnimator.start()
    }
}