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

@file:JvmName("FragmentUtils")

package com.paulrybitskyi.commons.ktx

import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.widget.Toast
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.annotation.DrawableRes
import androidx.fragment.app.Fragment


@get:ColorInt
var Fragment.statusBarColor: Int
    set(@ColorInt value) { requireActivity().statusBarColor = value }
    get() = requireActivity().statusBarColor

@get:ColorInt
var Fragment.navigationBarColor: Int
    set(@ColorInt value) { requireActivity().navigationBarColor = value }
    get() = requireActivity().navigationBarColor


fun Fragment.getColor(@ColorRes colorId: Int): Int {
    return requireContext().getCompatColor(colorId)
}


fun Fragment.getDimensionPixelSize(@DimenRes dimenId: Int): Int {
    return requireContext().getDimensionPixelSize(dimenId)
}


fun Fragment.getDimension(@DimenRes dimenId: Int): Float {
    return requireContext().getDimension(dimenId)
}


fun Fragment.getDrawable(@DrawableRes drawableId: Int): Drawable? {
    return requireContext().getCompatDrawable(drawableId)
}


fun Fragment.getColoredDrawable(@DrawableRes drawableId: Int, @ColorInt color: Int): Drawable? {
    return requireContext().getColoredDrawable(drawableId, color)
}


fun Fragment.getColoredStrokeDrawable(
    @DrawableRes drawableId: Int,
    @ColorInt strokeColor: Int,
    strokeWidth: Int
): Drawable? {
    return requireContext().getCompatDrawable(drawableId)
        ?.run { mutate() as? GradientDrawable }
        ?.apply { setStroke(strokeWidth, strokeColor) }
}


fun Fragment.showShortToast(message: CharSequence): Toast {
    return requireContext().showShortToast(message)
}


fun Fragment.showLongToast(message: CharSequence): Toast {
    return requireContext().showLongToast(message)
}


fun Fragment.isPermissionGranted(permission: String): Boolean {
    return requireContext().isPermissionGranted(permission)
}


fun Fragment.isPermissionDenied(permission: String): Boolean {
    return requireContext().isPermissionDenied(permission)
}


fun Fragment.arePermissionsGranted(permissions: Set<String>): Boolean {
    return requireContext().arePermissionsGranted(permissions)
}


fun Fragment.arePermissionsDenied(permissions: Set<String>): Boolean {
    return requireContext().arePermissionsDenied(permissions)
}


fun Fragment.makeScreenAlwaysAwake() {
    requireActivity().makeScreenAlwaysAwake()
}


fun Fragment.makeScreenSleepable() {
    requireActivity().makeScreenSleepable()
}


fun Fragment.setScreenAlwaysAwake(isScreenAlwaysAwake: Boolean) {
    requireActivity().setScreenAlwaysAwake(isScreenAlwaysAwake)
}


fun Fragment.setSoftInputMode(mode: Int) {
    requireActivity().setSoftInputMode(mode)
}