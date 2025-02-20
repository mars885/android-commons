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

package com.paulrybitskyi.commons.widgets.toolbar

import android.view.Gravity

enum class TitleGravity(
    internal val id: Int,
    internal val gravity: Int
) {

    LEFT(
        id = 1,
        gravity = Gravity.START
    ),
    CENTER(
        id = 2,
        gravity = Gravity.CENTER
    ),
    RIGHT(
        id = 3,
        gravity = Gravity.END
    );

    companion object {

        @JvmName("forId")
        @JvmStatic
        internal fun Int.asTitleGravity(): TitleGravity {
            return values().find { it.id == this }
                ?: throw IllegalArgumentException("Could not find the title gravity for the specified ID: $this.")
        }
    }
}
