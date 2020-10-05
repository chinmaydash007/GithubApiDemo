package com.chinmay.githubapidemo.viewmodel

import com.chinmay.githubapidemo.api.ApiHelper
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.chinmay.githubapidemo.db.Repo_dao
import com.chinmay.githubapidemo.repository.MainRepository

class ViewModelFactory(private val apiHelper: ApiHelper,private val repoDao: Repo_dao) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(MainRepository(apiHelper,repoDao)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}

