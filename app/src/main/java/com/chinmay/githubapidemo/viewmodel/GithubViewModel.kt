package com.chinmay.githubapidemo.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.chinmay.githubapidemo.data_source.DataSource
import com.chinmay.githubapidemo.model.Repos
import com.chinmay.githubapidemo.utils.Resource


class GithubViewModel(application: Application) :
    AndroidViewModel(
        application
    ) {
    private var dataSource: DataSource = DataSource(application)
    private var repos = dataSource.getRepos("chinmaydash007")


    //Remote
    fun getRepos(userName: String): LiveData<Resource<Repos>> {
        return repos
    }

    fun getRepoSFromLocal() = dataSource.getReposFromLocal()

}