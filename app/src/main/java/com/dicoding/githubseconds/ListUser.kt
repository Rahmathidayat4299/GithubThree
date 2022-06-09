package com.dicoding.githubseconds

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.adapter.AdapterUser
import com.dicoding.githubseconds.databinding.ActivityListUserBinding
import com.dicoding.viewmodel.ListUserVm

class ListUser : AppCompatActivity() {
    private lateinit var binding: ActivityListUserBinding
    private lateinit var viewModel: ListUserVm
    private lateinit var adapterUser: AdapterUser

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adapterUser = AdapterUser()
        adapterUser.notifyDataSetChanged()
        viewModel = ViewModelProvider(this)[ListUserVm::class.java]


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