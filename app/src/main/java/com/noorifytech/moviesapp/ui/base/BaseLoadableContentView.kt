package com.noorifytech.moviesapp.ui.base

interface BaseLoadableContentView : BaseView {
    fun showLoading()
    fun hideLoading()
    fun showNoContent()
    fun showNoConnection()
    fun showError()
}
