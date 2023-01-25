package com.annisaarss.movieapp.data.favorite.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.annisaarss.movieapp.domain.favorite.model.Favorite
import com.nbs.nucleosnucleo.Model

@Entity(tableName = "favorite")
data class FavoriteEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "idItem")
    val idItem: String,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "image")
    val image: String,
    @ColumnInfo(name = "year")
    val year: String,
    @ColumnInfo(name = "genre")
    val genre: String

) : Model() {

    fun toEntity(): FavoriteEntity {
        return FavoriteEntity(
            id = id,
            idItem = idItem,
            title = title,
            image = image,
            year = year,
            genre = genre
        )
    }

    fun toDomain(): Favorite {
        return Favorite(
            id = id,
            idItem = idItem,
            title = title,
            image = image,
            year = year,
            genre = genre
        )
    }
}