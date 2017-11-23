package com.rhino.chronometer.app.di


import android.content.Context
import android.content.res.Resources
import com.rhino.chronometer.app.ChronometerApplication
import dagger.Module
import dagger.Provides

@Module
class AppModule(private val app: ChronometerApplication) {

    @Provides
    @AppScope
    fun provideApp(): ChronometerApplication = app

    @Provides
    @AppScope
    fun provideApplicationContext(): Context = app

    @Provides
    @AppScope
    fun provideResources(): Resources = app.resources

}
