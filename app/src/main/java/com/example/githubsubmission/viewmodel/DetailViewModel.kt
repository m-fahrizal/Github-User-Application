package com.example.githubsubmission.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubsubmission.api.ApiConfig
import com.example.githubsubmission.data.DetailUserResponse
import com.example.githubsubmission.database.FavoriteUser
import com.example.githubsubmission.repository.FavRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel(application: Application): ViewModel() {

    private val mFavRepository: FavRepository = FavRepository(application)

    private val _detailUser = MutableLiveData<DetailUserResponse>()

    private val _isLoading = MutableLiveData<Boolean>()

    fun setDetail(username: String) {
        val clientDetail = ApiConfig.getApiService().getDetailUser(username.filterNot { it.isWhitespace() }.lowercase())
        clientDetail.enqueue(object : Callback<DetailUserResponse> {
            override fun onResponse(
                call: Call<DetailUserResponse>,
                response: Response<DetailUserResponse>
            ) {
                if (response.isSuccessful) {
                    _detailUser.postValue(response.body())
                    println(username.filterNot { it.isWhitespace() }.lowercase())
                } else {
                    println(username.filterNot { it.isWhitespace() }.lowercase())
                }
            }
            override fun onFailure(call: Call<DetailUserResponse>, t: Throwable) {
                Log.e("Failure", "onFailure: ${t.message}")
            }
        })
    }

    fun getDetail(): LiveData<DetailUserResponse> {
        return _detailUser
    }

    fun insert(userFavorite: FavoriteUser){
        mFavRepository.insert(userFavorite)
    }

    fun delete(userFavorite: FavoriteUser){
        mFavRepository.delete(userFavorite)
    }

    fun getFavoriteByUsername(username: String): LiveData<FavoriteUser> {
        return mFavRepository.getFavoriteByUsername(username)
    }
}