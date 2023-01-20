package com.annisaarss.movieapp.viewmodel

import androidx.lifecycle.MutableLiveData
import com.annisaarss.movieapp.base.BaseViewModel
import com.annisaarss.movieapp.domain.movie.MovieUseCase
import com.annisaarss.movieapp.domain.movie.model.PosterDetail
import com.nbs.nucleo.data.Result
import com.nbs.nucleo.utils.extensions.addTo
import com.nbs.nucleo.utils.rx.apihandler.genericErrorHandler
import com.nbs.nucleo.utils.rx.transformers.singleScheduler
import io.reactivex.disposables.CompositeDisposable

class HomeViewModel (
    private val movieUseCase: MovieUseCase,
    disposable: CompositeDisposable
) : BaseViewModel(disposable) {
    val listPosterPopular = MutableLiveData<Result<List<PosterDetail>>>()
    val listPosterCoomingSoon = MutableLiveData<Result<List<PosterDetail>>>()
    val listBanner = MutableLiveData<Result<List<String>>>()

    init {
        listPosterPopular.value = Result.default()
        listPosterCoomingSoon.value = Result.default()
        listBanner.value = Result.default()
    }

    fun getPosterPopular() {
        listPosterPopular.value = Result.loading()
        movieUseCase.getPosterPopularMovie()
            .compose(singleScheduler())
            .subscribe({
                listPosterPopular.value = Result.success(it)
            }, {
                genericErrorHandler(it, listPosterPopular)
            }).addTo(disposable)
    }

    fun getPosterCoomingSoon() {
        listPosterCoomingSoon.value = Result.loading()
        movieUseCase.getPosterCoomingSoonMovie()
            .compose(singleScheduler())
            .subscribe({
                listPosterCoomingSoon.value = Result.success(it)
            }, {
                genericErrorHandler(it, listPosterCoomingSoon)
            }).addTo(disposable)
    }

    fun getBanner(){
        listBanner.value = Result.loading()
        movieUseCase.getBannerPopular()
            .compose(singleScheduler())
            .subscribe({
                listBanner.value = Result.success(it)
            },{
                genericErrorHandler(it, listBanner)
            }).addTo(disposable)
    }
}