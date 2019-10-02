package com.noorifytech.moviesapp.ui.presenter

import com.noorifytech.moviesapp.ui.base.BasePresenter
import com.noorifytech.moviesapp.ui.view.MoviesListView

interface MoviesListPresenter : BasePresenter<MoviesListView> {

    fun onAttach()
    fun onMovieSelected(movieId: Int)
}