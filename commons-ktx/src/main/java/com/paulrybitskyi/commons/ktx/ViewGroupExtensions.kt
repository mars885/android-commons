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

@file:JvmName("ViewGroupUtils")

package com.paulrybitskyi.commons.ktx

import android.view.ViewGroup
import androidx.annotation.Px

val ViewGroup.MarginLayoutParams.horizontalMargin: Int
    get() = (marginStart + marginEnd)

val ViewGroup.MarginLayoutParams.verticalMargin: Int
    get() = (topMargin + bottomMargin)

fun ViewGroup.MarginLayoutParams.setHorizontalMargin(@Px margin: Int) {
    marginStart = margin
    marginEnd = margin
}

fun ViewGroup.MarginLayoutParams.setVerticalMargin(@Px margin: Int) {
    topMargin = margin
    bottomMargin = margin
}
