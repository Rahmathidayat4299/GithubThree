package com.dicoding.model

import com.google.gson.annotations.SerializedName

data class ItemResult(
    @SerializedName("html_url")
    val htmlUrl: String,
    @SerializedName("login")
    val login: String,
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
    @SerializedName("id")
    val id: Int,
    @SerializedName("avatar_url")
    val avatarUrl: String,
)
