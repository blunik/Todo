package com.example.todo

import android.app.Application
import androidx.viewbinding.BuildConfig
import timber.log.Timber

class TodoApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }
}