package com.annisaarss.movieapp.data.favorite.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.annisaarss.movieapp.domain.favorite.model.Favorite
import com.nbs.nucleo.data.RxLocalDb
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

@Dao
abstract class FavoriteDao : RxLocalDb<FavoriteEntity> {

    @Query("SELECT * FROM favorite WHERE id = :intId")
    abstract override fun get(intId: Int?): Single<FavoriteEntity>

    @Query("SELECT * FROM favorite")
    abstract override fun getList(): Single<List<FavoriteEntity>>

    @Query("DELETE FROM favorite WHERE id = :intId")
    abstract override fun remove(intId: Int?)

    @Query("DELETE FROM favorite")
    abstract override fun removeAll()

    @Query("SELECT * FROM favorite WHERE idItem= :stringId")
    abstract fun check(stringId: String?): Single<FavoriteEntity>

    override fun isCached(): Single<Boolean> {
        return getList().subscribeOn(Schedulers.io()).map { !it.isEmpty() }
    }

    override fun isCacheExpired(): Single<Boolean> {
        return Single.just(false)
    }

    override fun isItemCached(intId: Int?, strId: String?): Single<Boolean> {
        return Single.just(false)
    }

    override fun isItemCacheExpired(intId: Int?, strId: String?): Single<Boolean> {
        return Single.just(true)
    }
}