package com.noorifytech.moviesapp.common

data class AppException(val code: Int, val msg: String) : Exception(msg)