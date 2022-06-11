package com.dicoding.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.model.ModelDet
import com.dicoding.retrofit.RetroService
import retrofit2.Call
import retrofit2.Response

class DetailUserVm : ViewModel() {
    private val detailUser = MutableLiveData<ModelDet>()

    fun getDetUser(username: String) {
        RetroService.apiInstansiasi
            .userDetail(username)
            .enqueue(object : retrofit2.Callback<ModelDet> {
                override fun onResponse(call: Call<ModelDet>, response: Response<ModelDet>) {
                    if (response.isSuccessful) {
                        detailUser.postValue(response.body())
                    } else {
                        Log.e("DetUserVM", "onResponse: error data ")
                    }
                }

                override fun onFailure(call: Call<ModelDet>, t: Throwable) {
                    Log.e("DetUserVM", "onResponse: error data ${t.message} ")
                }

            })
    }

    fun showDetail(): LiveData<ModelDet> {
        return detailUser
    }
}