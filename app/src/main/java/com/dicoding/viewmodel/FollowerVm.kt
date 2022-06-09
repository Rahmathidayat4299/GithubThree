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


class FollowerVm : ViewModel() {
    // TODO: Implement the ViewModel
    val dataFollower = MutableLiveData<ArrayList<ItemResult>>()

    fun getFollower(username : String){
        RetroService.apiInstansiasi
            .listFollowers(username)
            .enqueue(object :Callback<ArrayList<ItemResult>> {
                override fun onResponse(
                    call: Call<ArrayList<ItemResult>>,
                    response: Response<ArrayList<ItemResult>>
                ) {
                    if (response.isSuccessful){
                        dataFollower.postValue(response.body())
                    }else{
                        Log.e("FollowerVm", "onResponse: error ${response.message()}")
                    }
                }

                override fun onFailure(call: Call<ArrayList<ItemResult>>, t: Throwable) {
                    Log.e("FollowerVm", "onResponse: error ${t.message.toString()}")
                }

            })
    }

    fun showFollower(): LiveData<ArrayList<ItemResult>> {
        return dataFollower
    }
}


