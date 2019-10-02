package com.noorifytech.moviesapp.data.dao.backend.impl

import com.noorifytech.moviesapp.data.dao.backend.MoviesBackendDao
import com.noorifytech.moviesapp.data.dao.backend.dto.ApiResponse
import com.noorifytech.moviesapp.data.dao.backend.dto.MoviesListResponse
import com.noorifytech.moviesapp.data.dao.backend.impl.retrofit.TMDBApi
import io.reactivex.Observable

class MoviesBackendDaoImpl(private val api: TMDBApi) : MoviesBackendDao {

    override fun getPopularMovies(page: Int): Observable<ApiResponse<MoviesListResponse>> =
        api.getPopularMovies(page = "$page").map { ApiResponse.create(it) }
}