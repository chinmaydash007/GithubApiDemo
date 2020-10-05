package com.chinmay.githubapidemo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.chinmay.githubapidemo.db.RepoRoomModel
import com.chinmay.githubapidemo.repository.MainRepository
import com.chinmay.githubapidemo.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainViewModel(private val mainRepository: MainRepository) : ViewModel() {


    fun insert(repoRoomModel: RepoRoomModel) = viewModelScope.launch {
        mainRepository.insert(repoRoomModel)
    }


    fun clearAll() = viewModelScope.launch {
        mainRepository.deleteAllRepoData()
    }

    fun getRepos(userName: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = mainRepository.getRepos(userName)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}