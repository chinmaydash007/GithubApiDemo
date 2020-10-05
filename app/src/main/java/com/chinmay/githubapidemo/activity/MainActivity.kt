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
import com.chinmay.githubapidemo.api.ApiHelper
import com.chinmay.githubapidemo.api.RetrofitBuilder
import com.chinmay.githubapidemo.databinding.MainActivityBinding
import com.chinmay.githubapidemo.db.RepoDataBase
import com.chinmay.githubapidemo.db.RepoRoomModel
import com.chinmay.githubapidemo.model.Repos
import com.chinmay.githubapidemo.model.ReposItem
import com.chinmay.githubapidemo.utils.NetWorkConnectionType
import com.chinmay.githubapidemo.utils.NetworkConnection
import com.chinmay.githubapidemo.utils.Status
import com.chinmay.githubapidemo.viewmodel.MainViewModel
import com.chinmay.githubapidemo.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    lateinit var binding: MainActivityBinding
    lateinit var adapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setupUI()
        setupViewModel()
        setupObservers()


        val networkConnection: NetworkConnection = NetworkConnection(applicationContext)
        networkConnection.observe(this, Observer { networkConnectionType ->
            showNetworkType(networkConnectionType)
        })
    }

    private fun showNetworkType(netWorkConnectionType: NetWorkConnectionType) {
        Log.d("mytag2","$netWorkConnectionType")
        binding.connectionTypeTextView.text = netWorkConnectionType.name
        val loadAnimator =
            AnimatorInflater.loadAnimator(this, R.animator.connection_layout_animator)
        loadAnimator.setTarget(binding.connectionLayout)
        loadAnimator.start()
    }

    private fun setupViewModel() {
        val repoDao = RepoDataBase.getInstance(applicationContext).repoDao


        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(ApiHelper(RetrofitBuilder.apiService), repoDao)
        ).get(MainViewModel::class.java)
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
                                var repoModel = RepoRoomModel(0, repo.name, repo.url)

                                CoroutineScope(Dispatchers.IO).launch {
                                    viewModel.insert(repoModel)
                                }
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
        Log.d("mytag", repo.toString())
        adapter.apply {
            addRepoItem(repo)
            notifyDataSetChanged()
        }
    }
}