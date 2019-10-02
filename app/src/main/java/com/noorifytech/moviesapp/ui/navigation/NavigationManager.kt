package com.noorifytech.moviesapp.ui.navigation

import android.app.Activity
import android.content.Intent
import com.noorifytech.moviesapp.ui.view.MovieDetailsView
import com.noorifytech.moviesapp.ui.view.activity.MovieDetailsActivity

object NavigationManager {

    fun navigateToMovieDetailsScreen(activity: Activity, movieId: Int) {
        val movieDetailsIntent = Intent(activity, MovieDetailsActivity::class.java)
        movieDetailsIntent.putExtra(MovieDetailsView.MOVIE_ID_KEY, movieId)
        activity.startActivity(movieDetailsIntent)
    }
}