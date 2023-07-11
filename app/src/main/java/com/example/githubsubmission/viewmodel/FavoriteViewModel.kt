package com.example.githubsubmission.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.githubsubmission.database.FavoriteUser
import com.example.githubsubmission.repository.FavRepository

class FavoriteViewModel (application: Application) : ViewModel() {
    private val mFavoriteUser: FavRepository = FavRepository(application)

    fun insert(favorite: FavoriteUser) {
        mFavoriteUser.insert(favorite)
    }
    fun update(favorite: FavoriteUser) {
        mFavoriteUser.update(favorite)
    }
    fun delete(favorite: FavoriteUser) {
        mFavoriteUser.delete(favorite)
    }

    fun getAllFavorites(): LiveData<List<FavoriteUser>> = mFavoriteUser.getAllFavorites()
}

