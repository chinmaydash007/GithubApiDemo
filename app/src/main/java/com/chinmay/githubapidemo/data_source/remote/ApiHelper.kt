package com.chinmay.githubapidemo.data_source.remote

class ApiHelper(private val githubApiService: GithubApiService) {
    suspend fun getRepos(userName: String) = githubApiService.getRepos(userName)
}