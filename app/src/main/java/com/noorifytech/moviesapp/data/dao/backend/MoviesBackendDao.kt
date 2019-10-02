package com.noorifytech.moviesapp.data.dao.backend

import com.noorifytech.moviesapp.data.dao.backend.dto.ApiResponse
import com.noorifytech.moviesapp.data.dao.backend.dto.MoviesListResponse
import io.reactivex.Observable

interface MoviesBackendDao {
    fun getPopularMovies(page: Int = 1): Observable<ApiResponse<MoviesListResponse>>
}