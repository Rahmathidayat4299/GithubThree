package com.dicoding.model.remote

import com.google.gson.annotations.SerializedName

data class ItemResult(
    @SerializedName("html_url")
    val htmlUrl: String?,
    @SerializedName("login")
    val login: String?,
    @SerializedName("id")
    val id: Int,
    @SerializedName("avatar_url")
    val avatarUrl: String?,
)
