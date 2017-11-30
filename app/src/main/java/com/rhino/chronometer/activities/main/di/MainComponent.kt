package com.rhino.chronometer.activities.main.di

import com.rhino.chronometer.app.di.AppComponent
import com.rhino.chronometer.activities.main.MainActivity
import dagger.Component

/**
 * Created by Alexander Fermin (alexfer06@gmail.com) on 11/21/17.
 */
@MainScope
@Component(dependencies = arrayOf(AppComponent::class), modules = arrayOf(MainModule::class))
interface MainComponent {
    fun inject(activity: MainActivity)
}