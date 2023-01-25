package com.annisaarss.movieapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.annisaarss.movieapp.domain.favorite.FavoriteUseCase
import com.annisaarss.movieapp.domain.favorite.model.Favorite
import com.nbs.nucleo.data.Result
import com.nbs.nucleo.utils.extensions.addTo
import com.nbs.nucleo.utils.rx.apihandler.genericErrorHandler
import com.nbs.nucleo.utils.rx.transformers.completableScheduler
import com.nbs.nucleo.utils.rx.transformers.singleScheduler
import io.reactivex.disposables.CompositeDisposable

class FavoriteViewModel(
    private val favoriteUseCase: FavoriteUseCase,
    private val disposable: CompositeDisposable
) : ViewModel() {

    val getFavorites = MutableLiveData<Result<List<Favorite>>>()
    val addFavorite = MutableLiveData<Result<Unit>>()
    val getFavorite = MutableLiveData<Result<Favorite>>()
    val removeFavorite = MutableLiveData<Result<Unit>>()
    val removeAllFavorite = MutableLiveData<Result<Unit>>()
    val checkFavorite = MutableLiveData<Result<Favorite>>()
    val searchFavorite = MutableLiveData<Result<List<Favorite>>>()

    init {
        getFavorites.value = Result.default()
        addFavorite.value = Result.default()
        getFavorite.value = Result.default()
        removeFavorite.value = Result.default()
        removeAllFavorite.value = Result.default()
        checkFavorite.value = Result.default()
        searchFavorite.value = Result.default()
    }

    fun getFavorites() {
        getFavorites.value = Result.loading()

        favoriteUseCase.getFavorites()
            .compose(singleScheduler())
            .subscribe({
                if (it.isEmpty()) getFavorites.value = Result.empty()
                else getFavorites.value = Result.success(it)
            }, { genericErrorHandler(it, getFavorites) })
            .addTo(disposable)
    }

    fun searchFavorite(query: String) {
        searchFavorite.value = Result.loading()

        favoriteUseCase.searchFavorite(query)
            .compose(singleScheduler())
            .subscribe({
                if (it.isEmpty()) searchFavorite.value = Result.empty()
                else searchFavorite.value = Result.success(it)
            }, { genericErrorHandler(it, searchFavorite) })
            .addTo(disposable)
    }

    fun getFavorite(favoriteId: Int) {
        getFavorite.value = Result.loading()

        favoriteUseCase.getFavorite(favoriteId)
            .compose(singleScheduler())
            .subscribe({
                getFavorite.value = Result.success(it)
            }, { genericErrorHandler(it, getFavorite) })
            .addTo(disposable)
    }

    fun addFavorite(
        id: Int,
        idItem: String,
        title: String,
        image: String,
        year: String,
        genre: String
    ) {
        addFavorite.value = Result.loading()

        favoriteUseCase.addFavorite(id, idItem, title, image, year, genre)
            .compose(completableScheduler<Unit>())
            .subscribe({
                addFavorite.value = Result.success(Unit)
            }, { genericErrorHandler(it, addFavorite) })
            .addTo(disposable)
    }

    fun removeFavorite(favoriteId: Int) {
        removeFavorite.value = Result.loading()

        favoriteUseCase.removeFavorite(favoriteId)
            .compose(completableScheduler<Unit>())
            .subscribe({
                removeFavorite.value = Result.success(Unit)
            }, { genericErrorHandler(it, removeFavorite) })
            .addTo(disposable)
    }

    fun removeAllFavorite() {
        removeAllFavorite.value = Result.loading()

        favoriteUseCase.removeAllFavorite()
            .compose(completableScheduler<Unit>())
            .subscribe({
                removeAllFavorite.value = Result.success(Unit)
            }, { genericErrorHandler(it, removeAllFavorite) })
            .addTo(disposable)
    }

    fun checkFavorite(itemId: String) {
        checkFavorite.value = Result.loading()

        favoriteUseCase.checkFavorite(itemId)
            .compose(singleScheduler())
            .subscribe({
                checkFavorite.value = Result.success(it)
            }, { genericErrorHandler(it, checkFavorite) })
            .addTo(disposable)
    }

    override fun onCleared() {
        super.onCleared()

        if (!disposable.isDisposed)
            disposable.dispose()
    }
}