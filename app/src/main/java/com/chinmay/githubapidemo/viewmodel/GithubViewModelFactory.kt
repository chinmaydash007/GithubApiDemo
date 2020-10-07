package com.chinmay.githubapidemo.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class GithubViewModelFactory(private val applicationContext:Application) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GithubViewModel::class.java)) {
            return GithubViewModel(applicationContext) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}

