/*
 * Copyright 2020 Paul Rybitskyi, paul.rybitskyi.work@gmail.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.paulrybitskyi.commons.recyclerview

import android.view.View
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewScrollListener constructor(
    stateListener: StateListener,
    shouldNotifyOnReachingEndsRepeatedly: Boolean = false
) : RecyclerView.OnScrollListener() {

    private var shouldNotifyOnReachingEndsRepeatedly: Boolean = false

    private var firstVisiblePosition: Int = -1
    private var previousFirstVisiblePosition: Int = -1
    private var lastVisiblePosition: Int = -1
    private var previousLastVisiblePosition: Int = -1
    private var visibleChildrenCount: Int = 0
    private var totalItemCount: Int = 0
    private var previousTotalItemCount: Int = 0

    private var stateListener: StateListener? = null

    private var child: View? = null

    enum class Direction {

        UNSPECIFIED,
        UPWARDS,
        DOWNWARDS
    }

    init {
        this.stateListener = stateListener
        this.shouldNotifyOnReachingEndsRepeatedly = shouldNotifyOnReachingEndsRepeatedly
    }

    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {}

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        when {
            (dy > 0) -> onScrolledDownwards(recyclerView, dy)
            (dy < 0) -> onScrolledUpwards(recyclerView, dy)
        }
    }

    private fun onScrolledDownwards(recyclerView: RecyclerView, dy: Int) {
        stateListener?.onScrolledDownwards(recyclerView, dy)

        visibleChildrenCount = recyclerView.childCount
        previousFirstVisiblePosition = getFirstVisiblePosition(recyclerView)
        lastVisiblePosition = getLastVisiblePosition(recyclerView)
        totalItemCount = recyclerView.adapter!!.itemCount

        when {
            isBottomReached() -> onBottomReached(recyclerView)
            isMiddleReachedFromTop() -> onMiddleReachedFromTop(recyclerView)
        }
    }

    private fun isBottomReached(): Boolean {
        return (
            (lastVisiblePosition == (totalItemCount - 1)) &&
                ((lastVisiblePosition != previousLastVisiblePosition) || shouldNotifyOnReachingEnds())
            )
    }

    private fun onBottomReached(recyclerView: RecyclerView) {
        child = recyclerView.getChildAt(visibleChildrenCount - 1)
        previousLastVisiblePosition = lastVisiblePosition
        previousTotalItemCount = totalItemCount

        stateListener?.onBottomReached(
            recyclerView,
            ((child!!.top + child!!.measuredHeight) == recyclerView.measuredHeight)
        )
    }

    private fun isMiddleReachedFromTop(): Boolean {
        return (lastVisiblePosition == (totalItemCount / 2))
    }

    private fun onMiddleReachedFromTop(recyclerView: RecyclerView) {
        stateListener?.onMiddleReached(recyclerView, Direction.DOWNWARDS)
    }

    private fun onScrolledUpwards(recyclerView: RecyclerView, dy: Int) {
        stateListener?.onScrolledUpwards(recyclerView, dy)

        visibleChildrenCount = recyclerView.childCount
        previousFirstVisiblePosition = getFirstVisiblePosition(recyclerView)
        lastVisiblePosition = getLastVisiblePosition(recyclerView)
        totalItemCount = recyclerView.adapter!!.itemCount

        when {
            isTopReached() -> onTopReached(recyclerView)
            isMiddleReachedFromBottom() -> onMiddleReachedFromBottom(recyclerView)
        }
    }

    private fun isTopReached(): Boolean {
        return (
            (firstVisiblePosition == 0) &&
                ((firstVisiblePosition != previousFirstVisiblePosition) || shouldNotifyOnReachingEnds())
            )
    }

    private fun onTopReached(recyclerView: RecyclerView) {
        child = recyclerView.getChildAt(0)
        previousFirstVisiblePosition = firstVisiblePosition
        previousTotalItemCount = totalItemCount

        stateListener?.onTopReached(recyclerView, (child!!.top == 0))
    }

    private fun isMiddleReachedFromBottom(): Boolean {
        return (firstVisiblePosition == (totalItemCount / 2))
    }

    private fun onMiddleReachedFromBottom(recyclerView: RecyclerView) {
        stateListener?.onMiddleReached(recyclerView, Direction.UPWARDS)
    }

    private fun getFirstVisiblePosition(recyclerView: RecyclerView): Int {
        return if (recyclerView.childCount == 0) {
            -1
        } else {
            recyclerView.getChildAdapterPosition(recyclerView.getChildAt(0))
        }
    }

    private fun getLastVisiblePosition(recyclerView: RecyclerView): Int {
        return if (recyclerView.childCount == 0) {
            -1
        } else {
            recyclerView.getChildAdapterPosition(recyclerView.getChildAt(recyclerView.childCount - 1))
        }
    }

    private fun shouldNotifyOnReachingEnds(): Boolean {
        if (shouldNotifyOnReachingEndsRepeatedly) {
            return true
        }

        return (previousTotalItemCount != totalItemCount)
    }

    interface StateListener {

        fun onScrolledUpwards(recyclerView: RecyclerView, deltaY: Int)

        fun onScrolledDownwards(recyclerView: RecyclerView, deltaY: Int)

        fun onTopReached(recyclerView: RecyclerView, reachedCompletely: Boolean)

        fun onMiddleReached(recyclerView: RecyclerView, direction: Direction)

        fun onBottomReached(recyclerView: RecyclerView, reachedCompletely: Boolean)
    }
}
