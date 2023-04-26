package com.example.githubsubmission.api

import com.example.githubsubmission.data.DetailUserResponse
import com.example.githubsubmission.data.GithubResponse
import com.example.githubsubmission.data.SearchUserResponse
import com.example.githubsubmission.data.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @Headers("Authorization: token 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")
    @GET("users")
    fun getUser(
        @Query("q") login: String
    ): Call<GithubResponse>

    @GET("search/users")
    fun getListUsers(
        @Query("q") login: String
    ): Call<SearchUserResponse>

    @GET("users/{username}")
    fun getDetailUser(
        @Path("username") username: String
    ): Call<DetailUserResponse>

    @GET("/users/{username}/following")
    fun getFollowing(
        @Path("username") username: String
    ): Call<List<User>>

    @GET("/users/{username}/followers")
    fun getFollowers(
        @Path("username") username: String
    ): Call<List<User>>

    @GET("search/users")
    fun getFavorite(
        @Query("q") login: String
    ): Call<SearchUserResponse>
}