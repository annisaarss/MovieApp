package com.annisaarss.movieapp.domain.movie

import com.annisaarss.movieapp.data.movie.MovieRepository
import com.annisaarss.movieapp.domain.movie.model.*
import com.nbs.utils.exts.isNull
import io.reactivex.Single

class MovieInteractor(private val repository: MovieRepository) : MovieUseCase {
    override fun getMostPopular(): Single<List<MostPopularDetail>> {
        return repository.getMostPopular().map {
            it.items?.map { data ->
                data.mapToMostPopularDetail()
            }
        }
    }

    override fun getCoomingSoon(): Single<List<CoomingSoonDetail>> {
        return repository.getCoomingSoon().map {
            it.items?.map { data ->
                data.mapToCoomingSoonDetail()
            }
        }
    }

    override fun getDetailMovie(id: String): Single<DetailMovieDetail> {
        return repository.getDetailMovie(id).map {
            it.mapToDetailMovieDetail()
        }
    }

    override fun getPosterPopularMovie(): Single<List<PosterDetail>> {
        return repository.getMostPopular().map {
            it.items?.map { data ->
                data.mapToPosterDetail()
            }
        }
    }

    override fun getPosterCoomingSoonMovie(): Single<List<PosterDetail>> {
        return repository.getCoomingSoon().map {
            it.items?.map { data ->
                data.mapToPosterDetail()
            }
        }
    }

    override fun getBannerPopular(): Single<List<String>> {
        return repository.getMostPopular().map {
            it.items?.slice(1..5)?.map {
                it.mapToImage()
            }
        }
    }

    override fun searchMovie(expression: String): Single<List<MostPopularDetail>> {
        return repository.search(expression).map {
            it.results?.map { data ->
                data.mapToMostPopularDetail()
            }
        }
    }

}