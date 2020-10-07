package com.chinmay.githubapidemo.data_source

import android.app.Application
import androidx.lifecycle.LiveData
import com.chinmay.githubapidemo.data_source.local.GithubLocalDataSource
import com.chinmay.githubapidemo.data_source.local.RepoRoomModel
import com.chinmay.githubapidemo.data_source.remote.GithubRemoteDataSource

class DataSource(application: Application) {
    private var githubRemoteDataSource: GithubRemoteDataSource = GithubRemoteDataSource()
    private var githubLocalDataSource: GithubLocalDataSource = GithubLocalDataSource(application)


    //Remote
    fun getRepos(userName: String) = githubRemoteDataSource.getRepos(userName)


    //Local
    //    var repoModel = RepoRoomModel(0, repo.name, repo.url)
    fun insertRepoToLocal(repoRoomModel: RepoRoomModel) {
        githubLocalDataSource.insert(repoRoomModel)

    }
    fun getReposFromLocal(): LiveData<List<RepoRoomModel>> {
        return githubLocalDataSource.repos
    }


}