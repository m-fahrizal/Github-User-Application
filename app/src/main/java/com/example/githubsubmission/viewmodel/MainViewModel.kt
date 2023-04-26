package com.example.githubsubmission.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.githubsubmission.api.ApiConfig
import com.example.githubsubmission.data.SearchUserResponse
import com.example.githubsubmission.data.User
import com.example.githubsubmission.database.FavoriteUser
import com.example.githubsubmission.repository.FavRepository
import com.example.githubsubmission.utils.SettingPreferences
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

//@HiltViewModel
class MainViewModel(application: Application) : ViewModel() {
    private val mFavRepository: FavRepository = FavRepository(application)
    fun getAllNotes(): LiveData<List<FavoriteUser>> = mFavRepository.getAllFavorites()

//    fun getTheme() = preferences.getThemeSetting().asLiveData()

    private val _listUser = MutableLiveData<List<User>>()
    val listUser: MutableLiveData<List<User>> = _listUser

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    companion object {
        private const val TAG = "MainViewModel"
        private const val USER = "m-fahrizal"
    }

    fun setSearch(query: String) {

        val client = ApiConfig.getApiService().getListUsers(query)
        client.enqueue(object : Callback<SearchUserResponse> {
            override fun onResponse(call: Call<SearchUserResponse>, response: Response<SearchUserResponse>) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        _listUser.value = responseBody.listUser
                    }
                }else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<SearchUserResponse>, t: Throwable) {
                Log.e("Failure", "onFailure: ${t.message}")
            }

        })
    }

    fun getSearch(): LiveData<List<User>> {
        return listUser
    }

    fun findUsers(username: String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getListUsers(username)
        client.enqueue(object : Callback<SearchUserResponse> {
            override fun onResponse(
                call: Call<SearchUserResponse>,
                response: Response<SearchUserResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        _listUser.value = responseBody.listUser
                    }
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<SearchUserResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

//    fun theme(preferences: SettingPreferences) {
//        preferences.getThemeSetting().asLiveData()
//    }
}
