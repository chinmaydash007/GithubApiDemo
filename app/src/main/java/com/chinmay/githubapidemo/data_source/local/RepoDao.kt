package com.chinmay.githubapidemo.data_source.local

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface Repo_dao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRepo(repo: RepoRoomModel): Long

    @Update
    suspend fun updateRepo(repo: RepoRoomModel)

    @Delete
    suspend fun deleteRepo(repo: RepoRoomModel)

    @Query("DELETE FROM repo_data_table")
    suspend fun deleteAll()

    @Query("SELECT * FROM repo_data_table")
    fun getAllRepo(): LiveData<List<RepoRoomModel>>
}