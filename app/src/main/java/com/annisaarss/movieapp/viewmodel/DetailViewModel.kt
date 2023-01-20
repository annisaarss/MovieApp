package com.annisaarss.movieapp.viewmodel

import androidx.lifecycle.MutableLiveData
import com.annisaarss.movieapp.base.BaseViewModel
import com.annisaarss.movieapp.domain.movie.MovieUseCase
import com.annisaarss.movieapp.domain.movie.model.DetailMovieDetail
import com.annisaarss.movieapp.domain.movie.model.MostPopularDetail
import com.nbs.nucleo.data.Result
import com.nbs.nucleo.utils.extensions.addTo
import com.nbs.nucleo.utils.rx.apihandler.genericErrorHandler
import com.nbs.nucleo.utils.rx.transformers.singleScheduler
import io.reactivex.disposables.CompositeDisposable

class DetailViewModel (
    private val movieUseCase: MovieUseCase,
    disposable: CompositeDisposable
) : BaseViewModel(disposable) {
    val detailMovie = MutableLiveData<Result<DetailMovieDetail>>()

    init {
        detailMovie.value = Result.default()
    }

    fun getDetailMovie(id: String) {
        detailMovie.value = Result.loading()
        movieUseCase.getDetailMovie(id)
            .compose(singleScheduler())
            .subscribe({
                detailMovie.value = Result.success(it)
            }, {
                genericErrorHandler(it, detailMovie)
            }).addTo(disposable)
    }
}