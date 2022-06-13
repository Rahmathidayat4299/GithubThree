package com.dicoding.githubseconds

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.adapter.AdapterUser
import com.dicoding.darkmode.SettingApp
import com.dicoding.githubseconds.databinding.ActivityListUserBinding
import com.dicoding.model.remote.ItemResult
import com.dicoding.viewmodel.ListUserVm

class ListUser : AppCompatActivity() {
    private lateinit var binding: ActivityListUserBinding
    private val viewModel by viewModels<ListUserVm>()
    private lateinit var adapterUser: AdapterUser

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adapterUser = AdapterUser()
        adapterUser.notifyDataSetChanged()


        binding.search.apply {

            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    if (query?.isNotEmpty()!!
                    ) {
                        viewLoading(true)
                        viewModel.getUser(query).observe(this@ListUser) {
                            if (it != null) {

                                adapterUser.addList(it)
                                viewLoading(false)
                                showData()
                            }
                        }
                    }

                    return true
                }


                override fun onQueryTextChange(newText: String?): Boolean = false
            })

        }

        adapterUser.setOnItemClickCallback(object : AdapterUser.OnItemClickCallback {
            override fun onItemClik(data: ItemResult) {
                Intent(this@ListUser, DetailUser::class.java).also {
                    it.putExtra(DetailUser.USERNAME_GITHUB, data.login)
                    it.putExtra(DetailUser.EXTRA_ID, data.id)
                    it.putExtra(DetailUser.EXTRA_URL_AVATAR, data.avatarUrl)
                    it.putExtra(DetailUser.EXTRA_URL, data.htmlUrl)
                    startActivity(it)
                }
            }
        })


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_option, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.favorite_menu -> {
                Intent(this, FavUserActivity::class.java).also {
                    startActivity(it)
                }
            }
            R.id.setting_menu -> {
                Intent(this, SettingApp::class.java).also {
                    startActivity(it)
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun viewLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun showData() {
        binding.rcvUser.apply {
            adapter = adapterUser
            layoutManager = LinearLayoutManager(this@ListUser, LinearLayoutManager.VERTICAL, false)
        }
    }

}