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

@file:JvmName("DialogUtils")

package com.paulrybitskyi.commons.ktx

import android.app.Dialog
import android.graphics.drawable.Drawable
import android.widget.Toast
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.annotation.DrawableRes


fun Dialog.getCompatColor(@ColorRes colorId: Int): Int {
    return context.getCompatColor(colorId)
}


fun Dialog.getDimensionPixelSize(@DimenRes dimenId: Int): Int {
    return context.getDimensionPixelSize(dimenId)
}


fun Dialog.getDimension(@DimenRes dimenId: Int): Float {
    return context.getDimension(dimenId)
}


fun Dialog.getCompatDrawable(@DrawableRes drawableId: Int): Drawable? {
    return context.getCompatDrawable(drawableId)
}


fun Dialog.getColoredDrawable(@DrawableRes drawableId: Int, @ColorInt color: Int): Drawable? {
    return context.getColoredDrawable(drawableId, color)
}


fun Dialog.showShortToast(message: CharSequence): Toast {
    return context.showShortToast(message)
}


fun Dialog.showLongToast(message: CharSequence): Toast {
    return context.showLongToast(message)
}
