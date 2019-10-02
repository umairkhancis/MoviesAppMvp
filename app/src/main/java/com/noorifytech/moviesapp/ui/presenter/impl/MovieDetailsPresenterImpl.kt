package com.noorifytech.moviesapp.ui.presenter.impl

import com.noorifytech.moviesapp.data.repository.vo.MovieDetailVO
import com.noorifytech.moviesapp.ui.base.BasePresenterImpl
import com.noorifytech.moviesapp.ui.presenter.MovieDetailsPresenter
import com.noorifytech.moviesapp.ui.usecase.GetMovieDetailsUseCase
import com.noorifytech.moviesapp.ui.view.MovieDetailsView
import io.reactivex.observers.DisposableObserver
import timber.log.Timber

class MovieDetailsPresenterImpl(private val getMovieDetailsUseCase: GetMovieDetailsUseCase) :
    BasePresenterImpl<MovieDetailsView>(), MovieDetailsPresenter {

    override fun onAttach(movieId: Int) {

        view.showLoading()
        val callback = object : DisposableObserver<MovieDetailVO>() {
            override fun onNext(movie: MovieDetailVO) {
                Timber.i("onNext: = $movie")

                view.hideLoading()
                view.showMovieDetails(movie)
            }

            override fun onError(e: Throwable) {
                Timber.e("onError: ${e.message}")

                view.hideLoading()
                view.showError()
            }

            override fun onComplete() {
                Timber.i("onComplete")
            }

        }

        this.baseProcessor.process(
            business = getMovieDetailsUseCase.getMovieDetails(movieId),
            callback = callback
        )
    }
}
