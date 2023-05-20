package com.example.challangechap6_fikrihaikal.di

import android.content.Context
import androidx.room.Room
import com.example.challangechap6_fikrihaikal.model.data.local.AppDatabase
import com.example.challangechap6_fikrihaikal.model.data.local.dao.FavoriteDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


//anotasi yang menandakan bahwa class LocalModule adalah modul degger
@Module

//anotasi yang menentukan bahwa modul ini akan diinstall didalam komponen dagger SingelComponent
@InstallIn(SingletonComponent::class)

//ini merupakan object tunggal kotlin yang berfungsi sebagai modul dagger
object LocalModule {

    @Singleton
    @Provides
    // Fungsi ini menyediakan instansiasi objek AppDatabase yang mewakili database aplikasi.
    fun providesDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "movie.db"
        ).fallbackToDestructiveMigration().build()

    @Provides
    //fun ini berfungsi menyediakan isntansiasi obeject pada FavoriteDao dengan parameter appDatabase yang diinjeksikan oleh dagger
    fun provideFavoriteDao(appDatabase: AppDatabase): FavoriteDao =
        appDatabase.favoriteDao()
}