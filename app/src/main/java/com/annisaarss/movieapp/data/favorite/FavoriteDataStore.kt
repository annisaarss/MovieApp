package com.annisaarss.movieapp.data.favorite

import com.annisaarss.movieapp.data.favorite.local.FavoriteDao
import com.annisaarss.movieapp.data.favorite.local.FavoriteEntity
import io.reactivex.Completable
import io.reactivex.Single

class FavoriteDataStore(
    db: FavoriteDao
) : FavoriteRepository {
    override fun addFavorite(favoriteEntity: FavoriteEntity): Completable {
        return Completable.fromAction {
            dbService.save(favoriteEntity)
        }
    }

    override fun getFavorites(): Single<List<FavoriteEntity>> {
        return dbService.getList()
    }

    override fun getFavorite(id: Int): Single<FavoriteEntity> {
        return dbService.get(id)
    }

    override fun removeFavorite(id: Int): Completable {
        return Completable.fromAction {
            dbService.remove(id)
        }
    }

    override fun removeAllFavorite(): Completable {
        return Completable.fromAction {
            dbService.removeAll()
        }
    }

    override fun checkFavorite(idItem: String): Single<FavoriteEntity> {
        return dbService.check(idItem)
    }

    override val dbService = db
    override val webService = null


}