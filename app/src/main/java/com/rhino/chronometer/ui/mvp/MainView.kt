package com.rhino.chronometer.ui.mvp

import android.annotation.SuppressLint
import android.graphics.drawable.ShapeDrawable
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.jakewharton.rxbinding2.view.RxView
import com.rhino.chronometer.R
import com.rhino.chronometer.ui.MainActivity
import com.rhino.chronometer.ui.LapAdapter
import io.reactivex.Observable

import kotlinx.android.synthetic.main.main_activity.view.*

/**
 * Created by alexanderjosefermingomez on 11/20/17.
 */

@SuppressLint("ViewConstructor")
class MainView(val activity: MainActivity, val adapter: LapAdapter)
    : FrameLayout(activity), MainContract.View {

    override fun inflateLayout(container: ViewGroup?): View? {
        val view = inflate(activity, R.layout.main_activity, this)
        recyclerView?.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(activity)
        layoutManager.reverseLayout = true
        layoutManager.stackFromEnd = true
        recyclerView?.layoutManager = layoutManager

        recyclerView.adapter = adapter
        return view
    }

    override val addSpin: Observable<Any> by lazy { RxView.clicks(tvRight) }
    override val switch: Observable<Any> by lazy { RxView.clicks(btnAction) }
    override val reset: Observable<Any> by lazy { RxView.clicks(tvLeft) }
    override var timer: String?
        get() = tvTimer?.text?.toString()
        set(value) {
            this.activity.runOnUiThread {
                tvTimer?.text = value
            }
        }
    override var switchIcon: Int? = R.drawable.ic_play_arrow_48px
        set(value) {
            btnAction.setImageDrawable(ContextCompat.getDrawable(context, value!!))
        }

    override fun addLap(text: String) {
        adapter.addItem(text)
    }

    override fun setContentDrawable(drawable: ShapeDrawable) {

    }

    override fun setTimer(id: Int) {
        tvTimer.setText(id)
    }

    override fun removeLaps() {
        adapter.removeAll()
    }

}
