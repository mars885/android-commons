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

@file:JvmName("NumberUtils")

package com.paulrybitskyi.commons.ktx

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import androidx.annotation.ColorInt
import androidx.annotation.FloatRange
import kotlin.math.roundToInt


private const val COLOR_ALPHA_MAX = 255
private const val COLOR_ALPHA_MIN = 0

@Suppress("ReplaceRangeToWithUntil", "MagicNumber")
private val COLOR_ALPHA_TRANSLUCENT_RANGE = (COLOR_ALPHA_MIN + 1)..(COLOR_ALPHA_MAX - 1)


val Int.isEven: Boolean
    get() = ((this and 1) == 0)

val Int.isOdd: Boolean
    get() = !isEven

val Long.isEven: Boolean
    get() = ((this and 1L) == 0L)

val Long.isOdd: Boolean
    get() = !isEven

val Int.isOpaque: Boolean
    get() = (Color.alpha(this) == COLOR_ALPHA_MAX)

val Int.isTransparent: Boolean
    get() = (Color.alpha(this) == COLOR_ALPHA_MIN)

val Int.isTranslucent: Boolean
    get() = (Color.alpha(this) in COLOR_ALPHA_TRANSLUCENT_RANGE)


fun Int.dpToPx(context: Context): Int {
    return toFloat().dpToPx(context).roundToInt()
}


fun Float.dpToPx(context: Context): Float {
    return (this * context.displayMetrics.density)
}


fun Int.spToPx(context: Context): Int {
    return toFloat().spToPx(context).roundToInt()
}


fun Float.spToPx(context: Context): Float {
    return (this * context.displayMetrics.scaledDensity)
}


fun Int.pxToDp(context: Context): Int {
    return toFloat().pxToDp(context).roundToInt()
}


fun Float.pxToDp(context: Context): Float {
    return (this / context.displayMetrics.density)
}


fun Int.pxToSp(context: Context): Int {
    return toFloat().pxToSp(context).roundToInt()
}


fun Float.pxToSp(context: Context): Float {
    return (this / context.displayMetrics.scaledDensity)
}


fun Int.containsBits(bits: Int): Boolean {
    return ((this and bits) == bits)
}


/**
 * Adjusts value of the alpha channel of the color.
 *
 * @param alpha The alpha to set. Accepts values in a range from 0.0 (min)
 * to 1.0 (max).
 *
 * @return The color with adjusted alpha
 */
fun @receiver:ColorInt Int.adjustAlpha(@FloatRange(from = 0.0, to = 1.0) alpha: Float): Int {
    val alphaChannel = (COLOR_ALPHA_MAX * alpha).toInt()
    val redChannel = Color.red(this)
    val greenChannel = Color.green(this)
    val blueChannel = Color.blue(this)

    return Color.argb(alphaChannel, redChannel, greenChannel, blueChannel)
}


fun @receiver:ColorInt Int.withHalfAlpha(): Int {
    return adjustAlpha(alpha = 0.5f)
}


fun @receiver:ColorInt Int.toColorStateList(): ColorStateList {
    return ColorStateList.valueOf(this)
}
