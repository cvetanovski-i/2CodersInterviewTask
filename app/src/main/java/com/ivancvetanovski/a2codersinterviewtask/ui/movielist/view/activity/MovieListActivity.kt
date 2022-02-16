package com.ivancvetanovski.a2codersinterviewtask.ui.movielist.view.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import com.ivancvetanovski.a2codersinterviewtask.databinding.ActivityMovieListBinding
import com.ivancvetanovski.a2codersinterviewtask.ui.base.view.BaseActivity
import com.ivancvetanovski.a2codersinterviewtask.ui.movielist.view.adapter.MovieListPagingAdapter
import com.ivancvetanovski.a2codersinterviewtask.ui.movielist.viewmodel.MovieListViewModel
import kotlinx.coroutines.launch

class MovieListActivity : BaseActivity<ActivityMovieListBinding>() {

  private val viewModel by viewModels<MovieListViewModel>()

  override fun inflateLayout(layoutInflater: LayoutInflater) =
    ActivityMovieListBinding.inflate(layoutInflater)

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setupAdapters()
    setupOnClickListeners()
    setupLiveDataObservers()
  }

  private fun setupAdapters() {
    setupMovieListAdapter()
  }

  private fun setupOnClickListeners() {
    binding.btnMovieListActivityRetry.setOnClickListener(retryOnClickListener())
  }

  private fun setupLiveDataObservers() {
    setupMoviesLiveDataObserver()
  }

  private fun setupMovieListAdapter() {
    binding.rvMovieListActivityList.adapter = MovieListPagingAdapter(this).apply {
      addLoadStateListener { combinedLoadStates: CombinedLoadStates ->
        if (combinedLoadStates.source.refresh is LoadState.Error) {
          if (itemCount < 1) {
            showEmptyState()
          } else {
            hideEmptyState()
          }
        }
      }
    }
  }

  private fun setupMoviesLiveDataObserver() {
    viewModel.getMovies().observe(this) { movies ->
      lifecycleScope.launch {
        (binding.rvMovieListActivityList.adapter as MovieListPagingAdapter).submitData(movies)
      }
    }
  }

  private fun showEmptyState() {
    binding.apply {
      linMovieListActivityEmptyState.visibility = View.VISIBLE
      rvMovieListActivityList.visibility = View.GONE
    }
  }

  private fun hideEmptyState() {
    binding.apply {
      linMovieListActivityEmptyState.visibility = View.GONE
      rvMovieListActivityList.visibility = View.VISIBLE
    }
  }

  private fun retryOnClickListener() = View.OnClickListener {
    (binding.rvMovieListActivityList.adapter as MovieListPagingAdapter).refresh()
  }
}