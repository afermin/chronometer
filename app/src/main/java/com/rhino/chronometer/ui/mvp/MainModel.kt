package com.rhino.chronometer.ui.mvp

import android.util.Log
import com.rhino.chronometer.ui.MainActivity
import com.twistedequations.rx2state.RxSaveState
import io.reactivex.Maybe
import io.reactivex.Observable
import java.util.concurrent.TimeUnit

/**
 * Created by alexanderjosefermingomez on 11/20/17.
 */

class MainModel(val activity: MainActivity) : MainContract.Model {

    private val TIME_STATE_KEY = "TIME_STATE_KEY"
    private val PAUSE_STATE_KEY = "PAUSE_STATE_KEY"

    override fun timerObservable(initTime: Long): Observable<Long> {
        Log.d("timerObservable", "timerObservable")
        return Observable.intervalRange(initTime, 3600000 - initTime, 0L, 1L, TimeUnit.MILLISECONDS)
    }

    override fun saveTimeState(time: Long) {
        RxSaveState.updateSaveState(activity) { bundle -> bundle.putLong(TIME_STATE_KEY, time) }
    }

    override fun getTimeFromSaveState(): Maybe<Long>? {
        return RxSaveState.getSavedState(activity)
                .map<Long> { bundle -> bundle.getLong(TIME_STATE_KEY) }
    }

    override fun savePauseState(pause: Boolean) {
        RxSaveState.updateSaveState(activity) { bundle -> bundle.putBoolean(PAUSE_STATE_KEY, pause) }
    }

    override fun getPauseFromSaveState(): Maybe<Boolean>? {
        return RxSaveState.getSavedState(activity)
                .map<Boolean> { bundle -> bundle.getBoolean(PAUSE_STATE_KEY) }
    }

}
