package com.example.githubsubmission.data

import com.google.gson.annotations.SerializedName

data class SearchUserResponse(
    @field:SerializedName("items")
    val listUser: List<User>
)

data class User(
    @field:SerializedName("login")
    val username: String? = "",

    @field:SerializedName("avatar_url")
    val avatarUrl: String? = "",

    @field:SerializedName("name")
    val name: String? = "",

    @field:SerializedName("following")
    val following: Int? = 0,

    @field:SerializedName("followers")
    val followers: Int? = 0,

    @field:SerializedName("gists_url")
    val gistsUrl: String? = null,

    @field:SerializedName("repos_url")
    val reposUrl: String? = null,

    @field:SerializedName("following_url")
    val followingUrl: String? = null,

    @field:SerializedName("twitter_username")
    val twitterUsername: Any? = null,

    @field:SerializedName("bio")
    val bio: Any? = null,

    @field:SerializedName("created_at")
    val createdAt: String? = null
)