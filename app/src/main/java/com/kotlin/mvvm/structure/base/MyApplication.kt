package com.kotlin.mvvm.structure.base

import android.app.Application
import com.kotlin.mvvm.structure.injection.component.AppComponent
import com.kotlin.mvvm.structure.injection.component.DaggerAppComponent
import com.kotlin.mvvm.structure.injection.modules.AppModule
import com.kotlin.mvvm.structure.injection.modules.NetworkModule

class MyApplication : Application() {

    companion object {
        lateinit var application: Application
        lateinit var appComponent: AppComponent
        var isInternetAvailable: Boolean = false
    }

    override fun onCreate() {
        super.onCreate()

        application = this

        appComponent = DaggerAppComponent.builder()
            .networkModule(NetworkModule)
            .appModule(AppModule(application))
            .build()

        appComponent.inject(this)

    }

}