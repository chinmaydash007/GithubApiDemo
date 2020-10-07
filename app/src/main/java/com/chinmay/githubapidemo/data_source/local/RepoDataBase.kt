package com.chinmay.githubapidemo.data_source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [RepoRoomModel::class], version = 1)
abstract class RepoDataBase : RoomDatabase() {
    abstract val repoDao: Repo_dao

    companion object {
        @Volatile
        private var INSTANCE: RepoDataBase? = null
        fun getInstance(context: Context): RepoDataBase {
            synchronized(lock = this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        RepoDataBase::class.java,
                        "RepoDataBase"
                    ).build()
                }
                return instance
            }
        }
    }
}