package com.ivancvetanovski.a2codersinterviewtask.ui.moviedetails.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.ivancvetanovski.a2codersinterviewtask.R
import com.ivancvetanovski.a2codersinterviewtask.databinding.ActivityMovieDetailsBinding
import com.ivancvetanovski.a2codersinterviewtask.ui.base.view.BaseActivity
import com.ivancvetanovski.a2codersinterviewtask.ui.moviedetails.model.MovieDetails
import com.ivancvetanovski.a2codersinterviewtask.ui.moviedetails.viewmodel.MovieDetailsViewModel
import com.ivancvetanovski.a2codersinterviewtask.ui.moviedetails.viewmodel.MovieDetailsViewModelFactory
import kotlinx.parcelize.Parcelize

class MovieDetailsActivity : BaseActivity<ActivityMovieDetailsBinding>() {

  private lateinit var viewModel: MovieDetailsViewModel

  companion object {
    private const val MOVIE_DETAILS_ACTIVITY_CONFIG_KEY = "MOVIE_DETAILS_ACTIVITY_CONFIG_KEY"

    fun startActivity(
      context: Context,
      config: MovieDetailsActivityConfig
    ) {
      val intent = Intent(context, MovieDetailsActivity::class.java).apply {
        putExtra(MOVIE_DETAILS_ACTIVITY_CONFIG_KEY, config)
      }

      context.startActivity(intent)
    }
  }

  override fun inflateLayout(layoutInflater: LayoutInflater) =
    ActivityMovieDetailsBinding.inflate(layoutInflater)

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    val config: MovieDetailsActivityConfig = checkNotNull(
      intent.getParcelableExtra(MOVIE_DETAILS_ACTIVITY_CONFIG_KEY)
    ) { "MovieDetailsAcitivity input parameter is missing" }

    viewModel =
      ViewModelProvider(
        this,
        MovieDetailsViewModelFactory(config.movieId)
      )[MovieDetailsViewModel::class.java]

    setupToolbar(config.movieTitle)
    setupLiveDataObservers()
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    when (item.itemId) {
      android.R.id.home -> {
        onBackPressed()
        return true
      }
    }
    return super.onOptionsItemSelected(item)
  }

  private fun setupToolbar(movieTitle: String) {
    supportActionBar?.apply {
      title = movieTitle
      setDisplayHomeAsUpEnabled(true)
    }
  }

  private fun setupLiveDataObservers() {
    setupIsLoadingInProgressLiveData()
    setupMovieDetailsLiveData()
  }

  private fun setupIsLoadingInProgressLiveData() {
    viewModel.getIsLoadingInProgress().observe(this) { isLoading ->
      if (isLoading) {
        showLoading()
      } else {
        hideLoading()
      }
    }
  }

  private fun setupMovieDetailsLiveData() {
    viewModel.getMovieDetails().observe(this) { movieDetails ->
      populateView(movieDetails)
    }
  }

  private fun populateView(movieDetails: MovieDetails?) {
    binding.apply {
      with(ivMovieDetailsActivityBackdrop) {
        if (movieDetails?.backdrop != null) {
          Glide.with(this.context)
            .load(movieDetails.backdrop)
            .thumbnail(0.5F)
            .into(this)
        }
      }

      with(ivMovieDetailsItemPoster) {
        if (movieDetails?.poster != null) {
          Glide.with(this.context)
            .load(movieDetails.poster)
            .thumbnail(0.5F)
            .into(this)
        }
      }

      tvMovieDetailsActivityRating.text = getString(R.string.movie_rating, movieDetails?.rating)
      tvMovieDetailsActivityReleaseYear.text = movieDetails?.releaseYear
      tvMovieDetailsActivityDescription.text = movieDetails?.description
    }
  }

  private fun showLoading() {
    binding.apply {
      ivMovieDetailsActivityBackdrop.visibility = View.GONE
      crdMovieDetailsItemPosterCard.visibility = View.GONE
      tvMovieDetailsActivityRating.visibility = View.GONE
      tvMovieDetailsActivityReleaseYear.visibility = View.GONE
      tvMovieDetailsActivityDescription.visibility = View.GONE
      pbMovieDetailsActivityProgressBar.visibility = View.VISIBLE
    }
  }

  private fun hideLoading() {
    binding.apply {
      ivMovieDetailsActivityBackdrop.visibility = View.VISIBLE
      crdMovieDetailsItemPosterCard.visibility = View.VISIBLE
      tvMovieDetailsActivityRating.visibility = View.VISIBLE
      tvMovieDetailsActivityReleaseYear.visibility = View.VISIBLE
      tvMovieDetailsActivityDescription.visibility = View.VISIBLE
      pbMovieDetailsActivityProgressBar.visibility = View.GONE
    }
  }

  @Parcelize
  data class MovieDetailsActivityConfig(
    val movieId: Int,
    val movieTitle: String
  ) : Parcelable
}