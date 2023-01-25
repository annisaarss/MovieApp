package com.annisaarss.movieapp.data.favorite

import com.annisaarss.movieapp.data.favorite.local.FavoriteEntity
import com.nbs.nucleo.data.BaseRepository
import io.reactivex.Completable
import io.reactivex.Single

interface FavoriteRepository : BaseRepository {
    fun addFavorite(favoriteEntity: FavoriteEntity): Completable
    fun getFavorites(): Single<List<FavoriteEntity>>
    fun getFavorite(id: Int): Single<FavoriteEntity>
    fun removeFavorite(id: Int): Completable
    fun removeAllFavorite(): Completable
    fun checkFavorite(idItem: String): Single<FavoriteEntity>
}