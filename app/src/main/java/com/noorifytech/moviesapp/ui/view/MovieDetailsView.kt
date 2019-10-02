package com.noorifytech.moviesapp.ui.view

import com.noorifytech.moviesapp.data.repository.vo.MovieDetailVO
import com.noorifytech.moviesapp.ui.base.BaseLoadableContentView

interface MovieDetailsView : BaseLoadableContentView {

    fun showMovieDetails(movie: MovieDetailVO)

    companion object {
        const val MOVIE_ID_KEY = "movie_id"
    }
}