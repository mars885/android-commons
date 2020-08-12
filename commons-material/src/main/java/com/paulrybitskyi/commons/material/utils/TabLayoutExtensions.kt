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

@file:JvmName("TabLayoutUtils")

package com.paulrybitskyi.commons.material.utils

import com.google.android.material.tabs.TabLayout


inline fun TabLayout.addOnTabSelectedListener(
    crossinline onTabSelected: (TabLayout.Tab) -> Unit = {},
    crossinline onTabReselected: (TabLayout.Tab) -> Unit = {},
    crossinline onTabUnselected: (TabLayout.Tab) -> Unit = {}
): TabLayout.OnTabSelectedListener {
    return object : TabLayout.OnTabSelectedListener {

        override fun onTabSelected(tab: TabLayout.Tab) = onTabSelected(tab)
        override fun onTabReselected(tab: TabLayout.Tab) = onTabReselected(tab)
        override fun onTabUnselected(tab: TabLayout.Tab) = onTabUnselected(tab)

    }
    .also(::addOnTabSelectedListener)
}