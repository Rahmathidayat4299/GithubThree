package com.dicoding.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dicoding.model.localstorage.FavoriteUser
import com.dicoding.model.localstorage.FavoriteUserDao
import com.dicoding.model.localstorage.UserDatabase
import com.dicoding.model.remote.ModelDet
import com.dicoding.retrofit.RetroService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response

class DetailUserVm(application: Application) : AndroidViewModel(application) {
    private val detailUser = MutableLiveData<ModelDet>()
    private var userDao: FavoriteUserDao? = null
    private var userDb: UserDatabase? = UserDatabase.getDatabase(application)
    init {
        userDao = userDb?.favoriteUserDao()
    }
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

    fun addFavorite(username: String?, id: Int, avatarUrl: String?, url: String?) {
        CoroutineScope(Dispatchers.IO).launch {
            val user = FavoriteUser(username, id, avatarUrl, url)
            userDao?.addToFavorite(user)
        }
    }

    fun checkUser(id: Int) = userDao?.checkUser(id)

    fun removeFromFavorite(id: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            userDao?.removeFromFavorite(id)
        }
    }
}