package com.rhino.chronometer.app.di

import com.rhino.chronometer.app.ChronometerApplication
import dagger.Component

@AppScope
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {
    fun inject(app: ChronometerApplication)
}
