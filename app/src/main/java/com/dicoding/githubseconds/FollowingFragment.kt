package com.dicoding.githubseconds

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.adapter.AdapterUser
import com.dicoding.githubseconds.databinding.FollowFragmentBinding
import com.dicoding.viewmodel.FollowingVm

class FollowingFragment : Fragment(R.layout.follow_fragment) {
    private var _binding: FollowFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<FollowingVm>()
    private lateinit var adapterUser: AdapterUser
    private lateinit var username: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FollowFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args = arguments
        username = args?.getString(DetailUser.USERNAME_GITHUB).toString()
        adapterUser = AdapterUser()
        adapterUser.notifyDataSetChanged()

        binding.rvFollow.apply {
            adapter = adapterUser
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        }
        viewLoading(true)

        viewModel.getFollowing(username)
        viewModel.showFollowing().observe(viewLifecycleOwner) {
            if (it != null) {
                adapterUser.addList(it)
                viewLoading(false)
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun viewLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}