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

@file:JvmName("TextViewUtils")

package com.paulrybitskyi.commons.ktx.views

import android.annotation.SuppressLint
import android.graphics.Paint
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.text.InputType
import android.util.TypedValue
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.annotation.StyleRes
import com.paulrybitskyi.commons.SdkInfo
import com.paulrybitskyi.commons.ktx.containsBits
import com.paulrybitskyi.commons.ktx.setColor

private const val COMPOUND_DRAWABLE_INDEX_LEFT = 0
private const val COMPOUND_DRAWABLE_INDEX_TOP = 1
private const val COMPOUND_DRAWABLE_INDEX_RIGHT = 2
private const val COMPOUND_DRAWABLE_INDEX_BOTTOM = 3

var TextView.isTextStruckThrough: Boolean
    set(value) {
        paintFlags = if (value) {
            (paintFlags or Paint.STRIKE_THRU_TEXT_FLAG)
        } else {
            (paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv())
        }
    }
    get() = paintFlags.containsBits(Paint.STRIKE_THRU_TEXT_FLAG)

var TextView.startDrawable: Drawable?
    set(value) = updateCompoundDrawable(start = value)
    get() = compoundDrawablesRelative[COMPOUND_DRAWABLE_INDEX_LEFT]

var TextView.topDrawable: Drawable?
    set(value) = updateCompoundDrawable(top = value)
    get() = compoundDrawablesRelative[COMPOUND_DRAWABLE_INDEX_TOP]

var TextView.endDrawable: Drawable?
    set(value) = updateCompoundDrawable(end = value)
    get() = compoundDrawablesRelative[COMPOUND_DRAWABLE_INDEX_RIGHT]

var TextView.bottomDrawable: Drawable?
    set(value) = updateCompoundDrawable(bottom = value)
    get() = compoundDrawablesRelative[COMPOUND_DRAWABLE_INDEX_BOTTOM]

fun TextView.updateCompoundDrawable(
    start: Drawable? = startDrawable,
    top: Drawable? = topDrawable,
    end: Drawable? = endDrawable,
    bottom: Drawable? = bottomDrawable
) {
    setCompoundDrawablesRelativeWithIntrinsicBounds(start, top, end, bottom)
}

fun TextView.setCompoundDrawableColor(@ColorInt color: Int) {
    updateCompoundDrawable(
        start = startDrawable?.setColor(color),
        top = topDrawable?.setColor(color),
        end = endDrawable?.setColor(color),
        bottom = bottomDrawable?.setColor(color)
    )
}

fun TextView.clearCompoundDrawables() {
    updateCompoundDrawable(null, null, null, null)
    compoundDrawablePadding = 0
}

@Suppress("DEPRECATION")
@SuppressLint("NewApi")
fun TextView.setTextAppearanceCompat(@StyleRes id: Int) {
    if (SdkInfo.IS_AT_LEAST_MARSHMALLOW) {
        setTextAppearance(id)
    } else {
        setTextAppearance(context, id)
    }
}

fun TextView.setLineCount(count: Int) {
    minLines = count
    maxLines = count
    setLines(count)
}

fun TextView.enableMultilineText() {
    minLines = 0
    maxLines = Integer.MAX_VALUE
    isSingleLine = false
}

fun TextView.disableMultilineText() {
    minLines = 1
    maxLines = 1
    isSingleLine = true
}

fun TextView.setMultilineTextEnabled(isMultilineTextEnabled: Boolean) {
    if (isMultilineTextEnabled) {
        enableMultilineText()
    } else {
        disableMultilineText()
    }
}

fun TextView.setSingleLineTextEnabled(isSingleLineTextEnabled: Boolean) {
    if (isSingleLineTextEnabled) {
        disableMultilineText()
    } else {
        enableMultilineText()
    }
}

fun TextView.setTextSizeInPx(size: Float) {
    setTextSize(TypedValue.COMPLEX_UNIT_PX, size)
}

fun TextView.setTextSizeInSp(size: Float) {
    setTextSize(TypedValue.COMPLEX_UNIT_SP, size)
}

fun TextView.setFontFamily(fontFamily: String) {
    typeface = Typeface.create(fontFamily, Typeface.NORMAL)
}

/**
 * Checks whether the text is ellipsized. Should only be called after
 * layout phase is finished. Otherwise returns false.
 */
fun TextView.isTextEllipsized(): Boolean {
    if (layout == null) return false

    val textLayout = checkNotNull(layout)
    val lineCount = textLayout.lineCount

    return ((lineCount > 0) && (textLayout.getEllipsisCount(lineCount - 1) > 0))
}

fun TextView.containsRawText() {
    inputType = InputType.TYPE_CLASS_TEXT
}

fun TextView.containsEmailAddress() {
    inputType = (InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS)
}

fun TextView.containsPersonName() {
    inputType = (InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PERSON_NAME)
}

fun TextView.containsPassword() {
    inputType = (InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD)
}

fun TextView.containsVisiblePassword() {
    inputType = (InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD)
}
