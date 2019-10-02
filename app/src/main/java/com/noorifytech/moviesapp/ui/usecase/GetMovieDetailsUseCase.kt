package com.noorifytech.moviesapp.ui.usecase

import com.noorifytech.moviesapp.data.repository.MoviesRepository
import com.noorifytech.moviesapp.data.repository.vo.MovieDetailVO
import io.reactivex.Observable

interface GetMovieDetailsUseCase {
    fun getMovieDetails(movieId: Int): Observable<MovieDetailVO>
}

class GetMovieDetailsUseCaseImpl(private val moviesRepository: MoviesRepository) :
    GetMovieDetailsUseCase {

    override fun getMovieDetails(movieId: Int): Observable<MovieDetailVO> =
        moviesRepository.getMovieDetails(movieId)
}