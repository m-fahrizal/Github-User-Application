package com.example.githubsubmission

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.githubsubmission.adapter.SectionsPagerAdapter
import com.example.githubsubmission.adapter.UserAdapter
import com.example.githubsubmission.api.ApiService
import com.example.githubsubmission.data.User
import com.example.githubsubmission.databinding.FragmentFollowBinding
import com.example.githubsubmission.viewmodel.FollowViewModel
import com.example.githubsubmission.viewmodel.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FollowFragment : Fragment() {
    private lateinit var binding: FragmentFollowBinding

    companion object {
        const val ARG_POSITION = "position"
        const val ARG_USERNAME = "username"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments.let {}
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFollowBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val followViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
            FollowViewModel::class.java
        )
        val args = arguments
        val index = args?.getInt(ARG_POSITION, 1)
        val username = args?.getString(ARG_USERNAME, "default")
        if (index == 1) {
            followViewModel.getFollowing(username.toString())
            followViewModel.following.observe(viewLifecycleOwner) {
                setData(it)
                showLoading(false)
            }
        } else {
            followViewModel.getFollowers(username.toString())
            followViewModel.followers.observe(viewLifecycleOwner) {
                setData(it)
                showLoading(false)
            }
        }

        followViewModel.isLoading.observe(viewLifecycleOwner) {
            showLoading(true)
        }
    }

    fun setData(followList: List<User>) {
        binding.rvFollow.layoutManager = LinearLayoutManager(requireActivity())
        val followAdapter = UserAdapter(followList)
        binding.rvFollow.adapter = followAdapter
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

//    override fun onDestroy() {
//        super.onDestroy()
//        binding = null
//    }
}