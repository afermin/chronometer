package com.rhino.chronometer.ui.mvp

import android.graphics.drawable.ShapeDrawable
import android.view.ViewGroup
import io.reactivex.Flowable
import io.reactivex.Observable

/**
 * Created by alexanderjosefermingomez on 11/20/17.
 */


class MainContract {
    interface Model {
        fun timerObservable(lastTime: Long): Observable<Long>
    }

    interface View {
        val addSpin: Observable<Any>
        val switch: Observable<Any>
        val reset: Observable<Any>
        var timer: String?
        fun setTimer(id: Int)
        fun addLap(text: String)
        fun setContentDrawable(drawable: ShapeDrawable)
        fun inflateLayout(container: ViewGroup? = null): android.view.View?
        var switchIcon: Int?
        fun removeLaps()
    }

    interface Presenter {
        val view: View
        val model: Model
        fun onCreate()
        fun onDestroy()
    }
}