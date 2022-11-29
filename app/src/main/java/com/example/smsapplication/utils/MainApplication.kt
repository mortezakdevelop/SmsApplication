package com.example.smsapplication.utils

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatDelegate

class MainApplication : Application() {

    init {
        instance = this
    }

    companion object {
       
        private var instance: MainApplication? = null

        fun applicationContext(): Context {
            return instance!!.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
//        MapboxSearchSdk.initialize(
//            application = this,
//            accessToken = getString(R.string.mapbox_access_token),
//            locationEngine = LocationEngineProvider.getBestLocationEngine(this)
//        )
    }
}