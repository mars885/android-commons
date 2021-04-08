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

@file:JvmName("WindowInsetsUtils")

package com.paulrybitskyi.commons.ktx

import android.annotation.SuppressLint
import android.view.View
import android.view.WindowInsets
import com.paulrybitskyi.commons.SdkInfo


fun View.applyWindowStartInsetAsMargin() {
    applyWindowInsetsAsMargin(applyStartInset = true)
}


fun View.applyWindowTopInsetAsMargin() {
    applyWindowInsetsAsMargin(applyTopInset = true)
}


fun View.applyWindowEndInsetAsMargin() {
    applyWindowInsetsAsMargin(applyEndInset = true)
}


fun View.applyWindowBottomInsetAsMargin() {
    applyWindowInsetsAsMargin(applyBottomInset = true)
}


fun View.applyWindowInsetsAsMargin(
    applyStartInset: Boolean = false,
    applyTopInset: Boolean = false,
    applyEndInset: Boolean = false,
    applyBottomInset: Boolean = false
) = applyWindowInsets(
    type = DimensionSnapshotType.MARGINS,
    applyStartInset = applyStartInset,
    applyTopInset = applyTopInset,
    applyEndInset = applyEndInset,
    applyBottomInset = applyBottomInset
)


fun View.applyWindowStartInsetAsPadding() {
    applyWindowInsetsAsPadding(applyStartInset = true)
}


fun View.applyWindowTopInsetAsPadding() {
    applyWindowInsetsAsPadding(applyTopInset = true)
}


fun View.applyWindowEndInsetAsPadding() {
    applyWindowInsetsAsPadding(applyEndInset = true)
}


fun View.applyWindowBottomInsetAsPadding() {
    applyWindowInsetsAsPadding(applyBottomInset = true)
}


fun View.applyWindowInsetsAsPadding(
    applyStartInset: Boolean = false,
    applyTopInset: Boolean = false,
    applyEndInset: Boolean = false,
    applyBottomInset: Boolean = false
) = applyWindowInsets(
    type = DimensionSnapshotType.PADDING,
    applyStartInset = applyStartInset,
    applyTopInset = applyTopInset,
    applyEndInset = applyEndInset,
    applyBottomInset = applyBottomInset
)


fun View.applyWindowInsets(
    type: DimensionSnapshotType,
    applyStartInset: Boolean = false,
    applyTopInset: Boolean = false,
    applyEndInset: Boolean = false,
    applyBottomInset: Boolean = false
) {
    doOnApplyWindowInsets(type) { targetView, insets, dimensions ->
        val start = (dimensions.start + (if(applyStartInset) insets.getCompatSystemWindowInsetLeft() else 0))
        val top = (dimensions.top + (if(applyTopInset) insets.getCompatSystemWindowInsetTop() else 0))
        val end = (dimensions.end + (if(applyEndInset) insets.getCompatSystemWindowInsetRight() else 0))
        val bottom = (dimensions.bottom + (if(applyBottomInset) insets.getCompatSystemWindowInsetBottom() else 0))

        when(type) {
            DimensionSnapshotType.MARGINS -> targetView.setMargins(start, top, end, bottom)
            DimensionSnapshotType.PADDING -> targetView.updatePadding(start, top, end, bottom)
        }
    }
}


fun View.doOnApplyWindowInsets(
    type: DimensionSnapshotType,
    listener: (View, WindowInsets, DimensionSnapshot) -> Unit
) {
    val dimensionSnapshot = createDimensionSnapshot(type)

    setOnApplyWindowInsetsListener { view, insets ->
        listener(view, insets, dimensionSnapshot)
        insets
    }

    requestApplyInsetsWhenAttached()
}


private fun View.createDimensionSnapshot(type: DimensionSnapshotType): DimensionSnapshot {
    return when(type) {

        DimensionSnapshotType.MARGINS -> DimensionSnapshot(
            start = this.startMargin,
            top = this.topMargin,
            end = this.endMargin,
            bottom = this.bottomMargin
        )

        DimensionSnapshotType.PADDING -> DimensionSnapshot(
            start = this.startPadding,
            top = this.topPadding,
            end = this.endPadding,
            bottom = this.bottomPadding
        )

    }
}


fun View.requestApplyInsetsWhenAttached() {
    if (isAttachedToWindow) {
        requestApplyInsets()
    } else {
        addOnAttachStateChangeListener(object : View.OnAttachStateChangeListener {

            override fun onViewAttachedToWindow(view: View) {
                view.removeOnAttachStateChangeListener(this)
                view.requestApplyInsets()
            }

            override fun onViewDetachedFromWindow(view: View) = Unit

        })
    }
}


@Suppress("DEPRECATION")
@SuppressLint("NewApi")
fun WindowInsets.getCompatSystemWindowInsetLeft(): Int {
    return if(SdkInfo.IS_AT_LEAST_11) {
        getInsets(WindowInsets.Type.systemBars()).left
    } else {
        systemWindowInsetLeft
    }
}


@Suppress("DEPRECATION")
@SuppressLint("NewApi")
fun WindowInsets.getCompatSystemWindowInsetTop(): Int {
    return if(SdkInfo.IS_AT_LEAST_11) {
        getInsets(WindowInsets.Type.systemBars()).top
    } else {
        systemWindowInsetTop
    }
}


@Suppress("DEPRECATION")
@SuppressLint("NewApi")
fun WindowInsets.getCompatSystemWindowInsetRight(): Int {
    return if(SdkInfo.IS_AT_LEAST_11) {
        getInsets(WindowInsets.Type.systemBars()).right
    } else {
        systemWindowInsetRight
    }
}


@Suppress("DEPRECATION")
@SuppressLint("NewApi")
fun WindowInsets.getCompatSystemWindowInsetBottom(): Int {
    return if(SdkInfo.IS_AT_LEAST_11) {
        getInsets(WindowInsets.Type.systemBars()).bottom
    } else {
        systemWindowInsetBottom
    }
}


enum class DimensionSnapshotType {

    MARGINS,
    PADDING

}


data class DimensionSnapshot(
    val start: Int,
    val top: Int,
    val end: Int,
    val bottom: Int
)