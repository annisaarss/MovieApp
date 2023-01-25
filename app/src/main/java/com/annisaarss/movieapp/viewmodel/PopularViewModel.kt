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

class PopularViewModel(
    private val movieUseCase: MovieUseCase,
    disposable: CompositeDisposable
) : BaseViewModel(disposable) {
    val listPopular = MutableLiveData<Result<List<MostPopularDetail>>>()

    init {
        listPopular.value = Result.default()
    }

    fun getMostPopular() {
        listPopular.value = Result.loading()
        movieUseCase.getMostPopular()
            .compose(singleScheduler())
            .subscribe({
                listPopular.value = Result.success(it)
            }, {
                genericErrorHandler(it, listPopular)
            }).addTo(disposable)
    }
}