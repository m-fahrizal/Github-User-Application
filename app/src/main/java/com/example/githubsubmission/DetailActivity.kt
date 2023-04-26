package com.example.githubsubmission

import android.content.Intent
import android.content.res.ColorStateList
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.githubsubmission.adapter.FavoriteAdapter
import com.example.githubsubmission.adapter.SectionsPagerAdapter
import com.example.githubsubmission.database.FavoriteUser
import com.example.githubsubmission.databinding.ActivityDetailBinding
import com.example.githubsubmission.viewmodel.DetailViewModel
import com.example.githubsubmission.viewmodel.FavoriteViewModel
import com.example.githubsubmission.viewmodel.MainViewModel
import com.example.githubsubmission.viewmodel.ViewModelFactory
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private var favoriteUser: FavoriteUser? = null
    private lateinit var username: String
    private var isFavorite = false
    private lateinit var adapter: FavoriteAdapter
    private lateinit var viewPager: ViewPager
    private lateinit var tabLayout: TabLayout
    private val detailViewModel by viewModels<DetailViewModel>() {
        ViewModelFactory.getInstance(application)
    }

    @StringRes
    private val TAB_TEXT = intArrayOf(
        R.string.following,
        R.string.followers
    )

    companion object{
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

//        adapter = FavoriteAdapter()
        val user = intent.getStringExtra(EXTRA_NAME)
        val bundle = Bundle()
        bundle.putString(EXTRA_NAME, user)

//        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
//            DetailViewModel::class.java)

//        detailViewModel = obtainViewModel(this@DetailActivity)

        if (user != null) {
            detailViewModel.setDetail(user)
        }

        detailViewModel.getDetail().observe(this){
            if (it != null) {
                binding.apply {
                    Glide.with(this@DetailActivity)
                        .load(it.avatarUrl)
                        .circleCrop()
                        .into(avatar)
                    favoriteUser = FavoriteUser(it.name.toString(), it.avatarUrl)
                }
                binding.username.text = it.login
                binding.fullname.text = it.name
                binding.following.text = it.following.toString()
                binding.followers.text = it.followers.toString()
                binding.location.text = it.location
                binding.company.text = it.company
                showLoading(true)
            }
            showLoading(false)
        }

        detailViewModel.getFavoriteByUsername(username).observe(this) {
            isFavorite = if (it != null) {
                binding.btnFavorite.setImageResource(R.drawable.ic_favorite)
//                binding.fabFavorite.changeIconColor(R.color.red)
                true
            }else{
                binding.btnFavorite.setImageResource(R.drawable.ic_favorite_border)
//                binding.fabFavorite.changeIconColor(R.color.white)
                false
            }
//            isFavorite = !isFavorite
        }


        binding.btnFavorite.setOnClickListener {
            if (it.id == R.id.btn_favorite) {
                if (isFavorite) {
                    favoriteUser?.let { detailViewModel.delete(it) }
                    Log.d(TAG, "this delete")
                } else {
                    favoriteUser?.let { detailViewModel.insert(it) }
                    Log.d(TAG, "this insert")
                }
            }
            isFavorite = !isFavorite
        }

        // Set adapter untuk ViewPager
        val adapter = SectionsPagerAdapter(this)
        adapter.username = user.toString()
        binding.viewPager.adapter = adapter

//        // Set TabLayout dengan ViewPager
//        tabLayout.setupWithViewPager(viewPager)

        TabLayoutMediator(binding.tabLayout, binding.viewPager){ tab, position ->
            tab.text = resources.getString(TAB_TEXT[position])
        }.attach()

        //        val favoriteViewModel = obtainViewModel(this@DetailActivity)
//        favoriteViewModel.getAllFavorites().observe(this) {
//            if (it != null){
//                adapter.setListFavorite(it)
//            }
//        }

//        val fragments = mutableListOf<Fragment>(
//            FollowFragment.newInstance(FollowFragment.FOLLOWING),
//            FollowFragment.newInstance(FollowFragment.FOLLOWERS)
//        )
//
//        val adapter = SectionsPagerAdapter(this, fragments)
//        binding.viewPager.adapter = adapter

//        val sectionsPagerAdapter = SectionsPagerAdapter(this)
//        val viewPager: ViewPager2 = findViewById(R.id.view_pager)
//        viewPager.adapter = sectionsPagerAdapter
//        val tabs: TabLayout = findViewById(R.id.tab_layout)


//        // Inisialisasi ViewPager dan TabLayout
//        viewPager = findViewById(R.id.view_pager)
//        tabLayout = findViewById(R.id.tab_layout)

//        // Mendapatkan username pengguna dari Intent
//        val username = intent.getStringExtra("username")
    }

//    private fun obtainViewModel(activity: AppCompatActivity): FavoriteViewModel {
//        val factory = ViewModelFactory.getInstance(activity.application)
//        return ViewModelProvider(activity, factory).get(FavoriteViewModel::class.java)
//    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    override fun onOptionsItemSelected(back: MenuItem): Boolean {
        when (back.itemId){
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(back)
    }

//    private fun obtainViewModel(activity: AppCompatActivity): DetailViewModel {
//        val vmFactory = ViewModelFactory.getInstance(activity.application)
//        return ViewModelProvider(activity, vmFactory)[DetailViewModel::class.java]
//    }
}

fun FloatingActionButton.changeIconColor(@ColorRes color: Int){
    imageTintList = ColorStateList.valueOf(ContextCompat.getColor(this.context, color))
}