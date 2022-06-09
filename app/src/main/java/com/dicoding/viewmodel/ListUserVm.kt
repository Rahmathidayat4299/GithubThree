package com.dicoding.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.model.ItemResult
import com.dicoding.model.UserResult
import com.dicoding.retrofit.RetroService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListUserVm : ViewModel() {
    private val list = MutableLiveData<ArrayList<ItemResult>>()


    fun getUser(q: String):LiveData<ArrayList<ItemResult>> {
        RetroService.apiInstansiasi
            .getListUser(q)
            .enqueue(object : Callback<UserResult> {
                override fun onResponse(call: Call<UserResult>, response: Response<UserResult>) {
                    if (response.isSuccessful) {
                        list.postValue(response.body()?.items)
                    }
                }

                override fun onFailure(call: Call<UserResult>, t: Throwable) {
                    Log.e("vmlistuser", "onFailure: ${t.message} ")
                }

            })
        return list
    }

//    fun getListUser(): LiveData<ArrayList<ItemResult>> {
//
//    }
}