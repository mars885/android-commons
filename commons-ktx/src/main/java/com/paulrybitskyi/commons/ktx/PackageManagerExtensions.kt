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

@file:JvmName("PackageManagerUtils")

package com.paulrybitskyi.commons.ktx

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri


/**
 * Tries to figure out a package name of a native application that
 * is able to open the given url.
 *
 * Note: Due to [Android 11 package visibility changes](https://g.co/dev/packagevisibility), this
 * method does not work on Android 11 and above.
 *
 * @param url The given url
 *
 * @return The package name of a native application or null.
 */
@SuppressLint("QueryPermissionsNeeded")
fun PackageManager.getNativeAppPackageForUrl(url: String): String? {
    val genericAppsIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.example.com"))
    val resolvedGenericApps = queryIntentActivities(genericAppsIntent, 0).map {
        it.activityInfo.packageName
    }

    val specializedAppsIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    val resolvedSpecializedApps = queryIntentActivities(specializedAppsIntent, 0).map {
        it.activityInfo.packageName
    }

    return resolvedSpecializedApps
        .subtract(resolvedGenericApps)
        .firstOrNull()
}


/**
 * Checks if the given url can be opened by a native application on the device.
 *
 * Note: Due to [Android 11 package visibility changes](https://g.co/dev/packagevisibility), this
 * method does not work on Android 11 and above.
 *
 * @param url The url to check
 *
 * @return true if the url can be opened by a native app; false otherwise
 */
fun PackageManager.canUrlBeOpenedByNativeApp(url: String): Boolean {
    return (getNativeAppPackageForUrl(url) != null)
}