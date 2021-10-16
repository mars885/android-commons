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

@file:JvmName("NavControllerUtils")

package com.paulrybitskyi.commons.navigation

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.navigation.NavController

fun NavController.containsDestination(@IdRes destinationId: Int): Boolean {
    return try {
        getBackStackEntry(destinationId)
        true
    } catch (ignore: Exception) {
        false
    }
}

fun NavController.containsAnyDestination(@IdRes destinationIds: List<Int>): Boolean {
    return destinationIds.any { containsDestination(it) }
}

fun NavController.getDestinationArgs(@IdRes destinationId: Int): Bundle? {
    return try {
        getBackStackEntry(destinationId).arguments
    } catch (ignore: Exception) {
        null
    }
}
