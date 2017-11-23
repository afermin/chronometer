package com.rhino.chronometer.ui.mvp

import android.util.Log
import com.rhino.chronometer.R
import com.rhino.chronometer.Util
import com.rhino.chronometer.model.Lap
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import java.util.*


/**
 * Created by alexanderjosefermingomez on 11/20/17.
 */

class MainPresenter(
        override val view: MainContract.View, override val model: MainContract.Model
) : MainContract.Presenter {

    private val compositeDisposable = CompositeDisposable()

    private var played = false

    private var lastTime: Long = 0

    private var lastTimeString: String = "0"

    private var timer: Disposable? = null

    override fun onCreate() {
        compositeDisposable.apply {
            add(view.addSpin.subscribe { addLap() })
            add(view.reset.subscribe { reset() })
            add(view.switch.subscribe { switch() })
        }

    }

    override fun onDestroy() {
        compositeDisposable.clear()
    }

    private fun switch() {
        if (played) {
            pause()
            view.switchIcon = R.drawable.ic_play_arrow_48px
        } else {
            played = true
            timer = model.timerObservable(lastTime)
                    .subscribeOn(AndroidSchedulers.mainThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        lastTime = it
                        lastTimeString = Util.chronometerFormat(it)
                        view.timer = lastTimeString
                    })

            view.switchIcon = R.drawable.ic_pause_48px
        }
    }

    private fun pause() {
        played = false

        if (timer != null) {
            timer!!.dispose()
            timer = null
        }
    }

    private fun reset() {
        pause()
        lastTime = 0
        view.setTimer(R.string.init)
        view.switchIcon = R.drawable.ic_play_arrow_48px
        view.removeLaps()
    }

    private fun addLap() {
        view.addLap(lastTimeString)
    }

}
