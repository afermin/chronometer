package com.rhino.chronometer.ui.mvp

import android.util.Log
import com.rhino.chronometer.R
import com.rhino.chronometer.Util
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable


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
        view.setContentDrawable(R.color.gray_900)

        compositeDisposable.add(loadSavedState())
    }

    override fun onDestroy() {
        pause()
        compositeDisposable.clear()
    }

    private fun loadSavedState(): Disposable {
        return model.getTimeFromSaveState()!!
                .subscribe { time ->
                    lastTime = time
                    view.timer = Util.chronometerFormat(lastTime)
                    Log.d("loadSavedState", lastTime.toString())
                    model.getPauseFromSaveState()!!.subscribe({ paused ->
                        pause = paused
                        Log.d("loadSavedState", pause.toString())
                        if (!paused) startTimer()
                        else if (lastTime != 0L ) {
                            view.startAnimationTimer()
                            view.setContentDrawable(R.color.gray_500)
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
            view.setContentDrawable(R.color.gray_500)
        }
    }

    private fun startTimer() {
        view.setContentDrawable(R.color.accent)
        view.clearAnimationTimer()
        pause = false
        model.savePauseState(pause)
        timer = getTimerObservable()
        view.switchIcon = R.drawable.ic_pause_48px
    }

    private fun getTimerObservable(): Disposable? {
        return model.timerObservable(lastTime)
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    lastTime = it
                    if (lastTime==0L) Log.d("-- loadSavedState", lastTime.toString())

                    model.saveTimeState(lastTime)
                    lastTimeString = Util.chronometerFormat(it)
                    view.timer = lastTimeString
                })
    }

    private fun pause() {
        pause = true

        if (timer != null) {
            timer!!.dispose()
            timer = null
        }
    }

    private fun reset() {
        pause()
        model.saveTimeState(0)
        view.setContentDrawable(R.color.gray_900)
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
