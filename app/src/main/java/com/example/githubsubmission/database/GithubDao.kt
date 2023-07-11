package com.example.githubsubmission.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface GithubDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: FavoriteUser)
    @Update
    fun update(user: FavoriteUser)
    @Delete
    fun delete(user: FavoriteUser)

    @Query("SELECT * from favorite")
    fun getAllFavorite(): LiveData<List<FavoriteUser>>

    @Query("SELECT * FROM favorite WHERE username = :username")
    fun getFavoriteUserByUsername(username: String): LiveData<FavoriteUser>
}