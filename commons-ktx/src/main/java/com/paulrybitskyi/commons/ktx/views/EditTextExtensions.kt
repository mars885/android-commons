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

@file:JvmName("EditTextUtils")

package com.paulrybitskyi.commons.ktx.views

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.TextView
import androidx.core.widget.doOnTextChanged
import com.paulrybitskyi.commons.SdkInfo


val EditText.isEmpty: Boolean
    get() = content.isEmpty()

val EditText.content: String
    get() = text.toString()

var EditText.isEditingEnabled: Boolean
    set(value) { isFocusable = value }
    get() = isFocusable


/**
 * Sets a cursor drawable of the EditText.
 *
 * Note: This solution is based on reflection since currently there is
 * not an option to set the drawable programmatically.
 * Based on the answer: https://stackoverflow.com/a/26543290
 *
 * Note: This solution won't work on PIE devices.
 *
 * @param drawable The drawable to set
 */
@SuppressLint("SoonBlockedPrivateApi", "DiscouragedPrivateApi")
fun EditText.setCursorDrawable(drawable: Drawable) {
    if(SdkInfo.IS_AT_LEAST_PIE) {
        return
    }

    try {
        val cursorDrawableResourceField = TextView::class.java.getDeclaredField("mCursorDrawableRes")
        cursorDrawableResourceField.isAccessible = true

        val editorField = TextView::class.java.getDeclaredField("mEditor")
        editorField.isAccessible = true

        val cursorDrawableFieldOwner = editorField.get(this)
        val cursorDrawableFieldClass = cursorDrawableFieldOwner.javaClass

        val cursorDrawableField = cursorDrawableFieldClass.getDeclaredField("mCursorDrawable")
        cursorDrawableField.isAccessible = true
        cursorDrawableField.set(
            cursorDrawableFieldOwner,
            arrayOf(drawable, drawable)
        )
    } catch (exception: Exception) {
        // Ignore
    }
}


inline fun EditText.onTextChanged(crossinline callback: (String) -> Unit): TextWatcher {
    return doOnTextChanged { text, _, _, _ ->
        callback(text.toString())
    }
}