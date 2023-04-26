package com.example.githubsubmission.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubsubmission.api.ApiConfig
import com.example.githubsubmission.data.DetailUserResponse
import com.example.githubsubmission.data.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowViewModel: ViewModel() {

    private val _following = MutableLiveData<List<User>>()
    val following: MutableLiveData<List<User>> = _following

    private val _followers = MutableLiveData<List<User>>()
    val followers: MutableLiveData<List<User>> = _followers

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: MutableLiveData<Boolean> get() = _isLoading


    fun getFollowing(username: String){
        _isLoading.value = true
        val clientFollowing = ApiConfig.getApiService().getFollowing(username)
        clientFollowing.enqueue(object : Callback<List<User>> {
            override fun onResponse(
                call: Call<List<User>>,
                response: Response<List<User>>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _following.postValue(response.body())
                }
            }
            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                Log.e("Failure", "onFailure: ${t.message}")
            }
        })
    }

//    fun getFollowing(): LiveData<DetailUserResponse> {
//        return _following
//    }

    fun getFollowers(username: String){
        _isLoading.value = true
        val clientFollowers = ApiConfig.getApiService().getFollowers(username)
        clientFollowers.enqueue(object : Callback<List<User>> {
            override fun onResponse(
                call: Call<List<User>>,
                response: Response<List<User>>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _followers.postValue(response.body())
                }
            }
            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                Log.e("Failure", "onFailure: ${t.message}")
            }
        })
    }

//    fun getFollowers(): LiveData<DetailUserResponse> {
//        return _followers
//    }
}