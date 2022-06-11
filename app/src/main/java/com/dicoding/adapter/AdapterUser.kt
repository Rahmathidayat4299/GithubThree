package com.dicoding.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.githubseconds.DetailUser
import com.dicoding.githubseconds.DetailUser.Companion.USERNAME_GITHUB
import com.dicoding.githubseconds.databinding.ItemListuserBinding
import com.dicoding.model.remote.ItemResult

class AdapterUser : RecyclerView.Adapter<AdapterUser.ListViewHolder>() {
    private var listUser = ArrayList<ItemResult>()

    fun addList(item: ArrayList<ItemResult>) {
        listUser.apply {
            clear()
            addAll(item)
        }
    }

    inner class ListViewHolder(private val binding: ItemListuserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(users: ItemResult) {

            binding.apply {
                name.text = users.login
                username.text = users.id.toString()
            }
            Glide.with(itemView.context)
                .load(users.avatarUrl)
                .apply(RequestOptions.circleCropTransform())
                .into(binding.imageUser)
            itemView.setOnClickListener {
                val intent = Intent(itemView.context, DetailUser::class.java)
                intent.putExtra(USERNAME_GITHUB, users.login)
                itemView.context.startActivity(intent)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view = ItemListuserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(listUser[position])
    }

    override fun getItemCount() = listUser.size
}
