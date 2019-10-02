package com.noorifytech.moviesapp.ui.usecase

import androidx.paging.PagedList
import com.noorifytech.moviesapp.data.repository.MoviesRepository
import com.noorifytech.moviesapp.data.repository.vo.MovieVO
import io.reactivex.Observable

interface GetPopularMoviesUseCase {
    fun getPopularMovies(): Observable<PagedList<MovieVO>>
}

class GetPopularMoviesUseCaseImpl(private val moviesRepository: MoviesRepository) :
    GetPopularMoviesUseCase {

    override fun getPopularMovies(): Observable<PagedList<MovieVO>> =
        moviesRepository.getPopularMovies()
}