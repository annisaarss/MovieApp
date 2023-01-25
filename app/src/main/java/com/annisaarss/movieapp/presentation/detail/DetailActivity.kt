package com.annisaarss.movieapp.presentation.detail

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.annisaarss.movieapp.R
import com.annisaarss.movieapp.data.movie.model.response.DetailMovieItem
import com.annisaarss.movieapp.databinding.ActivityDetailBinding
import com.annisaarss.movieapp.domain.favorite.model.Favorite
import com.annisaarss.movieapp.domain.movie.model.DetailMovieDetail
import com.annisaarss.movieapp.presentation.detail.adapter.CastAdapter
import com.annisaarss.movieapp.presentation.detail.adapter.GenreAdapter
import com.annisaarss.movieapp.presentation.popular.adapter.PopularMoviesAdapter
import com.annisaarss.movieapp.utils.constants.BundleKeys
import com.annisaarss.movieapp.viewmodel.DetailViewModel
import com.annisaarss.movieapp.viewmodel.FavoriteViewModel
import com.annisaarss.movieapp.viewmodel.PopularViewModel
import com.nbs.nucleo.data.Result
import com.nbs.nucleo.utils.emptyString
import com.nbs.nucleo.utils.extensions.setImageUrl
import com.nbs.nucleo.utils.showToast
import com.nbs.nucleosnucleo.presentation.viewbinding.NucleoActivity
import com.nbs.utils.exts.getCompatColor
import com.nbs.utils.exts.isNotNull
import com.nbs.utils.exts.isNull
import com.nbs.utils.exts.onClick
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity : NucleoActivity<ActivityDetailBinding>() {

    companion object {
        fun start(context: Context, idItem: String?, titleMovie: String?) {
            val intent = Intent(context, DetailActivity::class.java).apply {
                putExtra(BundleKeys.EXTRA_ID, idItem)
                putExtra(BundleKeys.EXTRA_TITLE, titleMovie)
            }
            context.startActivity(intent)
        }
    }

    private val detailViewModel: DetailViewModel by viewModel()
    private val favoriteViewModel: FavoriteViewModel by viewModel()

    private val castAdapter: CastAdapter by lazy {
        CastAdapter(
            context = this@DetailActivity,
            items = mutableListOf()
        )
    }

    private val genreAdapter: GenreAdapter by lazy {
        GenreAdapter(
            context = this@DetailActivity,
            items = mutableListOf()
        )
    }

    private lateinit var idItem: String
    private lateinit var titleMovie: String
    private var idFavorite: Int? = null
    private lateinit var detailMovie: DetailMovieDetail
    private var _isChecked: Boolean = false

    override fun getViewBinding(): ActivityDetailBinding {
        return ActivityDetailBinding.inflate(layoutInflater)
    }

    override fun initIntent() {
        idItem = intent.getStringExtra(BundleKeys.EXTRA_ID) ?: emptyString()
        titleMovie = intent.getStringExtra(BundleKeys.EXTRA_TITLE) ?: emptyString()
    }

    override fun initUI() {

        binding.collapseToolbar.apply {
            title = titleMovie
            setCollapsedTitleTextColor(getCompatColor(R.color.white))
            setExpandedTitleColor(getCompatColor(R.color.transparant))
        }

        binding.rvCast.apply {
            adapter = castAdapter
            layoutManager = LinearLayoutManager(
                context,
                LinearLayoutManager.HORIZONTAL,
                false
            )
        }

        binding.rvGenreMovie.apply {
            adapter = genreAdapter
            layoutManager = LinearLayoutManager(
                context,
                LinearLayoutManager.HORIZONTAL,
                false
            )
        }
        binding.btnBack.onClick { onBackPressed() }
    }

    override fun initAction() {
        binding.btnFavorite.onClick {
            _isChecked = !_isChecked
            if (_isChecked) {
                favoriteViewModel.addFavorite(
                    id = 0,
                    idItem = detailMovie.id,
                    title = detailMovie.title,
                    image = detailMovie.image,
                    year = detailMovie.year,
                    genre = detailMovie.genre
                )
            } else {
                idFavorite?.let { it -> favoriteViewModel.removeFavorite(it) }
            }
            binding.btnFavorite.isChecked = _isChecked
        }
    }

    override fun initProcess() {
        detailViewModel.getDetailMovie(idItem)

        favoriteViewModel.checkFavorite(idItem)
    }

    override fun initObservers() {
        detailViewModel.detailMovie.observe(this, Observer {
            when (it) {
                is Result.Loading -> {
                    showLoading()
                }

                is Result.Success -> {
                    hideLoading()
                    setDetail(it.data)
                    detailMovie = it.data
                }

                is Result.Failure -> {
                    hideLoading()
                    showToast(it.message.toString())
                }
                else -> {}
            }
        })

        favoriteViewModel.checkFavorite.observe(this, Observer {
            when (it) {
                is Result.Loading -> {
                    showLoading()
                }
                is Result.Failure -> {
                    hideLoading()
                }
                is Result.Success -> {
                    hideLoading()
                    idFavorite = it.data.id
                    if (idFavorite.isNotNull()) {
                        binding.btnFavorite.isChecked = true
                        _isChecked = true
                    } else {
                        binding.btnFavorite.isChecked = false
                        _isChecked = false
                    }
                }
                else -> {}
            }
        })

        favoriteViewModel.addFavorite.observe(this, Observer {
            when (it) {
                is Result.Loading -> {
                    showLoading()
                }

                is Result.Success -> {
                    hideLoading()
                    showToast(getString(R.string.message_add_favorite))
                }

                is Result.Failure -> {
                    hideLoading()
                    showToast(it.message.toString())
                }
                else -> {}
            }
        })
    }

    private fun setDetail(data: DetailMovieDetail) {
        castAdapter.clear()
        castAdapter.addOrUpdate(data.actorList)

        genreAdapter.clear()
        genreAdapter.addOrUpdate(data.genreList)

        binding.apply {
            tvTitleMovie.text = data.title
            tvDurationMovie.text = data.duration
            tvDescriptionMovie.text = data.plot
            imgMovie.setImageUrl(this@DetailActivity, data.image)
        }
    }
}