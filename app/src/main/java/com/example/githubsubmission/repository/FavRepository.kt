package com.example.githubsubmission.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.githubsubmission.database.FavRoomDatabase
import com.example.githubsubmission.database.FavoriteUser
import com.example.githubsubmission.database.GithubDao

import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class FavRepository(application: Application){

    private val mFavDao: GithubDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = FavRoomDatabase.getDatabase(application)
        mFavDao = db.favDao()
    }

    fun getAllFavorites(): LiveData<List<FavoriteUser>> = mFavDao.getAllFavorite()
    fun insert(favorite: FavoriteUser) {
        executorService.execute {
            mFavDao.insert(favorite)
        }
    }
    fun delete(favorite: FavoriteUser) {
        executorService.execute {
            mFavDao.delete(favorite)
        }
    }
    fun update(favorite: FavoriteUser) {
        executorService.execute {
            mFavDao.update(favorite)
        }
    }

    fun getFavoriteByUsername(username: String): LiveData<FavoriteUser> {
        return  mFavDao.getFavoriteUserByUsername(username)
    }
}

//    private val apiService: ApiService,
//    private val db: FavRoomDatabase
////    private val favDao: GithubDao,
////    private val appExecutors: AppExecutors
//) {
//
//    suspend fun getRepos(): List<FavoriteUser> {
//        // Panggil API GitHub untuk mengambil data repositori
//        val repos = apiService.getFavorite("asep")
//
//        // Simpan data repositori ke dalam Room Database
//        val repoEntities = repos.map { repo ->
//            FavoriteUser(repo.id, repo.userName, repo.avatarUrl)
//        }
//        db.favDao().insert(repoEntities)
//
//        // Mengembalikan data repositori sebagai list dari model data Repo
//        return repos
//    }
//
//    fun getFavoriteRepos(): List<FavoriteUser> {
//        // Ambil data repositori favorit dari Room Database
//        val repoEntities = db.favDao().getAllFavorite()
//
//        // Mengembalikan data repositori favorit sebagai list dari model data Repo
//        return repoEntities.map { repoEntity ->
//            Repo(repoEntity.id, repoEntity.name, repoEntity.description)
//        }
//    }
//
//    suspend fun addFavoriteRepo(repo: Repo) {
//        // Menyimpan repositori favorit ke dalam Room Database
//        val repoEntity = RepoEntity(repo.id, repo.name, repo.description)
//        db.repoDao().insertRepo(repoEntity)
//    }
//
//    suspend fun removeFavoriteRepo(repo: Repo) {
//        // Menghapus repositori favorit dari Room Database
//        val repoEntity = RepoEntity(repo.id, repo.name, repo.description)
//        db.repoDao().deleteRepo(repoEntity)
//    }


//    private val result = MediatorLiveData<Result<List<FavoriteUser>>>()
//
//    fun getFavorite(username: String): LiveData<Result<List<FavoriteUser>>> {
//        val clientDetail = ApiConfig.getApiService().getDetailUser(username)
//        clientDetail.enqueue(object : Callback<DetailUserResponse> {
//            override fun onResponse(
//                call: Call<DetailUserResponse>,
//                response: Response<DetailUserResponse>
//            ) {
//                if (response.isSuccessful) {
//                    _detailUser.postValue(response.body())
//                }
//            }
//            override fun onFailure(call: Call<DetailUserResponse>, t: Throwable) {
//                Log.e("Failure", "onFailure: ${t.message}")
//            }
//        })
////        val localData = favDao.getNews()
////        result.addSource(localData) { newData: List<FavoriteUser> ->
////            result.value = Result.Success(newData)
////        }
////        return result
//    }
//
//    companion object {
//        @Volatile
//        private var instance: FavRepository? = null
//        fun getInstance(
//            apiService: ApiService,
//            newsDao: GithubDao,
//            appExecutors: AppExecutors
//        ): FavRepository =
//            instance ?: synchronized(this) {
//                instance ?: FavRepository()
//            }.also { instance = it }
//    }

