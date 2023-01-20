package com.annisaarss.movieapp.presentation.popular

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.annisaarss.movieapp.R
import com.annisaarss.movieapp.databinding.FragmentPopularBinding
import com.annisaarss.movieapp.domain.movie.model.MostPopularDetail
import com.annisaarss.movieapp.domain.movie.model.SearchDetail
import com.annisaarss.movieapp.presentation.detail.DetailActivity
import com.annisaarss.movieapp.presentation.popular.adapter.PopularMoviesAdapter
import com.annisaarss.movieapp.viewmodel.HomeViewModel
import com.annisaarss.movieapp.viewmodel.PopularViewModel
import com.nbs.nucleo.data.Result
import com.nbs.nucleo.utils.showToast
import com.nbs.nucleosnucleo.presentation.viewbinding.NucleoFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class PopularFragment : NucleoFragment<FragmentPopularBinding>() {

    private val popularViewModel: PopularViewModel by viewModel()

    private val popularMoviesAdapter: PopularMoviesAdapter by lazy {
        PopularMoviesAdapter(
            context = requireActivity(),
            items = mutableListOf(),
            onItemClicked = {
                OnItemMovieClicked(it)
            }
        )
    }

    override fun getViewBinding(
        layoutInflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean
    ): FragmentPopularBinding {
        return FragmentPopularBinding.inflate(layoutInflater, container, false)
    }

    override fun initIntent() {}

    override fun initUI() {
        binding.rvPopular.apply {
            adapter = popularMoviesAdapter
            layoutManager = GridLayoutManager(requireActivity(), 2)
        }
    }

    override fun initAction() {}

    override fun initProcess() {
        popularViewModel.getMostPopular()
    }

    override fun initObservers() {
        popularViewModel.listPopular.observe(this, Observer {
            when (it) {
                is Result.Loading -> {
                    showLoading()
                }

                is Result.Success -> {
                    hideLoading()
                    popularMoviesAdapter.clear()
                    popularMoviesAdapter.addOrUpdate(it.data)
                }

                is Result.Failure -> {
                    hideLoading()
                    showToast(it.message.toString())
                }
                else -> {}
            }
        })
    }

    private fun OnItemMovieClicked(data : MostPopularDetail){
        DetailActivity.start(requireActivity(), id = data.id, titleMovie = data.title)
    }
}