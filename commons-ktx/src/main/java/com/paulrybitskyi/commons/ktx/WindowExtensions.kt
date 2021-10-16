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

@file:JvmName("WindowUtils")

package com.paulrybitskyi.commons.ktx

import android.view.Window
import android.view.WindowManager.LayoutParams.FLAG_DIM_BEHIND
import android.view.WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON

var Window.isBackgroundDimmingEnabled: Boolean
    set(value) {
        if (value) {
            addFlags(FLAG_DIM_BEHIND)
        } else {
            clearFlags(FLAG_DIM_BEHIND)
        }
    }
    get() = ((attributes.flags and FLAG_DIM_BEHIND) == FLAG_DIM_BEHIND)

fun Window.makeScreenAlwaysAwake() {
    addFlags(FLAG_KEEP_SCREEN_ON)
}

fun Window.makeScreenSleepable() {
    clearFlags(FLAG_KEEP_SCREEN_ON)
}

fun Window.setScreenAlwaysAwake(isScreenAlwaysAwake: Boolean) {
    if (isScreenAlwaysAwake) {
        makeScreenAlwaysAwake()
    } else {
        makeScreenSleepable()
    }
}
