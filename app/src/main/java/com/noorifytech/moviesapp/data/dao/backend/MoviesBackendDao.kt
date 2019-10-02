package com.noorifytech.moviesapp.data.dao.backend

import com.noorifytech.moviesapp.data.dao.backend.dto.MovieDto
import com.noorifytech.moviesapp.data.dao.backend.dto.MoviesListResponse
import io.reactivex.Observable

interface MoviesBackendDao {
    fun getPopularMovies(page: Int = 1): Observable<MoviesListResponse>
    fun getMovieDetails(movieId: Int): Observable<MovieDto>
}