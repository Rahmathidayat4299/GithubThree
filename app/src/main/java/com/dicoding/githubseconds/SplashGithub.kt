package com.dicoding.githubseconds

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatDelegate
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.dicoding.darkmode.MainViewModel
import com.dicoding.darkmode.ViewModelFactory
import com.dicoding.util.ConstValue.SPLASH_SEC
import com.dicoding.util.SettingPreferences

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
class SplashGithub : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_github)
        Handler(Looper.myLooper()!!).postDelayed({
            val intent = Intent(this, ListUser::class.java)
            startActivity(intent)
        }, SPLASH_SEC)


        val pref = SettingPreferences.getInstance(dataStore)
        viewModel = ViewModelProvider(this, ViewModelFactory(pref))[MainViewModel::class.java]
        viewModel.getThemeSettings().observe(this
        ) { isDarkModeActive: Boolean ->
            if (isDarkModeActive) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
    }


}