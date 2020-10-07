package com.chinmay.githubapidemo.data_source.local

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


@Parcelize
@Entity(tableName = "repo_data_table")
data class RepoRoomModel(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "repo_id")
    var repo_id: Int,
    @ColumnInfo(name = "repo_name")
    var repo_name: String,
    @ColumnInfo(name = "repo_url")
    var repo_url: String
) : Parcelable