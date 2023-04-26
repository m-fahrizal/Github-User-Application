package com.example.githubsubmission.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubsubmission.DetailActivity
import com.example.githubsubmission.data.User
import com.example.githubsubmission.databinding.ItemRowUsersBinding



class UserAdapter(private val listUser: List<User>) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {
    private var onItemClickCallBack: OnItemClickCallBack? = null

    inner class UserViewHolder(var binding: ItemRowUsersBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = ItemRowUsersBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    override fun getItemCount(): Int = listUser.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val (username, avatarUrl) = listUser[position]
        holder.binding.tvItemName.text = username
        Glide.with(holder.itemView.context)
            .load(avatarUrl)
            .into(holder.binding.imgItemPhoto)

        holder.itemView.setOnClickListener {

            val intentDetail = Intent(holder.itemView.context, DetailActivity::class.java)
            intentDetail.putExtra("extra_username", username)
            holder.itemView.context.startActivity(intentDetail)
        }
    }

    fun setOnItemClickCallBack(onItemClickCallBack: OnItemClickCallBack) {
        this.onItemClickCallBack = onItemClickCallBack
    }

    interface OnItemClickCallBack {
        fun onItemClicked(data: User)
    }
}
