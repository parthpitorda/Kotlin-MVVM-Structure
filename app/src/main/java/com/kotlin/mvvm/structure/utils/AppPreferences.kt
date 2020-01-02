package com.trust_tag.utils

import android.app.Application
import android.content.SharedPreferences

class AppPreferences(var application: Application) {

    companion object {
        fun newInstance(application: Application): AppPreferences {
            return AppPreferences(application)
        }
    }

    fun printLog() {
        println("AppPreferences Initialized and function Called")
    }

    fun writeSharedPreferencesString(key: String?, value: String?) {
        val settings: SharedPreferences =
            application.getSharedPreferences(AppConstants.PREFS_NAME, 0)
        val editor = settings.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun writeSharedPreferencesInt(key: String?, value: Int) {
        val settings: SharedPreferences =
            application.getSharedPreferences(AppConstants.PREFS_NAME, 0)
        val editor = settings.edit()
        editor.putInt(key, value)
        editor.apply()
    }

    fun writeSharedPreferencesBool(key: String?, value: Boolean?) {
        val settings: SharedPreferences =
            application.getSharedPreferences(AppConstants.PREFS_NAME, 0)
        val editor = settings.edit()
        editor.putBoolean(key, value!!)
        editor.apply()
    }

    fun clearAllPrefData() {
        val settings: SharedPreferences =
            application.getSharedPreferences(AppConstants.PREFS_NAME, 0)
        settings.edit().clear().apply()
    }

    fun getAppPrefString(key: String?): String? {
        return try {
            val settings: SharedPreferences =
                application.getSharedPreferences(AppConstants.PREFS_NAME, 0)
            settings.getString(key, "")
        } catch (ex: Exception) {
            ex.printStackTrace()
            ""
        }
    }

}