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

@file:JvmName("BitmapUtils")

package com.paulrybitskyi.commons.ktx

import android.graphics.Bitmap
import android.graphics.Color

val Bitmap.centerX: Float
    get() = (width / 2f)

val Bitmap.centerY: Float
    get() = (height / 2f)

/**
 * Checks whether the bitmap has any transparent pixels.
 *
 * Note: It is not desirable to invoke this method on the
 * main thread since it may take some time to finish.
 */
fun Bitmap.hasTransparentPixels(): Boolean {
    for (x in 0 until width) {
        for (y in 0 until height) {
            if (getPixel(x, y) == Color.TRANSPARENT) {
                return true
            }
        }
    }

    return false
}
