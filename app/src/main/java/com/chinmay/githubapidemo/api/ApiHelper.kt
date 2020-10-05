package com.chinmay.githubapidemo.api

class ApiHelper(private val apiService: ApiService) {

    suspend fun getRepos(userName: String) = apiService.getRepos(userName)
}