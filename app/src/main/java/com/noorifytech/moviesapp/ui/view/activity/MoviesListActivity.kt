package com.noorifytech.moviesapp.ui.view.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.paging.PagedList
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.noorifytech.moviesapp.R
import com.noorifytech.moviesapp.data.repository.vo.MovieVO
import com.noorifytech.moviesapp.ui.factory.MoviesAppMvpFactory
import com.noorifytech.moviesapp.ui.navigation.NavigationManager
import com.noorifytech.moviesapp.ui.presenter.MoviesListPresenter
import com.noorifytech.moviesapp.ui.view.MoviesListView
import com.noorifytech.moviesapp.ui.view.adapter.MoviesPagedListAdapter
import com.noorifytech.moviesapp.ui.view.callback.MoviesListCellCallback
import kotlinx.android.synthetic.main.activity_movies_list.*

class MoviesListActivity : AppCompatActivity(), MoviesListView, MoviesListCellCallback {

    private lateinit var moviesListAdapter: MoviesPagedListAdapter
    private lateinit var presenter: MoviesListPresenter

//    =======================  Android Activity Callback Methods =======================

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_movies_list)

        init()
    }

    override fun onDestroy() {
        super.onDestroy()

        presenter.onDetach()
    }

//    =======================  MoviesListView Implementation =======================

    override fun showList(list: PagedList<MovieVO>) {
        moviesListAdapter.submitList(list)
    }

    override fun showMovieDetailsScreen(movieId: Int) {
        NavigationManager.navigateToMovieDetailsScreen(this, movieId)
    }

    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBar.visibility = View.GONE
    }

    override fun showNoContent() {
        Snackbar.make(
            movies_list_root,
            R.string.error_no_content_message,
            Snackbar.LENGTH_LONG
        ).show()
    }

    override fun showNoConnection() {
        Snackbar.make(
            movies_list_root,
            R.string.no_internet,
            Snackbar.LENGTH_LONG
        ).show()
    }

    override fun showError() {
        Snackbar.make(
            movies_list_root,
            R.string.error_generic_message,
            Snackbar.LENGTH_LONG
        ).show()
    }

//    ============================== MoviesListCell Interaction Methods ==============================

    override fun onMovieSelected(movieId: Int, position: Int) {
        presenter.onMovieSelected(movieId)
    }

//    ==============================  Private Methods ==============================

    private fun init() {
        // Prepare presenter to take up the control
        presenter = MoviesAppMvpFactory.createMoviesListPresenter()
        presenter.initView(this)
        presenter.onAttach()

        // initialize view
        initRecyclerView()
    }

    private fun initRecyclerView() {
        moviesListRV.layoutManager = LinearLayoutManager(this)
        moviesListRV.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        moviesListAdapter = MoviesPagedListAdapter(this, this)
        moviesListRV.adapter = moviesListAdapter
    }
}