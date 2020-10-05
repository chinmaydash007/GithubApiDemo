package com.chinmay.githubapidemo.api

import com.chinmay.githubapidemo.model.Repos
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @Headers("Accept:application/vnd.github.v3+json")
    @GET("users/{userName}/repos")
    suspend fun getRepos(@Path("userName") userName: String): Repos

}