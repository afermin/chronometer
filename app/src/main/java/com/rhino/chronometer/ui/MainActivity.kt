package com.rhino.chronometer.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.rhino.chronometer.app.ChronometerApplication
import com.rhino.chronometer.ui.di.DaggerMainComponent
import com.rhino.chronometer.ui.di.MainModule
import com.rhino.chronometer.ui.mvp.MainContract
import javax.inject.Inject


class MainActivity : AppCompatActivity() {

    @Inject lateinit var view: MainContract.View
    @Inject lateinit var presenter: MainContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initComponent()
        setContentView(view.inflateLayout())
        presenter.onCreate()

    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    private fun initComponent() {
         DaggerMainComponent.builder()
                 .appComponent(ChronometerApplication[this].component())
                 .mainModule(MainModule(this))
                 .build()
                 .inject(this)
    }
}
