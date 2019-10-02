package com.noorifytech.moviesapp.ui.view.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.noorifytech.moviesapp.R
import com.noorifytech.moviesapp.data.repository.vo.MovieDetailVO
import com.noorifytech.moviesapp.ui.factory.MoviesAppMvpFactory
import com.noorifytech.moviesapp.ui.presenter.MovieDetailsPresenter
import com.noorifytech.moviesapp.ui.view.MovieDetailsView
import com.noorifytech.moviesapp.ui.view.MovieDetailsView.Companion.MOVIE_ID_KEY
import kotlinx.android.synthetic.main.activity_movie_details.*

class MovieDetailsActivity : AppCompatActivity(), MovieDetailsView {

    private lateinit var presenter: MovieDetailsPresenter
    private val movieId: Int by lazy { intent.getIntExtra(MOVIE_ID_KEY, 0) }

//    =======================  Android Activity Callback Methods =======================

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_movie_details)

        init()
    }

    override fun onDestroy() {
        super.onDestroy()

        presenter.onDetach()
    }

//    =======================  MovieDetailsView Implementation =======================

    override fun showMovieDetails(movie: MovieDetailVO) {
        movieNameTV.text = movie.title
        movieOverviewTV.text = movie.overview
        movieReleaseDateTV.text = movie.getReleaseDate()

        Glide.with(this)
            .asBitmap()
            .load(movie.imageUrl)
            .placeholder(R.mipmap.ic_launcher)
            .into(movieImageIV)
    }

    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBar.visibility = View.GONE
    }

    override fun showNoContent() {
        Snackbar.make(
            movie_details_root,
            R.string.error_no_content_message,
            Snackbar.LENGTH_LONG
        ).show()
    }

    override fun showNoConnection() {
        Snackbar.make(
            movie_details_root,
            R.string.no_internet,
            Snackbar.LENGTH_LONG
        ).show()
    }

    override fun showError() {
        Snackbar.make(
            movie_details_root,
            R.string.error_generic_message,
            Snackbar.LENGTH_LONG
        ).show()
    }

//    ==============================  Private Methods ==============================

    private fun init() {
        // Prepare presenter to take up the control
        presenter = MoviesAppMvpFactory.createMovieDetailsPresenter()
        presenter.initView(this)
        presenter.onAttach(movieId)
    }
}