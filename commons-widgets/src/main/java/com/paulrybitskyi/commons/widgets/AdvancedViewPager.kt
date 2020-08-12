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

package com.paulrybitskyi.commons.widgets

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.viewpager.widget.ViewPager

class AdvancedViewPager @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
): ViewPager(context, attrs) {


    var isSwipingEnabled = true


    fun enableSwiping() {
        isSwipingEnabled = true
    }


    fun disableSwiping() {
        isSwipingEnabled = false
    }


    override fun onTouchEvent(event: MotionEvent): Boolean {
        return (isSwipingEnabled && super.onTouchEvent(event))
    }


    override fun onInterceptTouchEvent(event: MotionEvent): Boolean {
        return (isSwipingEnabled && super.onInterceptTouchEvent(event))
    }


}