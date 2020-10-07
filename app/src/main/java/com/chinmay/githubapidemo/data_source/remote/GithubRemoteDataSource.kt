package com.chinmay.githubapidemo.data_source.remote

import androidx.lifecycle.liveData
import com.chinmay.githubapidemo.utils.Resource
import kotlinx.coroutines.Dispatchers

class GithubRemoteDataSource {

    private val apiHelper = ApiHelper(RetrofitBuilder.githubApiService)

    fun getRepos(userName: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))

        try {
            emit(Resource.success(data = apiHelper.getRepos(userName)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }

    }

}
