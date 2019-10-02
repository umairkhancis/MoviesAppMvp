package com.noorifytech.moviesapp.ui.view

import androidx.paging.PagedList
import com.noorifytech.moviesapp.data.repository.vo.MovieVO
import com.noorifytech.moviesapp.ui.base.BaseLoadableContentView

interface MoviesListView : BaseLoadableContentView {
    fun showList(list: PagedList<MovieVO>)
    fun showMovieDetailsScreen(movieId: Int)
}