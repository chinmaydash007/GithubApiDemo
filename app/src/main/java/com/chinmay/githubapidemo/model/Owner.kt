package com.chinmay.githubapidemo.model

data class Owner(
    val id: Int,
    val login: String,
    val repos_url: String,
    val type: String,
    val url: String
)