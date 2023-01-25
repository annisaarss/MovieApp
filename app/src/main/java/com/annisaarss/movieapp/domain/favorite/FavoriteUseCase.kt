package com.annisaarss.movieapp.domain.favorite

import com.annisaarss.movieapp.domain.favorite.model.Favorite
import io.reactivex.Completable
import io.reactivex.Single

interface FavoriteUseCase {
    fun addFavorite(
        id: Int,
        idItem: String,
        title: String,
        image: String,
        year: String,
        genre: String
    ): Completable

    fun getFavorites(): Single<List<Favorite>>
    fun getFavorite(id: Int): Single<Favorite>
    fun removeFavorite(id: Int): Completable
    fun removeAllFavorite(): Completable
    fun checkFavorite(idItem: String): Single<Favorite>
    fun searchFavorite(query: String): Single<List<Favorite>>
}