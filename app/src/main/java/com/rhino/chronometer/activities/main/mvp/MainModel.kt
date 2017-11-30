package com.rhino.chronometer.activities.main.mvp

import android.util.Log
import com.rhino.chronometer.activities.main.MainActivity
import com.twistedequations.rx2state.RxSaveState
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Observable
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * Created by alexanderjosefermingomez on 11/20/17.
 */

class MainModel(val activity: MainActivity) : MainContract.Model {

    private val LAPS_STATE_KEY = "LAPS_STATE_KEY"
    private val TIME_STATE_KEY = "TIME_STATE_KEY"
    private val PAUSE_STATE_KEY = "PAUSE_STATE_KEY"

    companion object {
        val INTERVAL = 103L
    }

    override fun timerObservable(initTime: Long): Flowable<Long> {
        Log.d("timerObservable", "timerObservable")
        return Flowable.intervalRange(initTime, 3600000 - initTime, 0L, INTERVAL,
                TimeUnit.MILLISECONDS)
    }

    override fun saveTimeState(time: Long) {
        this.activity.runOnUiThread {
            RxSaveState.updateSaveState(activity) { bundle -> bundle.putLong(TIME_STATE_KEY, time) }
        }
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

    override fun addLap(lastTimeString: String) {
        RxSaveState.updateSaveState(activity) { bundle ->
            var array = bundle.getStringArrayList(LAPS_STATE_KEY)
            if (array == null) array = ArrayList<String>()
            array.add(lastTimeString)
            bundle.putStringArrayList(LAPS_STATE_KEY, array)
        }
    }

    override fun getLapsFromSaveState(): Maybe<ArrayList<String>>? {
        return RxSaveState.getSavedState(activity)
                .map<ArrayList<String>> { bundle -> bundle.getStringArrayList(LAPS_STATE_KEY) }
    }

    override fun clearSaveState() {
        RxSaveState.getSavedStateDirect(activity)?.clear()
    }


}
