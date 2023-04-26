package com.example.githubsubmission.di

import android.content.Context
import com.example.githubsubmission.api.ApiConfig
import com.example.githubsubmission.database.FavRoomDatabase
import com.example.githubsubmission.repository.FavRepository
//import com.example.githubsubmission.utils.AppExecutors

//object Injection {
//    fun provideRepository(context: Context): FavRepository {
//        val apiService = ApiConfig.getApiService()
//        val database = FavRoomDatabase.getDatabase(context)
//        val dao = database.favDao()
//        val appExecutors = AppExecutors()
//        return FavRepository.getInstance(apiService, dao, appExecutors)
//    }
//}