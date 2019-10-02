package com.noorifytech.moviesapp.data.repository.impl

import androidx.paging.PagedList
import com.noorifytech.moviesapp.common.MovieMapper
import com.noorifytech.moviesapp.data.dao.backend.MoviesBackendDao
import com.noorifytech.moviesapp.data.dao.backend.dto.ApiResponse
import com.noorifytech.moviesapp.data.dao.backend.dto.ApiSuccessResponse
import com.noorifytech.moviesapp.data.dao.backend.dto.MoviesListResponse
import com.noorifytech.moviesapp.data.dao.db.MoviesDBDao
import com.noorifytech.moviesapp.data.repository.vo.MovieVO
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class PopularMoviesBoundaryCallback(
    private val moviesDBDao: MoviesDBDao,
    private val moviesApiDao: MoviesBackendDao,
    private val movieMapper: MovieMapper
) : PagedList.BoundaryCallback<MovieVO>() {

    @Volatile
    private var isInProgress: Boolean = false

    override fun onZeroItemsLoaded() {
        queryAndSave()
    }

    override fun onItemAtEndLoaded(itemAtEnd: MovieVO) {
        queryAndSave(itemAtEnd)
    }

    private fun queryAndSave(itemAtEnd: MovieVO? = null) {
        val nextPage = itemAtEnd?.page?.plus(1) ?: 1

        if (isInProgress) return

        isInProgress = true

        moviesApiDao.getPopularMovies(nextPage)
            .subscribeOn(Schedulers.io())
            .subscribe(object : Observer<ApiResponse<MoviesListResponse>> {

                override fun onNext(response: ApiResponse<MoviesListResponse>) {
                    Timber.i("onNext")

                    if (response is ApiSuccessResponse) {
                        val movieEntities = movieMapper.toMovies(response.body)
                        moviesDBDao.insert(movieEntities)
                    } else {
                        // TODO set observable for the view
                    }
                }

                override fun onError(e: Throwable) {
                    Timber.e("onError")
                    // TODO set observable for the view
                }

                override fun onComplete() {
                    Timber.i("onComplete")
                }

                override fun onSubscribe(d: Disposable) {
                    Timber.i("onSubscribe")
                }
            })

        isInProgress = false
    }
}