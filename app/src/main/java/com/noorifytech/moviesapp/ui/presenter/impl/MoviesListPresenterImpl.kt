package com.noorifytech.moviesapp.ui.presenter.impl

import androidx.paging.PagedList
import com.noorifytech.moviesapp.data.repository.vo.MovieVO
import com.noorifytech.moviesapp.ui.base.BasePresenterImpl
import com.noorifytech.moviesapp.ui.presenter.MoviesListPresenter
import com.noorifytech.moviesapp.ui.usecase.GetPopularMoviesUseCase
import com.noorifytech.moviesapp.ui.view.MoviesListView
import io.reactivex.observers.DisposableObserver
import timber.log.Timber

class MoviesListPresenterImpl(private val getPopularMoviesUseCase: GetPopularMoviesUseCase) :
    BasePresenterImpl<MoviesListView>(), MoviesListPresenter {

    override fun onAttach() {

        view.showLoading()
        val callback = object : DisposableObserver<PagedList<MovieVO>>() {
            override fun onNext(pagedList: PagedList<MovieVO>) {
                Timber.i("onNext: pagedList.size = ${pagedList.size}")

                view.hideLoading()
                view.showList(pagedList)
            }

            override fun onError(e: Throwable) {
                Timber.e("onError: ${e.message}")

                view.hideLoading()
                view.showError()
            }

            override fun onComplete() {
                Timber.i("onComplete")

                view.showError()
            }

        }

        this.baseProcessor.process(
            business = getPopularMoviesUseCase.getPopularMovies(),
            callback = callback
        )
    }

    override fun onMovieSelected(movieId: Int) {
        view.showMovieDetailsScreen(movieId)
    }
}
