package com.dicoding.githubseconds

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.adapter.AdapterUser
import com.dicoding.githubseconds.databinding.ActivityFavoriteUserBinding
import com.dicoding.model.localstorage.FavoriteUser
import com.dicoding.model.remote.ItemResult
import com.dicoding.viewmodel.FavUserVm

class FavUserActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavoriteUserBinding
    private lateinit var adapterUser: AdapterUser
    private val viewModel by viewModels<FavUserVm>()

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adapterUser = AdapterUser()
        adapterUser.notifyDataSetChanged()
        adapterUser.setOnItemClickCallback(object : AdapterUser.OnItemClickCallback {
            override fun onItemClik(data: ItemResult) {
                Intent(this@FavUserActivity, DetailUser::class.java).also {
                    it.putExtra(DetailUser.USERNAME_GITHUB, data.login)
                    it.putExtra(DetailUser.EXTRA_ID, data.id)
                    it.putExtra(DetailUser.EXTRA_URL_AVATAR, data.avatarUrl)
                    startActivity(it)
                }
            }

        })
        binding.rcvFavUser.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@FavUserActivity)
            adapter = adapterUser
        }
        viewModel.getFavoriteUser()?.observe(this) {
            if (it != null) {
                val list = mapList(it)
                adapterUser.addList(list)
            }
        }
    }

    private fun mapList(users: List<FavoriteUser>): ArrayList<ItemResult> {
        val listUsers = ArrayList<ItemResult>()
        for (user in users) {
            val userMapped = ItemResult(
                user.html_url,
                user.login,
                user.id,
                user.avatar_url,
            )
            listUsers.add(userMapped)
        }
        return listUsers
    }
}