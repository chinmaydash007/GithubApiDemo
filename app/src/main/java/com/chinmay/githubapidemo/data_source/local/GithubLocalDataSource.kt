package com.chinmay.githubapidemo.data_source.local

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GithubLocalDataSource(private val application: Application) {

    private var repoDao: Repo_dao = RepoDataBase.getInstance(application.applicationContext).repoDao
    private var coroutineScope = CoroutineScope(Dispatchers.IO)


    val repos = repoDao.getAllRepo()

    fun insert(repoRoomModel: RepoRoomModel) {
        coroutineScope.launch {
            repoDao.insertRepo(repoRoomModel)
        }
    }

    fun update(repoRoomModel: RepoRoomModel) {
        coroutineScope.launch {
            repoDao.updateRepo(repoRoomModel)
        }
    }

    fun delete(repoRoomModel: RepoRoomModel) {
        coroutineScope.launch {
            repoDao.deleteRepo(repoRoomModel)
        }
    }

    fun deleteAllRepoData() {
        coroutineScope.launch {
            repoDao.deleteAll()
        }
    }
}