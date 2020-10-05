package com.chinmay.githubapidemo.repository

import com.chinmay.githubapidemo.api.ApiHelper
import com.chinmay.githubapidemo.db.RepoRoomModel
import com.chinmay.githubapidemo.db.Repo_dao

class MainRepository(private val apiHelper: ApiHelper, private val repoDao: Repo_dao) {

    suspend fun getRepos(userName: String) = apiHelper.getRepos(userName)


    //For Room DB
    val repos = repoDao.getAllRepo()
    suspend fun insert(repoRoomModel: RepoRoomModel) {
        repoDao.insertRepo(repoRoomModel)
    }

    suspend fun update(repoRoomModel: RepoRoomModel) {
        repoDao.updateRepo(repoRoomModel)
    }

    suspend fun delete(repoRoomModel: RepoRoomModel) {
        repoDao.deleteRepo(repoRoomModel)
    }

    suspend fun deleteAllRepoData() {
        repoDao.deleteAll()
    }
}
