package com.demo.mindvalley.assemblyrecyclerview

interface NotifyableAdapter {
    val listener: OnNotifyDatasetChangeListener?
        get() = null

    fun setOnNotifyDataSetChangeListener(onNotifyDataSetChangeListener: OnNotifyDatasetChangeListener)
}