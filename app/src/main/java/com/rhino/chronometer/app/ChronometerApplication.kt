package com.rhino.chronometer.app

import android.app.Activity
import android.app.Application
import android.app.Service

import com.rhino.chronometer.app.di.AppComponent
import com.rhino.chronometer.app.di.AppModule
import com.rhino.chronometer.app.di.DaggerAppComponent

class ChronometerApplication : Application() {

    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder().build()

        appComponent.inject(this)
    }

    fun component(): AppComponent? = appComponent

    companion object {

        operator fun get(activity: Activity): ChronometerApplication =
                activity.application as ChronometerApplication

        operator fun get(service: Service): ChronometerApplication =
                service.application as ChronometerApplication
    }

}
