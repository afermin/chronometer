package com.rhino.chronometer.ui.mvp

import android.arch.lifecycle.ViewModel
import android.graphics.drawable.ShapeDrawable
import android.view.ViewGroup
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Observable

/**
 * Created by alexanderjosefermingomez on 11/20/17.
 */


class MainContract {
    interface Model {
        fun timerObservable(lastTime: Long): Observable<Long>
        fun saveTimeState(time: Long)
        fun getTimeFromSaveState(): Maybe<Long>?
        fun savePauseState(pause: Boolean)
        fun getPauseFromSaveState(): Maybe<Boolean>?
    }

    interface View {
        val addSpin: Observable<Any>
        val switch: Observable<Any>
        val reset: Observable<Any>
        var timer: String?
        var visibilityLapButton: Int?
        fun setTimer(id: Int)
        fun addLap(text: String)
        fun setContentDrawable(color: Int)
        fun inflateLayout(container: ViewGroup? = null): android.view.View?
        var switchIcon: Int?
        fun removeLaps()
        fun startAnimationTimer()
        fun clearAnimationTimer()
    }

    interface Presenter {
        val view: View
        val model: Model
        fun onCreate()
        fun onDestroy()
    }
}