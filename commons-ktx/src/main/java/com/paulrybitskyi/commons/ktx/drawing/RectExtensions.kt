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

package com.paulrybitskyi.commons.ktx.drawing

import android.graphics.Rect
import android.graphics.RectF


val Rect.halfWidth : Int
    get() = (width() / 2)

val Rect.halfHeight : Int
    get() = (height() / 2)

val RectF.halfWidth : Float
    get() = (width() * 0.5f)

val RectF.halfHeight : Float
    get() = (height() * 0.5f)


fun Rect.setBounds(
    left: Int = this.left,
    top: Int = this.top,
    right: Int = this.right,
    bottom: Int = this.bottom
) {
    set(left, top, right, bottom)
}


fun RectF.setBounds(
    left: Float = this.left,
    top: Float = this.top,
    right: Float = this.right,
    bottom: Float = this.bottom
) {
    set(left, top, right, bottom)
}