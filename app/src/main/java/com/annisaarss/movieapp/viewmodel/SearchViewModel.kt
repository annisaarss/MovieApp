package com.annisaarss.movieapp.viewmodel

import androidx.lifecycle.MutableLiveData
import com.annisaarss.movieapp.base.BaseViewModel
import com.annisaarss.movieapp.domain.movie.MovieUseCase
import com.annisaarss.movieapp.domain.movie.model.MostPopularDetail
import com.nbs.nucleo.data.Result
import com.nbs.nucleo.utils.extensions.addTo
import com.nbs.nucleo.utils.rx.apihandler.genericErrorHandler
import com.nbs.nucleo.utils.rx.transformers.singleScheduler
import io.reactivex.disposables.CompositeDisposable

class SearchViewModel(
    private val movieUseCase: MovieUseCase,
    disposable: CompositeDisposable
) : BaseViewModel(disposable) {
    val resultSearch = MutableLiveData<Result<List<MostPopularDetail>>>()

    init {
        resultSearch.value = Result.default()
    }

    fun getResultSearch(expression: String) {
        resultSearch.value = Result.loading()
        movieUseCase.searchMovie(expression)
            .compose(singleScheduler())
            .subscribe({
                resultSearch.value = Result.success(it)
            }, {
                genericErrorHandler(it, resultSearch)
            }).addTo(disposable)
    }
}