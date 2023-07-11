package com.example.githubsubmission

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.githubsubmission.adapter.SectionsPagerAdapter
import com.example.githubsubmission.database.FavoriteUser
import com.example.githubsubmission.databinding.ActivityDetailBinding
import com.example.githubsubmission.viewmodel.DetailViewModel
import com.example.githubsubmission.viewmodel.ViewModelFactory
import com.google.android.material.tabs.TabLayoutMediator

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private var favoriteUser: FavoriteUser? = null
    private lateinit var username: String
    private var isFavorite: Boolean = false
    private val detailViewModel by viewModels<DetailViewModel> {
        ViewModelFactory.getInstance(application)
    }

    @StringRes
    private val TAB_TEXT = intArrayOf(
        R.string.following,
        R.string.followers
    )

    companion object {
        const val EXTRA_NAME = "extra_username"
        const val TAG = "Detail Activity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.detail)
        username = ""

        val user = intent.getStringExtra(EXTRA_NAME)
        val bundle = Bundle()
        bundle.putString(EXTRA_NAME, user)

        detailViewModel.getFavoriteByUsername(user!!).observe(this) {
            isFavorite = it != null

            println(it)
            if (it != null) {
                isFavorite = true
                binding.btnUnfavorite.setImageResource(R.drawable.ic_favorite)
            } else {
                isFavorite = false
                binding.btnUnfavorite.setImageResource(R.drawable.ic_favorite_border)
            }
        }

        if (user != null) {
            detailViewModel.setDetail(user)
        }

        detailViewModel.getDetail().observe(this) {
            if (it != null) {
                binding.apply {
                    Glide.with(this@DetailActivity)
                        .load(it.avatarUrl)
                        .circleCrop()
                        .into(avatar)
                    favoriteUser = FavoriteUser(it.login.toString(), it.avatarUrl)
                }
                binding.username.text = it.login
                binding.fullname.text = it.name
                binding.following.text = it.following.toString()
                binding.followers.text = it.followers.toString()
                binding.location.text = it.location
                binding.company.text = it.company
                showLoading(false)
            }
            showLoading(false)
        }

        binding.btnUnfavorite.setOnClickListener {
            if (!isFavorite) {
                favoriteUser?.let { detailViewModel.insert(it) }
                Toast.makeText(applicationContext, "Add to Favorite", Toast.LENGTH_SHORT).show()
                Log.d(TAG, "this insert")
                val intent = Intent(this, FavoriteActivity::class.java)
                startActivity(intent)
            } else {
                favoriteUser?.let { detailViewModel.delete(it) }
                Toast.makeText(applicationContext, "Remove from Favorite", Toast.LENGTH_SHORT).show()
                Log.d(TAG, "this delete")
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }

        val adapter = SectionsPagerAdapter(this)
        adapter.username = user.toString()
        binding.viewPager.adapter = adapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TEXT[position])
        }.attach()
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    override fun onOptionsItemSelected(back: MenuItem): Boolean {
        when (back.itemId) {
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(back)
    }
}