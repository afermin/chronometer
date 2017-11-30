package com.rhino.chronometer.activities.main

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rhino.chronometer.R
import com.rhino.chronometer.activities.main.di.MainScope
import javax.inject.Inject
import android.widget.TextView
import java.util.*

/**
 * Created by alexanderjosefermingomez on 11/20/17.
 */
@MainScope
class LapAdapter @Inject constructor(@MainScope val context: Context): RecyclerView.Adapter<LapAdapter.ViewHolder>() {

    private val dataSet: MutableList<String> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view  = LayoutInflater.from(parent?.context).inflate(R.layout.item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.tvLap?.text = "#"+(position+1)
        holder?.tvTimer?.text = dataSet.get(position)
    }

    override fun getItemCount(): Int = dataSet.count()

    class ViewHolder(val view: View?): RecyclerView.ViewHolder(view) {

        val root = view
        val tvLap = view?.findViewById<TextView>(R.id.tvLap) as TextView
        val tvTimer = view?.findViewById<TextView>(R.id.tvTime) as TextView
    }

    fun addItem(text: String) {
        dataSet.add(text)
        notifyItemInserted(dataSet.size-1)
    }

    fun setItems(array: ArrayList<String>) {
        dataSet.clear()
        dataSet.addAll(array)
        notifyItemInserted(dataSet.size-1)
    }

    fun removeAll() {
        dataSet.clear()
        notifyDataSetChanged()
    }
}