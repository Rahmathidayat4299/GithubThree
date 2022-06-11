package com.dicoding.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.model.ItemResult
import com.dicoding.retrofit.RetroService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowingVm : ViewModel() {
    private val dataFollowing = MutableLiveData<ArrayList<ItemResult>>()

    fun getFollowing(username: String) {
        RetroService.apiInstansiasi
            .listFollowing(username)
            .enqueue(object : Callback<ArrayList<ItemResult>> {
                override fun onResponse(
                    call: Call<ArrayList<ItemResult>>,
                    response: Response<ArrayList<ItemResult>>
                ) {
                    if (response.isSuccessful) {
                        dataFollowing.postValue(response.body())
                    } else {
                        Log.e("FollowingVm", "onResponse: error ${response.message()}")
                    }
                }

                override fun onFailure(call: Call<ArrayList<ItemResult>>, t: Throwable) {
                    Log.e("FollowerVm", "onResponse: error ${t.message.toString()}")
                }
            })
    }

    fun showFollowing(): LiveData<ArrayList<ItemResult>> {
        return dataFollowing
    }
}