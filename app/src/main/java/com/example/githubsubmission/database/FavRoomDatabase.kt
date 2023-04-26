package com.example.githubsubmission.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.githubsubmission.utils.SettingPreferences

@Database(entities = [FavoriteUser::class], version = 1)
abstract class FavRoomDatabase: RoomDatabase() {
    abstract fun favDao(): GithubDao

    companion object{
        @Volatile
        private var INSTANCE: FavRoomDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context): FavRoomDatabase {
            if (INSTANCE == null){
                synchronized(FavRoomDatabase::class.java){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        FavRoomDatabase::class.java,
                        "favorite_database")
                        .build()
                }
            }
            return INSTANCE as FavRoomDatabase
        }
    }
}