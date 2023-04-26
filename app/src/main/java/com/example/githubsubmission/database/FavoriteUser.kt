package com.example.githubsubmission.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "favorite")
data class FavoriteUser(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "userName")
    var userName: String = "",
    @ColumnInfo(name = "avatarUrl")
    var avatarUrl: String? = null
): Parcelable
