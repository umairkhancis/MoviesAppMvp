package com.noorifytech.moviesapp.data.factory

import com.noorifytech.moviesapp.application.MoviesAppMvp
import com.noorifytech.moviesapp.common.MovieMapper
import com.noorifytech.moviesapp.data.dao.backend.impl.MoviesBackendDaoImpl
import com.noorifytech.moviesapp.data.dao.backend.impl.retrofit.RetrofitFactory
import com.noorifytech.moviesapp.data.dao.backend.impl.retrofit.TMDBApi
import com.noorifytech.moviesapp.data.dao.db.RoomDB
import com.noorifytech.moviesapp.data.repository.MoviesRepository
import com.noorifytech.moviesapp.data.repository.impl.MoviesRepositoryImpl


object MoviesRepositoryFactory {

    fun getMoviesRepository(): MoviesRepository {
        val db = RoomDB.getInstance(MoviesAppMvp.instance!!.applicationContext)

        val backendTMDBApi = RetrofitFactory.getDefaultRetrofit()
            .create(TMDBApi::class.java)

        val moviesBackendDao = MoviesBackendDaoImpl(backendTMDBApi)

        return MoviesRepositoryImpl(
            db.moviesDao(),
            moviesBackendDao,
            MovieMapper
        )
    }
}