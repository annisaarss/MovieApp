package com.annisaarss.movieapp.domain.favorite.model

import com.annisaarss.movieapp.data.favorite.local.FavoriteEntity

data class Favorite(
    val id: Int,
    val idItem: String,
    val title: String,
    val image: String,
    val year: String,
    val genre: String
) {

    fun toEntity(): FavoriteEntity {
        return FavoriteEntity(
            id, idItem, title, image, year, genre
        )
    }
}