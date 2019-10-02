package com.noorifytech.moviesapp.ui.factory

import com.noorifytech.moviesapp.data.factory.MoviesRepositoryFactory
import com.noorifytech.moviesapp.ui.presenter.MoviesListPresenter
import com.noorifytech.moviesapp.ui.presenter.impl.MoviesListPresenterImpl
import com.noorifytech.moviesapp.ui.usecase.GetPopularMoviesUseCaseImpl


object MoviesListFactory {

    fun createMoviesListPresenter(): MoviesListPresenter {
        val getPopularMoviesUseCase =
            GetPopularMoviesUseCaseImpl(MoviesRepositoryFactory.getMoviesRepository())

        return MoviesListPresenterImpl(getPopularMoviesUseCase)
    }
}