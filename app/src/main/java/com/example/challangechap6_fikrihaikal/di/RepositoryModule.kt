package com.example.challangechap6_fikrihaikal.di

import com.example.challangechap6_fikrihaikal.model.data.repository.LocalRepository
import com.example.challangechap6_fikrihaikal.model.data.repository.LocalRepositoryImpl
import com.example.challangechap6_fikrihaikal.model.data.repository.NetworkRepository
import com.example.challangechap6_fikrihaikal.model.data.repository.NetworkRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

//anotasi module menandakan bahwa object NetworkModule adalah modul dagger
@Module
//anotasi yang menentukan bahwa modul ini akan diinstall di dalam komponen dagger
@InstallIn(SingletonComponent::class)
//abstract class ini yang berfungsi sebagai modul dagger
abstract class RepositoryModule {

    @Binds
    abstract fun provideNetworkRepository(networkRepositoryImpl: NetworkRepositoryImpl): NetworkRepository

    @Binds
    abstract fun provideLocalRepository(localRepositoryImpl: LocalRepositoryImpl): LocalRepository

}