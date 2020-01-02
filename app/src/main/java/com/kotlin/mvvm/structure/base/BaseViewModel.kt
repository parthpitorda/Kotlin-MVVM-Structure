package com.kotlin.mvvm.structure.base

import android.app.Application
import androidx.lifecycle.ViewModel
import com.kotlin.mvvm.structure.network.PostApi
import javax.inject.Inject

open class BaseViewModel : ViewModel() {

    @Inject
    lateinit var postApi: PostApi

    @Inject
    lateinit var context: Application

    init {
        inject()
    }

    /**
     * Injects the required dependencies
     */
    private fun inject() {
        MyApplication.appComponent.inject(this)
    }

}