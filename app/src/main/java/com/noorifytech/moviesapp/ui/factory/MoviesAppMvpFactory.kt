package com.noorifytech.moviesapp.ui.factory

import com.noorifytech.moviesapp.data.factory.MoviesRepositoryFactory
import com.noorifytech.moviesapp.ui.presenter.MovieDetailsPresenter
import com.noorifytech.moviesapp.ui.presenter.MoviesListPresenter
import com.noorifytech.moviesapp.ui.presenter.impl.MovieDetailsPresenterImpl
import com.noorifytech.moviesapp.ui.presenter.impl.MoviesListPresenterImpl
import com.noorifytech.moviesapp.ui.usecase.GetMovieDetailsUseCaseImpl
import com.noorifytech.moviesapp.ui.usecase.GetPopularMoviesUseCaseImpl


object MoviesAppMvpFactory {

    fun createMoviesListPresenter(): MoviesListPresenter {
        val getPopularMoviesUseCase =
            GetPopularMoviesUseCaseImpl(MoviesRepositoryFactory.getMoviesRepository())

        return MoviesListPresenterImpl(getPopularMoviesUseCase)
    }

    fun createMovieDetailsPresenter(): MovieDetailsPresenter {
        val getMovieDetailsUseCase =
            GetMovieDetailsUseCaseImpl(MoviesRepositoryFactory.getMoviesRepository())

        return MovieDetailsPresenterImpl(getMovieDetailsUseCase)
    }
}