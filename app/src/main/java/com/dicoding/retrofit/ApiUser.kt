package com.dicoding.retrofit

import com.dicoding.model.ItemResult
import com.dicoding.model.ModelDet
import com.dicoding.model.UserResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiUser {
    @GET("search/users")
    fun getListUser(
        @Query("q") query: String
    ): Call<UserResult>

    @GET("users/{username}")
    fun userDetail(
        @Path("username") username: String
    ): Call<ModelDet>

    @GET("users/{username}/followers")
    fun listFollowers(
        @Path("username") username: String
    ): Call<ArrayList<ItemResult>>

    @GET("users/{username}/following")
    fun listFollowing(
        @Path("username") username: String
    ): Call<ArrayList<ItemResult>>
}