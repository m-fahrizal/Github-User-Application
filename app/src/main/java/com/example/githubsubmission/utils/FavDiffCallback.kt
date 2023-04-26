package com.example.githubsubmission.utils

import android.provider.ContactsContract
import androidx.recyclerview.widget.DiffUtil
import com.example.githubsubmission.database.FavoriteUser

//class FavDiffCallback (private val mOldFavList: List<FavoriteUser>, private val mNewFavList: List<FavoriteUser>) : DiffUtil.Callback(){
//    override fun getOldListSize(): Int {
//        return mOldFavList.size
//    }
//    override fun getNewListSize(): Int {
//        return mNewFavList.size
//    }
//    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
////        return mOldFavList[oldItemPosition].id == mNewFavList[newItemPosition].id
//        return false
//    }
//    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
//        val oldEmployee = mOldFavList[oldItemPosition]
//        val newEmployee = mNewFavList[newItemPosition]
//        return oldEmployee.userName == newEmployee.userName && oldEmployee.avatarUrl == newEmployee.avatarUrl
//    }
//}