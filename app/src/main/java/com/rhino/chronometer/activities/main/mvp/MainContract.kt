package com.rhino.chronometer.activities.main.mvp

import android.view.ViewGroup
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Observable
import java.util.*

/**
 * Created by alexanderjosefermingomez on 11/20/17.
 */


class MainContract {
    interface Model {
        fun timerObservable(lastTime: Long): Flowable<Long>
        fun saveTimeState(time: Long)
        fun getTimeFromSaveState(): Maybe<Long>?
        fun savePauseState(pause: Boolean)
        fun getPauseFromSaveState(): Maybe<Boolean>?
        fun addLap(lastTimeString: String)
        fun getLapsFromSaveState(): Maybe<ArrayList<String>>?
        fun clearSaveState()
    }

    interface View {
        val addSpin: Observable<Any>
        val switch: Observable<Any>
        val reset: Observable<Any>
        fun setTimer(string: String)
        var visibilityLapButton: Int?
        fun setTimer(id: Int)
        fun addLap(text: String)
        fun setContentDrawable(color: Int)
        fun inflateLayout(container: ViewGroup? = null): android.view.View?
        var switchIcon: Int?
        fun removeLaps()
        fun startAnimationTimer()
        fun clearAnimationTimer()
        fun setLaps(array: ArrayList<String>)
    }

    interface Presenter {
        val view: View
        val model: Model
        fun onCreate()
        fun onDestroy()
    }
}