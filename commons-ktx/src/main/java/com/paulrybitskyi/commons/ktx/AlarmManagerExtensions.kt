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

@file:JvmName("AlarmManagerUtils")

package com.paulrybitskyi.commons.ktx

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import com.paulrybitskyi.commons.SdkInfo

@SuppressLint("NewApi")
fun AlarmManager.setAlarm(triggerAtMillis: Long, pendingIntent: PendingIntent) {
    if (SdkInfo.IS_AT_LEAST_MARSHMALLOW) {
        setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, triggerAtMillis, pendingIntent)
    } else if (SdkInfo.IS_AT_LEAST_KITKAT && !SdkInfo.IS_AT_LEAST_MARSHMALLOW) {
        setExact(AlarmManager.RTC_WAKEUP, triggerAtMillis, pendingIntent)
    } else {
        set(AlarmManager.RTC_WAKEUP, triggerAtMillis, pendingIntent)
    }
}
