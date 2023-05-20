package com.example.challangechap6_fikrihaikal.model.data.repository


import com.example.challangechap6_fikrihaikal.model.api.ApiService
import DetailMovieResponse
import MovieResponse
import javax.inject.Inject

//sebuah interface yang menentukan fun yang dapat dilakukan
interface NetworkRepository {

    //berfungsi mengembalikan respon MoviewRespon yang sedang diputar. dan mengambil parameter page dengan nilai defaultnya 1
    suspend fun getAllMoviesNowPlaying(
        page: Int = 1
    ): MovieResponse
    //mengembalikan respon DetailMovie yang berisi detail film dengan ID yang diberikan
    suspend fun getDetailMovie(
        movieId: Int
    ): DetailMovieResponse
}
//class ini mengimplementasikan dengan menggunakan construktor yang di inject dan apiService sebagai parameter
class NetworkRepositoryImpl @Inject constructor(private val apiService: ApiService) :
    NetworkRepository {
    override suspend fun getAllMoviesNowPlaying(page: Int): MovieResponse =
        apiService.getAllMoviesNowPlaying()

    override suspend fun getDetailMovie(movieId: Int): DetailMovieResponse =
        apiService.getDetailMovie(movieId)

}