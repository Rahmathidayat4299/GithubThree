package com.dicoding.retrofit

import com.dicoding.model.remote.ItemResult
import com.dicoding.model.remote.ModelDet
import com.dicoding.model.remote.UserResult
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

//    @GET("users/{username}/followers")
//    fun listFollowers(
//        @Path("username") username: String
//    ): Call<ArrayList<ItemResult>>
//
//    @GET("users/{username}/following")
//    fun listFollowing(
//        @Path("username") username: String
//    ): Call<ArrayList<ItemResult>>

    @GET("users/{username}/{type}")
    fun pathFollow(
        @Path("username") username: String,@Path("type") type: String,
    ): Call<ArrayList<ItemResult>>
}