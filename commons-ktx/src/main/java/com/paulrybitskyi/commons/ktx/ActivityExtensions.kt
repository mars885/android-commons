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

@file:JvmName("ActivityUtils")

package com.paulrybitskyi.commons.ktx

import android.annotation.SuppressLint
import android.app.Activity
import android.app.ActivityManager
import android.graphics.BitmapFactory
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import com.paulrybitskyi.commons.SdkInfo


@get:ColorInt
var Activity.statusBarColor: Int
    set(@ColorInt value) { window.statusBarColor = value }
    get() = window.statusBarColor

@get:ColorInt
var Activity.navigationBarColor: Int
    set(@ColorInt value) { window.navigationBarColor = value }
    get() = window.navigationBarColor


fun Activity.makeScreenAlwaysAwake() {
    window.makeScreenAlwaysAwake()
}


fun Activity.makeScreenSleepable() {
    window.makeScreenSleepable()
}


fun Activity.setScreenAlwaysAwake(isScreenAlwaysAwake: Boolean) {
    window.setScreenAlwaysAwake(isScreenAlwaysAwake)
}


fun Activity.setSoftInputMode(mode: Int) {
    window.setSoftInputMode(mode)
}


@Suppress("DEPRECATION")
@SuppressLint("NewApi")
fun Activity.setTaskDescription(
    label: String,
    @DrawableRes iconId: Int,
    @ColorInt primaryColor: Int
) {
    setTaskDescription(
        if(SdkInfo.IS_AT_LEAST_PIE) {
            ActivityManager.TaskDescription(label, iconId, primaryColor)
        } else {
            ActivityManager.TaskDescription(
                label,
                BitmapFactory.decodeResource(resources, iconId),
                primaryColor
            )
        }
    )
}