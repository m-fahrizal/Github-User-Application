package com.example.githubsubmission.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.githubsubmission.database.FavoriteUser
import com.example.githubsubmission.repository.FavRepository
import com.example.githubsubmission.utils.SettingPreferences

class FavoriteViewModel (application: Application) : ViewModel() {
    private val mFavRepository: FavRepository = FavRepository(application)
    fun insert(favorite: FavoriteUser) {
        mFavRepository.insert(favorite)
    }
    fun update(favorite: FavoriteUser) {
        mFavRepository.update(favorite)
    }
    fun delete(favorite: FavoriteUser) {
        mFavRepository.delete(favorite)
    }

    private val mFavoriteUser: FavRepository = FavRepository(application)
    fun getAllFavorites(): LiveData<List<FavoriteUser>> = mFavoriteUser.getAllFavorites()
}

