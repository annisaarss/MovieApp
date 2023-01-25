package com.annisaarss.movieapp.presentation.favorite

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.annisaarss.movieapp.R
import com.annisaarss.movieapp.databinding.FragmentFavoriteBinding
import com.annisaarss.movieapp.domain.favorite.model.Favorite
import com.annisaarss.movieapp.presentation.favorite.adapter.FavoriteAdapter
import com.annisaarss.movieapp.viewmodel.FavoriteViewModel
import com.nbs.nucleo.data.Result
import com.nbs.nucleo.utils.showToast
import com.nbs.nucleosnucleo.presentation.viewbinding.NucleoFragment
import com.nbs.utils.exts.gone
import com.nbs.utils.exts.visible
import okhttp3.internal.notify
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteFragment : NucleoFragment<FragmentFavoriteBinding>() {

    private val favoriteViewModel: FavoriteViewModel by viewModel()

    private val favoriteAdapter: FavoriteAdapter by lazy {
        FavoriteAdapter(
            context = requireActivity(),
            items = mutableListOf(),
            onItemClicked = {
                deleteFavorite(it)
            }
        )
    }

    override fun getViewBinding(
        layoutInflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean
    ): FragmentFavoriteBinding {
        return FragmentFavoriteBinding.inflate(layoutInflater, container, false)
    }

    override fun initIntent() {}

    override fun initUI() {
        binding.rvFavorite.apply {
            adapter = favoriteAdapter
            layoutManager = LinearLayoutManager(
                context,
                LinearLayoutManager.VERTICAL,
                false
            )
        }
    }

    override fun initAction() {
        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                favoriteViewModel.searchFavorite(s.toString())
            }
        })
    }

    override fun initProcess() {
        favoriteViewModel.getFavorites()
    }

    override fun initObservers() {
        favoriteViewModel.getFavorites.observe(this, Observer {
            when (it) {
                is Result.Empty -> {
                    binding.tvNoData.visible()
                    binding.rvFavorite.gone()
                }
                is Result.Loading -> {
                    showLoading()
                }

                is Result.Success -> {
                    hideLoading()
                    binding.tvNoData.gone()
                    binding.rvFavorite.visible()
                    favoriteAdapter.clear()
                    favoriteAdapter.addOrUpdate(it.data)
                }

                is Result.Failure -> {
                    hideLoading()
                    showToast(it.message.toString())
                }
                else -> {}
            }
        })

        favoriteViewModel.removeFavorite.observe(this, Observer {
            when (it) {
                is Result.Loading -> {
                    showLoading()
                }

                is Result.Success -> {
                    hideLoading()
                    showToast(getString(R.string.message_delete))
                    favoriteViewModel.getFavorites()
                }

                is Result.Failure -> {
                    hideLoading()
                    showToast(it.message.toString())
                }
                else -> {}
            }
        })

        favoriteViewModel.searchFavorite.observe(this, Observer {
            when (it) {
                is Result.Empty -> {
                    binding.tvNoData.visible()
                    binding.rvFavorite.gone()
                }
                is Result.Loading -> {
                    showLoading()
                }

                is Result.Success -> {
                    hideLoading()
                    binding.tvNoData.gone()
                    binding.rvFavorite.visible()
                    favoriteAdapter.clear()
                    favoriteAdapter.addOrUpdate(it.data)
                }

                is Result.Failure -> {
                    hideLoading()
                    showToast(it.message.toString())
                }
                else -> {}
            }
        })
    }

//    private fun searchFavorite(text: String) {
//        val filteredlist: ArrayList<Favorite> = ArrayList()
//
//        for (item in listFavorite) {
//            if (item.title.lowercase().contains(text.lowercase())) {
//                filteredlist.add(item)
//            }
//        }
//        if (filteredlist.isEmpty()) {
//            favoriteAdapter.clear()
//            binding.tvNoData.isVisible = true
//        } else {
//            favoriteAdapter.clear()
//            favoriteAdapter.addOrUpdate(filteredlist)
//        }
//    }

    private fun deleteFavorite(data: Favorite) {
        favoriteViewModel.removeFavorite(data.id)
    }

    private fun reload() {
        val currentFragment =
            activity?.supportFragmentManager?.findFragmentByTag(FavoriteFragment::class.java.simpleName)
        val fragmentTransaction: FragmentTransaction = requireFragmentManager().beginTransaction()
        currentFragment?.let { fragmentTransaction.detach(it) }
        currentFragment?.let { fragmentTransaction.attach(it) }
        fragmentTransaction.commit()
    }
}