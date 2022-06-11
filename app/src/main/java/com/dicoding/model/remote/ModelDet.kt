package com.dicoding.model.remote

import com.google.gson.annotations.SerializedName

data class ModelDet(
    @SerializedName("login")
    val login: String,
    @SerializedName("avatar_url")
    val avatarUrl: String,
    @SerializedName("followers")
    val followers: Int,
    @SerializedName("followers_url")
    val followersUrl: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("following")
    val following: Int,
    @SerializedName("following_url")
    val followingUrl: String,
    @SerializedName("public_repos")
    val publicRepos: Int,
    @SerializedName("location")
    val location: String,
    @SerializedName("company")
    val company: String,
)
