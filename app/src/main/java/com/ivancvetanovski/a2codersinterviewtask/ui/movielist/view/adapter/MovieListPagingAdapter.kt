package com.ivancvetanovski.a2codersinterviewtask.ui.movielist.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ivancvetanovski.a2codersinterviewtask.databinding.ItemMovieListBinding
import com.ivancvetanovski.a2codersinterviewtask.ui.moviedetails.view.MovieDetailsActivity
import com.ivancvetanovski.a2codersinterviewtask.ui.movielist.model.MovieListItem

class MovieListPagingAdapter(private val context: Context) :
  PagingDataAdapter<MovieListItem, MovieListPagingAdapter.ViewHolder>(MovieListItemComparator) {

  override fun onCreateViewHolder(
    parent: ViewGroup,
    viewType: Int
  ): ViewHolder {
    val inflater = LayoutInflater.from(parent.context)
    val binding = ItemMovieListBinding.inflate(inflater, parent, false)
    return ViewHolder(binding)
  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    getItem(position)?.let { holder.bind(it) }
  }

  inner class ViewHolder(private val itemBinding: ItemMovieListBinding) :
    RecyclerView.ViewHolder(itemBinding.root) {
    fun bind(item: MovieListItem) {
      itemBinding.apply {

        with(ivMovieListItemPoster) {
          if (item.poster != null) {
            Glide.with(this.context)
              .load(item.poster)
              .thumbnail(0.5F)
              .into(this)
          }
        }

        tvMovieListItemName.text = item.name
        tvMovieListItemDescription.text = item.description
        crdMovieListItemContainer.setOnClickListener(cardContainerOnClickListener(item))
      }
    }
  }

  private fun cardContainerOnClickListener(movieListItem: MovieListItem) = View.OnClickListener {
    MovieDetailsActivity.startActivity(
      context,
      MovieDetailsActivity.MovieDetailsActivityConfig(
        movieId = movieListItem.id,
        movieTitle = movieListItem.name
      )
    )
  }
}

object MovieListItemComparator : DiffUtil.ItemCallback<MovieListItem>() {
  override fun areItemsTheSame(oldItem: MovieListItem, newItem: MovieListItem): Boolean =
    oldItem.id == newItem.id

  override fun areContentsTheSame(oldItem: MovieListItem, newItem: MovieListItem): Boolean =
    oldItem.description == newItem.description
      && oldItem.name == newItem.name
      && oldItem.poster == newItem.poster
}