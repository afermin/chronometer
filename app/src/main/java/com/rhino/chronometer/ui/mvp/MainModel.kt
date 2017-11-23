package com.rhino.chronometer.ui.mvp

import android.util.Log
import com.rhino.chronometer.ui.MainActivity
import io.reactivex.Flowable
import io.reactivex.Observable
import java.util.concurrent.TimeUnit


/**
 * Created by alexanderjosefermingomez on 11/20/17.
 */

class MainModel(activity: MainActivity) : MainContract.Model {

    override fun timerObservable(initTime: Long): Observable<Long> {
        Log.d("timerObservable", "timerObservable")
        return Observable.intervalRange(initTime,3600000-initTime,0L,1L, TimeUnit.MILLISECONDS)
    }


    /*override fun timerObservable(initTime: Long): Flowable<Long> by lazy {
        Log.d("timerObservable", "timerObservable")
        Flowable.intervalRange(initTime, 1L, TimeUnit.MILLISECONDS)
    }
*/



}
