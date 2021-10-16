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

@file:JvmName("SwitchCompatUtils")

package com.paulrybitskyi.commons.ktx.views

import android.content.res.ColorStateList
import androidx.annotation.ColorInt
import androidx.appcompat.widget.SwitchCompat
import androidx.core.graphics.drawable.DrawableCompat
import com.paulrybitskyi.commons.ktx.withHalfAlpha

fun SwitchCompat.setColor(@ColorInt color: Int) {
    setColors(color, color, color, color)
}

fun SwitchCompat.setColors(
    @ColorInt deactivatedPointerColor: Int,
    @ColorInt activatedPointerColor: Int,
    @ColorInt deactivatedBackgroundColor: Int,
    @ColorInt activatedBackgroundColor: Int
) {
    val switchStates: Array<IntArray> = arrayOf(
        intArrayOf(-android.R.attr.state_checked),
        intArrayOf(android.R.attr.state_checked)
    )

    val switchThumbDrawableColors = intArrayOf(
        deactivatedPointerColor,
        activatedPointerColor
    )

    val switchTrackDrawableColors = intArrayOf(
        deactivatedBackgroundColor.withHalfAlpha(),
        activatedBackgroundColor.withHalfAlpha()
    )

    DrawableCompat.setTintList(
        DrawableCompat.wrap(thumbDrawable),
        ColorStateList(switchStates, switchThumbDrawableColors)
    )

    DrawableCompat.setTintList(
        DrawableCompat.wrap(trackDrawable),
        ColorStateList(switchStates, switchTrackDrawableColors)
    )
}
