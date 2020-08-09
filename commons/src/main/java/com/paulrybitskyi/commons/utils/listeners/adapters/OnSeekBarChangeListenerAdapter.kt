package com.paulrybitskyi.commons.utils.listeners.adapters

import android.widget.SeekBar

interface OnSeekBarChangeListenerAdapter : SeekBar.OnSeekBarChangeListener {

    override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {}

    override fun onStartTrackingTouch(seekBar: SeekBar) {}

    override fun onStopTrackingTouch(seekBar: SeekBar) {}

}