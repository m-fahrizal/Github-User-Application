package com.example.githubsubmission.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.githubsubmission.database.FavRoomDatabase
import com.example.githubsubmission.database.FavoriteUser
import com.example.githubsubmission.database.GithubDao
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class FavRepository(application: Application){

    private val mFavDao: GithubDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = FavRoomDatabase.getDatabase(application)
        mFavDao = db.favDao()
    }

    fun getAllFavorites(): LiveData<List<FavoriteUser>> = mFavDao.getAllFavorite()

    fun insert(favorite: FavoriteUser) {
        executorService.execute {
            mFavDao.insert(favorite)
        }
    }
    fun delete(favorite: FavoriteUser) {
        executorService.execute {
            mFavDao.delete(favorite)
        }
    }
    fun update(favorite: FavoriteUser) {
        executorService.execute {
            mFavDao.update(favorite)
        }
    }

    fun getFavoriteByUsername(username: String): LiveData<FavoriteUser> {
        return  mFavDao.getFavoriteUserByUsername(username)
    }
}