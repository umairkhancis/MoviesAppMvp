package com.noorifytech.moviesapp.data.repository.impl

import androidx.paging.DataSource
import androidx.paging.PagedList
import androidx.paging.RxPagedListBuilder
import com.noorifytech.moviesapp.common.MovieMapper
import com.noorifytech.moviesapp.data.dao.backend.MoviesBackendDao
import com.noorifytech.moviesapp.data.dao.db.MoviesDBDao
import com.noorifytech.moviesapp.data.repository.MoviesRepository
import com.noorifytech.moviesapp.data.repository.vo.MovieDetailVO
import com.noorifytech.moviesapp.data.repository.vo.MovieVO
import io.reactivex.Observable

class MoviesRepositoryImpl(
    private val moviesDBDao: MoviesDBDao,
    private val moviesBackendDao: MoviesBackendDao,
    private val movieMapper: MovieMapper
) : MoviesRepository {

    override fun getPopularMovies(): Observable<PagedList<MovieVO>> {
        val config = PagedList.Config.Builder()
            .setPageSize(PAGE_SIZE)
            .setInitialLoadSizeHint(PAGE_SIZE)
            .setPrefetchDistance(PRE_FETCH_DISTANCE)
            .setEnablePlaceholders(false)
            .build()

        val boundaryCallback =
            PopularMoviesBoundaryCallback(moviesDBDao, moviesBackendDao, MovieMapper)

        val factory: DataSource.Factory<Int, MovieVO> = moviesDBDao.getPopularMovies()
            .map { movieMapper.toMovieVO(it) }

        val rxPagedListBuilder: RxPagedListBuilder<Int, MovieVO> =
            RxPagedListBuilder(factory, config)
                .setBoundaryCallback(boundaryCallback)

        return rxPagedListBuilder.buildObservable()
    }

    override fun getMovieDetails(movieId: Int): Observable<MovieDetailVO> =
        moviesDBDao.getMovieDetails(movieId)
            .toObservable()
            .onErrorResumeNext { _: Throwable ->
                moviesBackendDao.getMovieDetails(movieId)
                    .flatMap { Observable.just(movieMapper.toMovieDetailEntity(it)) }
                    .flatMap {
                        moviesDBDao.insert(it)
                        Observable.just(it)
                    }
            }.flatMap { Observable.just(movieMapper.toMovieDetailVO(it)) }

    companion object {
        const val PAGE_SIZE = 20
        const val PRE_FETCH_DISTANCE = 5
    }
}