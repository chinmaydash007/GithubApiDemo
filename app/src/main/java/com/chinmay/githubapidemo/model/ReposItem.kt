package com.chinmay.githubapidemo.model

data class ReposItem(
    val id: Int,
    val name: String,
    val owner: Owner,
    val `private`: Boolean,
    val url: String,
)