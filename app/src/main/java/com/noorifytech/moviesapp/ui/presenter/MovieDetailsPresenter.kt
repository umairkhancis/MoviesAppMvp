package com.noorifytech.moviesapp.ui.presenter

import com.noorifytech.moviesapp.ui.base.BasePresenter
import com.noorifytech.moviesapp.ui.view.MovieDetailsView

interface MovieDetailsPresenter : BasePresenter<MovieDetailsView> {

    fun onAttach(movieId: Int)
}