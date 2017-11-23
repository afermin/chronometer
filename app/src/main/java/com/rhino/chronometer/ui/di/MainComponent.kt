package com.rhino.chronometer.ui.di

import com.rhino.chronometer.app.di.AppComponent
import com.rhino.chronometer.ui.MainActivity
import dagger.Component

/**
 * Created by alexanderjosefermingomez on 11/21/17.
 */
@MainScope
@Component(dependencies = arrayOf(AppComponent::class), modules = arrayOf(MainModule::class))
interface MainComponent {
    fun inject(activity: MainActivity)
}