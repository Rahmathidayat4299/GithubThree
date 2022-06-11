package com.dicoding.githubseconds

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.dicoding.util.ConstValue.SPLASH_SEC

class SplashGithub : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_github)
        Handler(Looper.myLooper()!!).postDelayed({
            val intent = Intent(this, ListUser::class.java)
            startActivity(intent)
        }, SPLASH_SEC)
    }


}