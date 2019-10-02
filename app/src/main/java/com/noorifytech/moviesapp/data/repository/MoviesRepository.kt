package com.noorifytech.moviesapp.data.repository

import androidx.paging.PagedList
import com.noorifytech.moviesapp.data.repository.vo.MovieDetailVO
import com.noorifytech.moviesapp.data.repository.vo.MovieVO
import io.reactivex.Observable

interface MoviesRepository {
    fun getPopularMovies(): Observable<PagedList<MovieVO>>
    fun getMovieDetails(movieId: Int): Observable<MovieDetailVO>
}