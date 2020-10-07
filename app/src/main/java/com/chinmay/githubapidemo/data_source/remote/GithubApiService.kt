package com.chinmay.githubapidemo.data_source.remote

import com.chinmay.githubapidemo.model.Repos
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface GithubApiService {

    @Headers("Accept:application/vnd.github.v3+json")
    @GET("users/{userName}/repos")
    suspend fun getRepos(@Path("userName") userName: String): Repos

}