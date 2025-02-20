/*
 * Copyright 2020 Paul Rybitskyi, oss@paulrybitskyi.com
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

@file:JvmName("RecyclerViewUtils")

package com.paulrybitskyi.commons.recyclerview.utils

import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.RecyclerView
import com.paulrybitskyi.commons.recyclerview.RecyclerViewScrollListener
import com.paulrybitskyi.commons.recyclerview.RecyclerViewScrollListener.Direction

@Suppress("LongParameterList")
inline fun RecyclerView.addOnScrollListener(
    shouldNotifyOnReachingEndsRepeatedly: Boolean = false,
    crossinline onScrolledUpwards: (recyclerView: RecyclerView, deltaY: Int) -> Unit = { _, _ -> },
    crossinline onScrolledDownwards: (recyclerView: RecyclerView, deltaY: Int) -> Unit = { _, _ -> },
    crossinline onTopReached: (recyclerView: RecyclerView, reachedCompletely: Boolean) -> Unit = { _, _ -> },
    crossinline onMiddleReached: (recyclerView: RecyclerView, direction: Direction) -> Unit = { _, _ -> },
    crossinline onBottomReached: (recyclerView: RecyclerView, reachedCompletely: Boolean) -> Unit = { _, _ -> }
): RecyclerView.OnScrollListener {
    val stateListener = object : RecyclerViewScrollListener.StateListener {

        override fun onScrolledUpwards(recyclerView: RecyclerView, deltaY: Int) {
            onScrolledUpwards(recyclerView, deltaY)
        }

        override fun onScrolledDownwards(recyclerView: RecyclerView, deltaY: Int) {
            onScrolledDownwards(recyclerView, deltaY)
        }

        override fun onTopReached(recyclerView: RecyclerView, reachedCompletely: Boolean) {
            onTopReached(recyclerView, reachedCompletely)
        }

        override fun onMiddleReached(recyclerView: RecyclerView, direction: Direction) {
            onMiddleReached(recyclerView, direction)
        }

        override fun onBottomReached(recyclerView: RecyclerView, reachedCompletely: Boolean) {
            onBottomReached(recyclerView, reachedCompletely)
        }
    }

    return RecyclerViewScrollListener(
        stateListener = stateListener,
        shouldNotifyOnReachingEndsRepeatedly = shouldNotifyOnReachingEndsRepeatedly
    )
    .also(::addOnScrollListener)
}

fun RecyclerView.recreateItemViews() {
    val savedAdapter = adapter

    adapter = null
    adapter = savedAdapter
}

fun RecyclerView.disableScrollbars() {
    isVerticalScrollBarEnabled = false
    isHorizontalScrollBarEnabled = false
}

fun RecyclerView.disableOverScrollMode() {
    overScrollMode = RecyclerView.OVER_SCROLL_NEVER
}

fun RecyclerView.disableAddAnimations() {
    itemAnimator?.addDuration = 0L
}

fun RecyclerView.disableRemovalAnimations() {
    itemAnimator?.removeDuration = 0L
}

fun RecyclerView.disableMoveAnimations() {
    itemAnimator?.moveDuration = 0L
}

fun RecyclerView.disableUpdateAnimations() {
    itemAnimator?.changeDuration = 0L
}

fun RecyclerView.disableChangeAnimations() {
    (itemAnimator as? DefaultItemAnimator)?.supportsChangeAnimations = false
}

fun RecyclerView.disableAnimations() {
    if (itemAnimator != null) {
        itemAnimator = null
    }
}
