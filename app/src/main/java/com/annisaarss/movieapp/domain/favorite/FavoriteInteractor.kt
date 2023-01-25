package com.annisaarss.movieapp.domain.favorite

import com.annisaarss.movieapp.data.favorite.FavoriteRepository
import com.annisaarss.movieapp.domain.favorite.model.Favorite
import io.reactivex.Completable
import io.reactivex.Single

class FavoriteInteractor(private val repository: FavoriteRepository) : FavoriteUseCase {
    override fun addFavorite(
        id: Int,
        idItem: String,
        title: String,
        image: String,
        year: String,
        genre: String
    ): Completable {
        return repository.addFavorite(
            Favorite(
                id = id,
                idItem = idItem,
                title = title,
                image = image,
                year = year,
                genre = genre
            ).toEntity()
        )
    }

    override fun getFavorites(): Single<List<Favorite>> {
        return repository.getFavorites().map {
            it.map {
                it.toDomain()
            }
        }
    }

    override fun getFavorite(id: Int): Single<Favorite> {
        return repository.getFavorite(id).map {
            it.toDomain()
        }
    }

    override fun removeFavorite(id: Int): Completable {
        return repository.removeFavorite(id)
    }

    override fun removeAllFavorite(): Completable {
        return repository.removeAllFavorite()
    }

    override fun checkFavorite(idItem: String): Single<Favorite> {
        return repository.checkFavorite(idItem).map {
            it.toDomain()
        }
    }

    override fun searchFavorite(query: String): Single<List<Favorite>> {
        return repository.getFavorites()
            .map {
            it.filter {
                it.title.lowercase().contains(query.lowercase())
            }.map {
                it.toDomain()
            }
        }
    }
}