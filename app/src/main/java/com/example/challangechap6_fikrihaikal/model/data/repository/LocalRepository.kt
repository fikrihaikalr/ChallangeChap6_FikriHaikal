package com.example.challangechap6_fikrihaikal.model.data.repository


import com.example.challangechap6_fikrihaikal.model.data.local.dao.FavoriteDao
import com.example.challangechap6_fikrihaikal.model.data.local.entity.FavoriteEntity
import javax.inject.Inject


interface LocalRepository {
    //berfungsi untuk mengembalikan nilai pada FavoriteEntity
    suspend fun getFavoriteTickets(uuid_user: String): List<FavoriteEntity>
    //fungsi untuk memeriksa apakah tiket dengan Id film yang diberikan adalah fovorit untuk pengguna
    suspend fun isFavorite(id_movie: Int, uuid_user: String): Boolean
    //berfungsi untuk menambahkan entitas pada FavoriteEntity
    suspend fun addFavorite(favoriteEntity: FavoriteEntity)
    //fungsi ini menghapus tiket favorit dengan ID film dan UUID pengguna yang diberikan dari penyimpanan local
    suspend fun deleteFavorite(id_movie: Int, uuid_user: String)
}

//class ini diimplementasikan dengan menggunakan constructor yang di (INJECT) dan favoriteDao sebagai parameter
class LocalRepositoryImpl @Inject constructor(private val favoriteDao: FavoriteDao) :
    LocalRepository {
    override suspend fun getFavoriteTickets(uuid_user: String): List<FavoriteEntity> =
        favoriteDao.getFavoriteTickets(uuid_user)

    override suspend fun isFavorite(id_movie: Int, uuid_user: String): Boolean =
        favoriteDao.isFavorite(id_movie, uuid_user)

    override suspend fun addFavorite(favoriteEntity: FavoriteEntity) =
        favoriteDao.addFavorite(favoriteEntity)

    override suspend fun deleteFavorite(id_movie: Int, uuid_user: String) =
        favoriteDao.deleteFavorite(id_movie, uuid_user)

}