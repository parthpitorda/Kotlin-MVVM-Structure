package com.kotlin.mvvm.structure.injection.component

import com.kotlin.mvvm.structure.base.BaseActivity
import com.kotlin.mvvm.structure.base.BaseViewModel
import com.kotlin.mvvm.structure.base.MyApplication
import com.kotlin.mvvm.structure.injection.modules.AppModule
import com.kotlin.mvvm.structure.injection.modules.NetworkModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, AppModule::class])
interface AppComponent {

    fun inject(myApplication: MyApplication)
    fun inject(baseActivity: BaseActivity)
    fun inject(baseViewModel: BaseViewModel)

    @Component.Builder
    interface Builder {
        fun build(): AppComponent

        fun networkModule(networkModule: NetworkModule): Builder

        fun appModule(appModule: AppModule): Builder
    }

}