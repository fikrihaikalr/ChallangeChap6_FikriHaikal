package com.example.challangechap6_fikrihaikal.model.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.challangechap6_fikrihaikal.model.data.local.dao.FavoriteDao
import com.example.challangechap6_fikrihaikal.model.data.local.entity.FavoriteEntity

//anotasi ini menandakan bahwa class AppDatabase adalah definisi database dengan menggunakan Room
//dan menggunakan parameter entities dalam FavoriteEntity
@Database(entities = [FavoriteEntity::class], version = 1, exportSchema = false)
//Kelas ini merupakan kelas abstrak yang merupakan turunan dari RoomDatabase. Kelas ini berfungsi sebagai titik masuk untuk mengakses dan mengelola database.
abstract class  AppDatabase : RoomDatabase() {

    //berfungsi untuk mengembalikan object FavoriteDao
    abstract fun favoriteDao(): FavoriteDao
}