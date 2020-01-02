package com.trust_tag.utils

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager

class AppUtils(var application: Application) {

    companion object {
        fun newInstance(application: Application): AppUtils {
            return AppUtils(application)
        }
    }

    fun printLog() {
        println("AppUtils Initialized and function Called")
    }

    fun Context.isConnectedToNetwork(): Boolean {
        val connectivityManager =
            this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        return connectivityManager?.activeNetworkInfo?.isConnectedOrConnecting ?: false
    }

}