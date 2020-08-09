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

@file:JvmName("TypedArrayUtils")

package com.paulrybitskyi.commons.ktx

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.content.res.TypedArray
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import androidx.annotation.StyleableRes
import androidx.core.content.res.ResourcesCompat
import com.paulrybitskyi.commons.SdkInfo


fun TypedArray.getString(@StyleableRes index: Int, default: CharSequence = ""): CharSequence {
    return (getString(index) ?: default)
}


fun TypedArray.getDrawable(@StyleableRes index: Int, default: Drawable? = null): Drawable? {
    return (getDrawable(index) ?: default)
}


@SuppressLint("NewApi")
fun TypedArray.getFont(
    context: Context,
    @StyleableRes index: Int,
    default: Typeface
): Typeface {
    return if(SdkInfo.IS_AT_LEAST_OREO) {
        (getFont(index) ?: default)
    } else {
        getResourceId(index, -1)
            .takeIf { it != -1 }
            ?.let { ResourcesCompat.getFont(context, it) }
            ?: default
    }
}


fun TypedArray.getColorStateList(@StyleableRes index: Int, default: ColorStateList): ColorStateList {
    return (getColorStateList(index) ?: default)
}