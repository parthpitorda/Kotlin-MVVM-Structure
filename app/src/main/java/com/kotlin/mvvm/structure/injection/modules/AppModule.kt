package com.kotlin.mvvm.structure.injection.modules

import android.app.Application
import com.trust_tag.utils.AppPreferences
import com.trust_tag.utils.AppUtils
import dagger.Module
import dagger.Provides
import dagger.Reusable

@Module
class AppModule(private val application: Application) {

    @Provides
    @Reusable
    internal fun providerApplication(): Application {
        return application
    }

    @Provides
    @Reusable
    internal fun providerUtils(): AppUtils {
        return AppUtils.newInstance(application)
    }

    @Provides
    @Reusable
    internal fun providerPreferences(): AppPreferences {
        return AppPreferences.newInstance(application)
    }

}