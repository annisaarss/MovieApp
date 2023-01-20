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
import com.annisaarss.movieapp.domain.movie.model.DetailMovieDetail
import com.annisaarss.movieapp.presentation.detail.adapter.CastAdapter
import com.annisaarss.movieapp.presentation.detail.adapter.GenreAdapter
import com.annisaarss.movieapp.presentation.popular.adapter.PopularMoviesAdapter
import com.annisaarss.movieapp.utils.constants.BundleKeys
import com.annisaarss.movieapp.viewmodel.DetailViewModel
import com.annisaarss.movieapp.viewmodel.PopularViewModel
import com.nbs.nucleo.data.Result
import com.nbs.nucleo.utils.emptyString
import com.nbs.nucleo.utils.extensions.setImageUrl
import com.nbs.nucleo.utils.showToast
import com.nbs.nucleosnucleo.presentation.viewbinding.NucleoActivity
import com.nbs.utils.exts.getCompatColor
import com.nbs.utils.exts.onClick
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity : NucleoActivity<ActivityDetailBinding>() {

    companion object {
        fun start(context: Context, id: String?, titleMovie: String?) {
            val intent = Intent(context, DetailActivity::class.java).apply{
                putExtra(BundleKeys.EXTRA_ID, id)
                putExtra(BundleKeys.EXTRA_TITLE, titleMovie)
            }
            context.startActivity(intent)
        }
    }

    private val detailViewModel: DetailViewModel by viewModel()

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

    private lateinit var id: String
    private lateinit var titleMovie: String

    override fun getViewBinding(): ActivityDetailBinding {
        return ActivityDetailBinding.inflate(layoutInflater)
    }

    override fun initIntent() {
        id = intent.getStringExtra(BundleKeys.EXTRA_ID) ?: emptyString()
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

//        binding.btnFavorite.onClick { changeButton() }
    }

    override fun initAction() {}

    override fun initProcess() {
        detailViewModel.getDetailMovie(id)
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
                }

                is Result.Failure -> {
                    hideLoading()
                    showToast(it.message.toString())
                }
                else -> {}
            }
        })
    }

    private fun setDetail(data: DetailMovieDetail){
        castAdapter.clear()
        castAdapter.addOrUpdate(data.actorList)

        genreAdapter.clear()
        genreAdapter.addOrUpdate(data.genreList)

        binding.apply{
            tvTitleMovie.text = data.title
            tvDurationMovie.text = data.duration
            tvDescriptionMovie.text = data.plot
            imgMovie.setImageUrl(this@DetailActivity, data.image)
        }
    }

    private fun changeButton(){
        binding.btnFavorite.apply {
            setText("Unfavorite")
            setIconResource(R.drawable.ic_delete)
            setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.white_12, null))
        }
    }
}