package com.dicoding.githubseconds

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.ConstValue.TITLE_FOLLOW
import com.dicoding.adapter.AdapterViewPager
import com.dicoding.githubseconds.databinding.ActivityDetailUserBinding
import com.dicoding.viewmodel.DetailUserVm
import com.google.android.material.tabs.TabLayoutMediator

class DetailUser : AppCompatActivity() {
    private lateinit var binding: ActivityDetailUserBinding
    private val viewModel by viewModels<DetailUserVm>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        viewModel = ViewModelProvider(this)[DetailUserVm::class.java]
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
        }
        val username = intent.getStringExtra(USERNAME_GITHUB)
        val bundle = Bundle()
        bundle.putString(USERNAME_GITHUB, username)
        if (username != null) {
            viewModel.getDetUser(username)
        }
        viewModel.showDetail().observe(this) {
            if (it != null) {
                binding.apply {
                    detailNama.text = it.name
                    detailUsername.text = it.login
                    followers.text = it.followers.toString()
                    following.text = it.following.toString()
                    repoUser.text = it.publicRepos.toString()
                    locationUser.text = it.location
                    companyUser.text = it.company
                    Glide.with(this@DetailUser)
                        .load(it.avatarUrl)
                        .apply(RequestOptions.circleCropTransform())
                        .into(ivAvatar)
                }
            }
        }
        val viewPagerAdapter = AdapterViewPager(this, bundle)
        binding.apply {
            viewPager2.adapter = viewPagerAdapter
            TabLayoutMediator(tabsLayout, viewPager2) { view, position ->
                view.text = resources.getString(TITLE_FOLLOW[position])
            }.attach()
        }


    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    companion object {
        const val USERNAME_GITHUB = "username_github"

    }

}