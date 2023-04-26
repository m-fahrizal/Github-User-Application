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
    val detailUser: MutableLiveData<DetailUserResponse> = _detailUser

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    companion object {
        private const val TAG = "DetailViewModel"
        private const val USER = "m-fahrizal"
    }

    fun setDetail(username: String) {
        val clientDetail = ApiConfig.getApiService().getDetailUser(username)
        clientDetail.enqueue(object : Callback<DetailUserResponse> {
            override fun onResponse(
                call: Call<DetailUserResponse>,
                response: Response<DetailUserResponse>
            ) {
                if (response.isSuccessful) {
                    _detailUser.postValue(response.body())
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


//    fun detailUsers(username: String) {
//        _isLoading.value = true
//        val client = ApiConfig.getApiService().getListUsers(username)
//        client.enqueue(object : Callback<DetailUserResponse> {
//            override fun onResponse(
//                call: Call<DetailUserResponse>,
//                response: Response<DetailUserResponse>
//            ) {
//                _isLoading.value = false
//                if (response.isSuccessful) {
//                    val responseBody = response.body()
//                    if (responseBody != null) {
//                        _detailUser.value = responseBody.detailUser
//                    }
//                } else {
//                    Log.e(TAG, "onFailure: ${response.message()}")
//                }
//            }
//
//            override fun onFailure(call: Call<DetailUserResponse>, t: Throwable) {
//                _isLoading.value = false
//                Log.e(TAG, "onFailure: ${t.message.toString()}")
//            }
//
//        })
//    }


}