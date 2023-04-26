package com.example.githubsubmission

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubsubmission.adapter.UserAdapter
import com.example.githubsubmission.data.User
import com.example.githubsubmission.database.FavoriteUser
import com.example.githubsubmission.databinding.ActivityFavoriteBinding
import com.example.githubsubmission.viewmodel.DetailViewModel
import com.example.githubsubmission.viewmodel.FavoriteViewModel
import com.example.githubsubmission.viewmodel.MainViewModel
import com.example.githubsubmission.viewmodel.ViewModelFactory

class FavoriteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavoriteBinding
    private val favoriteViewModel by viewModels<FavoriteViewModel>() {
        ViewModelFactory.getInstance(application)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = getString(R.string.favorite)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

//        favoriteViewModel = obtainViewModel(this)

        favoriteViewModel.getAllFavorites().observe(this) {
            if (it != null) {
                val favoriteList = mutableListOf<User>()
                var user: User
                it.forEach {
                    user = User(
                        it.userName,
                        it.avatarUrl
                    )
                    favoriteList.add(user)
                }
                setListFavorite(favoriteList)
            }
        }
    }

    override fun onOptionsItemSelected(back: MenuItem): Boolean {
        when (back.itemId) {
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(back)
    }

    private fun setListFavorite(userList: List<User>) {
        val userList = UserAdapter(userList)
        binding.rvList.layoutManager = LinearLayoutManager(this)
        binding.rvList.setHasFixedSize(true)
        binding.rvList.adapter = userList
    }

    override fun onDestroy() {
        super.onDestroy()
//        _binding = null
    }

//    private fun obtainViewModel(activity: AppCompatActivity): FavoriteViewModel {
//        val Favfactory = ViewModelFactory.getInstance(activity.application)
//        return ViewModelProvider(activity, Favfactory)[FavoriteViewModel::class.java]
//    }
}