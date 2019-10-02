package com.noorifytech.moviesapp.ui.base

interface BasePresenter<T : BaseView> {

    fun initView(view: T)

    fun onDetach()
}

abstract class BasePresenterImpl<T : BaseView> : BasePresenter<T> {

    lateinit var view: T

    val baseProcessor = RxBaseProcessor()

    override fun initView(view: T) {
        this.view = view
    }

    override fun onDetach() {
        baseProcessor.clear()
    }
}