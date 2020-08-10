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

package com.paulrybitskyi.commons.device.info.model.screen

import android.content.res.Configuration

enum class ScreenSizeCategory(
    val layoutSize: Int,
    val title: String
) {


    UNDEFINED(
        layoutSize = Configuration.SCREENLAYOUT_SIZE_UNDEFINED,
        title = "Undefined"
    ),
    SMALL(
        layoutSize = Configuration.SCREENLAYOUT_SIZE_SMALL,
        title = "Small"
    ),
    NORMAL(
        layoutSize = Configuration.SCREENLAYOUT_SIZE_NORMAL,
        title = "Normal"
    ),
    LARGE(
        layoutSize = Configuration.SCREENLAYOUT_SIZE_LARGE,
        title = "Large"
    ),
    XLARGE(
        layoutSize = Configuration.SCREENLAYOUT_SIZE_XLARGE,
        title = "XLarge"
    );


    companion object {

        fun Int.asScreenSizeCategory(): ScreenSizeCategory {
            return values().find { it.layoutSize == this }
                ?: UNDEFINED
        }

    }


}