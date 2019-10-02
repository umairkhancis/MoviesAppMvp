package com.noorifytech.moviesapp.ui.base

import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class RxBaseProcessor(
    private val backgroundSchedulers: Scheduler = Schedulers.io(),
    private val foregroundSchedulers: Scheduler = AndroidSchedulers.mainThread()
) {

    private val disposables = CompositeDisposable()

    fun <T> process(business: Observable<T>, callback: DisposableObserver<T>) {
        disposables.add(
            business
                .subscribeOn(backgroundSchedulers)
                .observeOn(foregroundSchedulers)
                .subscribeWith(callback)
        )
    }

    fun <T> process(business: Single<T>, callback: DisposableSingleObserver<T>) {
        disposables.add(
            business
                .subscribeOn(backgroundSchedulers)
                .observeOn(foregroundSchedulers)
                .subscribeWith(callback)
        )
    }

    fun clear() {
        disposables.clear()
    }
}