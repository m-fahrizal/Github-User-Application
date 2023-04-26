package com.example.githubsubmission.adapter

import android.content.ClipData
import android.content.Context
import android.content.Intent
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubsubmission.DetailActivity
import com.example.githubsubmission.FavoriteActivity
import com.example.githubsubmission.R
import com.example.githubsubmission.data.User
import com.example.githubsubmission.database.FavoriteUser
import com.example.githubsubmission.database.GithubDao
import com.example.githubsubmission.databinding.ItemRowUsersBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoriteAdapter (private val listFavorite: List<FavoriteUser>) : RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {

    class FavoriteViewHolder(var binding: ItemRowUsersBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val bind = ItemRowUsersBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteViewHolder(bind)
    }

    override fun getItemCount(): Int {
        return listFavorite.size
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val (username, avatarUrl) = listFavorite[position]
        holder.binding.tvItemName.text = username
        Glide.with(holder.itemView.context)
            .load(avatarUrl)
            .into(holder.binding.imgItemPhoto)

        holder.itemView.setOnClickListener {
            val intentDetailActivity = Intent(holder.itemView.context, DetailActivity::class.java)
            intentDetailActivity.putExtra("extra_username", username)
            holder.itemView.context.startActivity(intentDetailActivity)
        }
    }
}

//class FavoriteAdapter: RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>()  {
//
//    var listFavorite = ArrayList<FavoriteUser>()
//
//    fun setListFavorite(listFavorite: List<FavoriteUser>) {
//        val diffCallback = FavDiffCallback(this.listFavorite, listFavorite)
//        val diffResult = DiffUtil.calculateDiff(diffCallback)
//        this.listFavorite.clear()
//        this.listFavorite.addAll(listFavorite)
//        diffResult.dispatchUpdatesTo(this)
//    }
//
//    inner class FavoriteViewHolder (private val binding: ItemRowUsersBinding) : RecyclerView.ViewHolder(binding.root) {
//        fun bind(favorite: FavoriteUser){
//            with(binding){
//                tvItemName.text = favorite.userName
//                cardView.setOnClickListener{
//                    val intent = Intent(it.context, FavoriteActivity::class.java)
//                    intent.putExtra(FavoriteActivity.EXTRA_NAME, favorite)
//                    it.context.startActivity(intent)
//                }
//            }
//        }
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
//        val binding = ItemRowUsersBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//        return FavoriteViewHolder(binding)
//    }
//
//    override fun getItemCount(): Int = listFavorite.size
//
//    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
//        holder.bind(listFavorite[position])
//    }
//
//    interface OnItemClickCallback {
//        fun onItemClicked(selectedNote: FavoriteUser?, position: Int?)
//    }
//
//    fun addItem(favorite: FavoriteUser) {
//        this.listFavorite.add(favorite)
//        notifyItemInserted(this.listFavorite.size - 1)
//    }
//    fun updateItem(position: Int, favorite: FavoriteUser) {
//        this.listFavorite[position] = favorite
//        notifyItemChanged(position, favorite)
//    }
//    fun removeItem(position: Int) {
//        this.listFavorite.removeAt(position)
//        notifyItemRemoved(position)
//        notifyItemRangeChanged(position, this.listFavorite.size)
//    }
//}



//class FavoriteAdapter(private val items: List<FavoriteUser>, private val favoriteDao: GithubDao) :
//    RecyclerView.Adapter<FavoriteAdapter.ViewHolder>() {
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_row_users, parent, false)
//        return ViewHolder(view)
//    }
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        val item = items[position]
//        holder.bind(item)
//        holder.itemView.findViewById<Button>(R.id.btn_favorite).setOnClickListener {
//            val favorite = FavoriteUser(
//                item.userName,
//                item.avatarUrl
//            )
//            CoroutineScope(Dispatchers.IO).launch {
//                favoriteDao.insert(favorite)
//            }
//            Toast.makeText(holder.itemView.context, "Added to favorites", Toast.LENGTH_SHORT)
//                .show()
//        }
//    }
//
//    override fun getItemCount() = items.size
//
//    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        fun bind(item: FavoriteUser) {
//            itemView.findViewById<ImageView>(R.id.img_item_photo).setImageResource(item.avatarUrl)
//            itemView.findViewById<TextView>(R.id.username).text = item.userName
//        }
//    }
//}
