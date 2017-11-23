package com.rhino.chronometer.ui.di

import android.content.Context
import com.rhino.chronometer.app.di.AppScope
import com.rhino.chronometer.ui.MainActivity
import com.rhino.chronometer.ui.LapAdapter
import com.rhino.chronometer.ui.mvp.MainContract
import com.rhino.chronometer.ui.mvp.MainModel
import com.rhino.chronometer.ui.mvp.MainPresenter
import com.rhino.chronometer.ui.mvp.MainView
import dagger.Module
import dagger.Provides

/**
 * Created by alexanderjosefermingomez on 11/21/17.
 */
@Module
class MainModule(private val activity: MainActivity) {

    @Provides
    @MainScope
    fun provideActivity(): MainActivity = activity

    @Provides
    @MainScope
    fun provideModel(): MainContract.Model = MainModel(
            activity = activity
    )

    @Provides
    @MainScope
    fun provideView(activity: MainActivity,
                    adapter: LapAdapter):
            MainContract.View = MainView(activity = activity, adapter = adapter)

    @Provides
    @MainScope
    fun providePresenter(
            view: MainContract.View,
            model: MainContract.Model
    ): MainContract.Presenter = MainPresenter(view = view, model = model)

    @Provides
    @MainScope
    fun provideContext(): Context = activity

}
