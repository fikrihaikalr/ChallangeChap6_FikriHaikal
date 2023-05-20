package com.example.challangechap6_fikrihaikal.di

import com.example.challangechap6_fikrihaikal.model.api.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

//anotasi module menandakan bahwa object NetworkModule adalah modul dagger
@Module
//anotasi yang menentukan bahwa modul ini akan diinstall di dalam komponen dagger
@InstallIn(SingletonComponent::class)
//object ini yang berfungsi sebagai modul dagger
object NetworkModule {
    @Singleton
    @Provides
    //fungsi ini menghasilkan instansi Retrofit yang digunakan untuk melakukan komunikasi jaringan
    fun provideRetrofit(): Retrofit {
        val loggingInterceptor = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Singleton
    @Provides
    // Fungsi ini menyediakan instansi ApiService yang mewakili layanan API yang digunakan untuk berkomunikasi dengan server jaringan.
    fun provideApiService(retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)
}