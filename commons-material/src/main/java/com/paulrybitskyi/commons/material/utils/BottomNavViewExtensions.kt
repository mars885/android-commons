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

@file:JvmName("BottomNavViewUtils")

package com.paulrybitskyi.commons.material.utils

import android.content.res.ColorStateList
import androidx.annotation.ColorInt
import com.google.android.material.bottomnavigation.BottomNavigationView

fun BottomNavigationView.setItemColors(
    @ColorInt unselectedStateColor: Int,
    @ColorInt selectedStateColor: Int
) {
    ColorStateList(
        arrayOf(
            intArrayOf(-android.R.attr.state_checked),
            intArrayOf()
        ),
        intArrayOf(
            unselectedStateColor,
            selectedStateColor
        )
    ).also {
        itemTextColor = it
        itemIconTintList = it
    }
}
