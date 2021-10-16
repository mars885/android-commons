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

package com.paulrybitskyi.commons.listeners.gestures

import android.view.GestureDetector
import android.view.MotionEvent

/**
 * A listener that provides hooks to some of the most
 * common gestures.
 */
class GestureListener(private val callback: Callback) : GestureDetector.SimpleOnGestureListener() {

    override fun onSingleTapConfirmed(event: MotionEvent): Boolean {
        callback.onSingleTap(event)

        return super.onSingleTapConfirmed(event)
    }

    override fun onDoubleTap(event: MotionEvent): Boolean {
        callback.onDoubleTap(event)

        return super.onDoubleTap(event)
    }

    override fun onFling(
        startEvent: MotionEvent,
        endEvent: MotionEvent,
        velocityX: Float,
        velocityY: Float
    ): Boolean {
        callback.onFling(startEvent, endEvent)

        val deltaX = (startEvent.x - endEvent.x)
        val deltaY = (startEvent.y - endEvent.y)

        detectHorizontalSwipes(deltaX, startEvent, endEvent)
        detectVerticalSwipes(deltaY, startEvent, endEvent)

        return super.onFling(startEvent, endEvent, velocityX, velocityY)
    }

    private fun detectHorizontalSwipes(
        deltaX: Float,
        startEvent: MotionEvent,
        endEvent: MotionEvent
    ) {
        if (deltaX > 0f) {
            callback.onSwipedToLeft(startEvent, endEvent)
        } else {
            callback.onSwipedToRight(startEvent, endEvent)
        }
    }

    private fun detectVerticalSwipes(
        deltaY: Float,
        startEvent: MotionEvent,
        endEvent: MotionEvent
    ) {
        if (deltaY > 0f) {
            callback.onSwipedToTop(startEvent, endEvent)
        } else {
            callback.onSwipedToBottom(startEvent, endEvent)
        }
    }

    interface Callback {

        fun onSingleTap(motionEvent: MotionEvent)

        fun onDoubleTap(motionEvent: MotionEvent)

        fun onFling(startEvent: MotionEvent, endEvent: MotionEvent)

        fun onSwipedToLeft(startEvent: MotionEvent, endEvent: MotionEvent)

        fun onSwipedToRight(startEvent: MotionEvent, endEvent: MotionEvent)

        fun onSwipedToTop(startEvent: MotionEvent, endEvent: MotionEvent)

        fun onSwipedToBottom(startEvent: MotionEvent, endEvent: MotionEvent)
    }
}
