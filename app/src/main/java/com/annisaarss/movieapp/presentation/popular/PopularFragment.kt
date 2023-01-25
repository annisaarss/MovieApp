package com.annisaarss.movieapp.presentation.popular

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.annisaarss.movieapp.R
import com.annisaarss.movieapp.databinding.FragmentPopularBinding
import com.annisaarss.movieapp.domain.movie.model.MostPopularDetail
import com.annisaarss.movieapp.presentation.detail.DetailActivity
import com.annisaarss.movieapp.presentation.popular.adapter.PopularMoviesAdapter
import com.annisaarss.movieapp.viewmodel.HomeViewModel
import com.annisaarss.movieapp.viewmodel.PopularViewModel
import com.annisaarss.movieapp.viewmodel.SearchViewModel
import com.nbs.nucleo.data.Result
import com.nbs.nucleo.utils.showToast
import com.nbs.nucleosnucleo.presentation.viewbinding.NucleoFragment
import com.nbs.utils.exts.isVisible
import org.koin.androidx.viewmodel.ext.android.viewModel

class PopularFragment : NucleoFragment<FragmentPopularBinding>() {

    private val popularViewModel: PopularViewModel by viewModel()

    private val searchViewModel: SearchViewModel by viewModel()

    private val popularMoviesAdapter: PopularMoviesAdapter by lazy {
        PopularMoviesAdapter(
            context = requireActivity(),
            items = mutableListOf(),
            onItemClicked = {
                OnItemMovieClicked(it)
            }
        )
    }

    private lateinit var searchItem: String

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

    override fun initAction() {
        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                searchItem = s.toString()
                searchViewModel.getResultSearch(searchItem)
            }
        })
    }

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

        searchViewModel.resultSearch.observe(this, Observer {
            when (it) {
                is Result.Loading -> {
                    showLoading()
                }

                is Result.Success -> {
                    hideLoading()
                    popularMoviesAdapter.clear()
                    popularMoviesAdapter.addOrUpdate(it.data)
                    binding.tvExpression.text = searchItem
                    binding.containerTitlePopular.visibility = View.VISIBLE
                }

                is Result.Failure -> {
                    hideLoading()
                    showToast(it.message.toString())
                }
                else -> {}
            }
        })
    }

    private fun OnItemMovieClicked(data: MostPopularDetail) {
        DetailActivity.start(requireActivity(), idItem = data.id, titleMovie = data.title)
    }
}