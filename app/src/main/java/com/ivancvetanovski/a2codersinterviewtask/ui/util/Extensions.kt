package com.ivancvetanovski.a2codersinterviewtask.ui.util

import java.util.*

/**
 * Extracts a year as a [String] from a [Date]
 */
fun Date?.extractYear(): String? = this?.let {
  val calendar = Calendar.getInstance().apply { time = it }
  calendar.get(Calendar.YEAR).toString()
}

/**
 * Formats the url of the poster image
 */
fun String?.formatPosterUrl(): String? = this?.let {
  "https://image.tmdb.org/t/p/original/$this"
}