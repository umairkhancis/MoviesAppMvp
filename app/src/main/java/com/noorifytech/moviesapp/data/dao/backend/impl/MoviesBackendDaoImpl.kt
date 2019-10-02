package com.noorifytech.moviesapp.data.dao.backend.impl

import com.noorifytech.moviesapp.common.AppException
import com.noorifytech.moviesapp.common.ErrorCodes
import com.noorifytech.moviesapp.data.dao.backend.MoviesBackendDao
import com.noorifytech.moviesapp.data.dao.backend.dto.*
import com.noorifytech.moviesapp.data.dao.backend.impl.retrofit.TMDBApi
import io.reactivex.Observable

class MoviesBackendDaoImpl(private val api: TMDBApi) : MoviesBackendDao {

    override fun getPopularMovies(page: Int): Observable<MoviesListResponse> =
        api.getPopularMovies(page = "$page")
            .map { ApiResponse.create(it) }
            .flatMap { apiResponse: ApiResponse<MoviesListResponse> ->
                when (apiResponse) {
                    is ApiSuccessResponse -> Observable.just(apiResponse.body)
                    is ApiErrorResponse -> Observable.error(
                        AppException(
                            ErrorCodes.BACKEND_ERROR,
                            apiResponse.errorMessage
                        )
                    )
                    else -> Observable.error(
                        AppException(
                            ErrorCodes.GENERIC_ERROR,
                            "generic error"
                        )
                    )
                }
            }

    override fun getMovieDetails(movieId: Int): Observable<MovieDto> =
        api.getMovieDetails(movieId = movieId)
            .map { ApiResponse.create(it) }
            .flatMap { apiResponse: ApiResponse<MovieDto> ->
                when (apiResponse) {
                    is ApiSuccessResponse -> Observable.just(apiResponse.body)
                    is ApiErrorResponse -> Observable.error(
                        AppException(
                            ErrorCodes.BACKEND_ERROR,
                            apiResponse.errorMessage
                        )
                    )
                    else -> Observable.error(
                        AppException(
                            ErrorCodes.GENERIC_ERROR,
                            "generic error"
                        )
                    )
                }
            }
}