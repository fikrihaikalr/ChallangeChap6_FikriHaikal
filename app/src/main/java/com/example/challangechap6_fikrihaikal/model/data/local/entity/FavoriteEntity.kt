package com.example.challangechap6_fikrihaikal.model.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//Anotasi ini menandakan bahwa kelas FavoriteEntity adalah entitas dalam database dengan nama tabel "favorite_table"
@Entity(tableName = "favorite_table")
data class FavoriteEntity(
    @PrimaryKey
    @ColumnInfo(name = "id_movie")
    val id_movie: Int,
    @ColumnInfo(name = "photo_movie")
    val photo: String,
    @ColumnInfo(name = "title_movie")
    val title: String,
    @ColumnInfo(name = "uuid_user")
    val uuid_user: String,
)
