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

@file:JvmName("CanvasUtils")

package com.paulrybitskyi.commons.ktx.drawing

import android.annotation.SuppressLint
import android.graphics.Canvas
import android.graphics.Path
import android.graphics.Region
import com.paulrybitskyi.commons.SdkInfo

@Suppress("DEPRECATION")
@SuppressLint("NewApi")
fun Canvas.clipOutPathCompat(path: Path) {
    if (SdkInfo.IS_AT_LEAST_OREO) {
        clipOutPath(path)
    } else {
        clipPath(path, Region.Op.DIFFERENCE)
    }
}

@Suppress("DEPRECATION")
@SuppressLint("NewApi")
fun Canvas.clipPathCompat(path: Path) {
    if (SdkInfo.IS_AT_LEAST_OREO) {
        clipPath(path)
    } else {
        clipPath(path, Region.Op.INTERSECT)
    }
}
