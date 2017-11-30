package com.rhino.chronometer.activities.main.mvp

import android.util.Log
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import com.rhino.chronometer.R
import com.rhino.chronometer.Util
import com.rhino.chronometer.model.Lap
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


/**
 * Created by alexanderjosefermingomez on 11/20/17.
 */

class MainPresenter(override val view: MainContract.View, override val model: MainContract.Model)
    : MainContract.Presenter {

    private val compositeDisposable = CompositeDisposable()

    private var pause = true

    private var lastTime: Long = 0

    private var lastTimeString: String = "0"

    private var timer: Disposable? = null

    override fun onCreate() {
        compositeDisposable.apply {
            add(view.addSpin.subscribe { addLap() })
            add(view.reset.subscribe { reset() })
            add(view.switch.subscribe { switch() })
        }
        view.setContentDrawable(R.color.gray_600)

        compositeDisposable.add(loadSavedState())
    }

    override fun onDestroy() {
        dispose()
        compositeDisposable.clear()
    }

    private fun loadSavedState(): Disposable {
        return model.getTimeFromSaveState()!!
                .subscribe { time ->
                    lastTime = time
                    view.setTimer(Util.chronometerFormat(lastTime))
                    Log.d("loadSavedState", lastTime.toString())
                    model.getPauseFromSaveState()!!.subscribe({ paused ->
                        pause = paused
                        Log.d("loadSavedState", pause.toString())
                        if (!paused) startTimer()
                        else if (lastTime != 0L ) {
                            view.startAnimationTimer()
                            view.setContentDrawable(R.color.gray_300)
                        }
                    })
                }
    }

    private fun switch() {
        if (pause) {
            startTimer()
        } else {
            view.startAnimationTimer()
            pause()
            model.savePauseState(pause)
            view.switchIcon = R.drawable.ic_play_arrow_48px
            view.setContentDrawable(R.color.gray_300)
        }
    }

    private fun startTimer() {
        view.visibilityLapButton = VISIBLE
        view.setContentDrawable(R.color.accent)
        view.clearAnimationTimer()
        pause = false
        model.savePauseState(pause)
        timer = getTimerObservable()
        view.switchIcon = R.drawable.ic_pause_48px
    }

    private fun getTimerObservable(): Disposable? {
        return model.timerObservable(lastTime)
                .onBackpressureDrop()
                .observeOn(Schedulers.io())
                .subscribe({
                    lastTime += MainModel.INTERVAL
                    model.saveTimeState(lastTime)
                    lastTimeString = Util.chronometerFormat(lastTime)
                    view.setTimer(lastTimeString)
                })
    }

    private fun pause() {
        pause = true
        view.visibilityLapButton = INVISIBLE
        dispose()
    }

    private fun dispose(){
        if (timer != null) {
            timer!!.dispose()
            timer = null
        }
    }

    private fun reset() {
        pause()
        model.saveTimeState(0)
        view.setContentDrawable(R.color.gray_600)
        view.clearAnimationTimer()
        lastTime = 0
        view.setTimer(R.string.init)
        view.switchIcon = R.drawable.ic_play_arrow_48px
        view.removeLaps()
    }

    private fun addLap() {
        view.addLap(lastTimeString)
    }

}
