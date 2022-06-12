package com.dicoding

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModel as ViewModel

class ViewModelFactory(private val pref: SettingDataStore) : ViewModelProvider.NewInstanceFactory() {


    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DarkModeVm::class.java)){
            return DarkModeVm(pref) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}