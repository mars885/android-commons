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

@file:JvmName("IntentUtils")
@file:Suppress("UNCHECKED_CAST")

package com.paulrybitskyi.commons.ktx

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable

fun Intent.getStringExtra(key: String, default: String): String {
    return (getStringExtra(key) ?: default)
}

fun Intent.getStringExtraOrThrow(key: String): String {
    return checkNotNull(getStringExtra(key)) {
        "The intent does not contain a string value with the key: $key."
    }
}

fun Intent.getBundleExtra(key: String, default: Bundle): Bundle {
    return (getBundleExtra(key) ?: default)
}

fun Intent.getBundleExtraOrThrow(key: String): Bundle {
    return checkNotNull(getBundleExtra(key)) {
        "The intent does not contain a bundle value with the key: $key."
    }
}

fun <T> Intent.getSerializableExtra(key: String, default: T): T {
    return ((getSerializableExtra(key) as? T) ?: default)
}

fun <T : Any> Intent.getSerializableExtraOrThrow(key: String): T {
    return checkNotNull(getSerializableExtra(key) as? T) {
        "The intent does not contain a serializable value with the key: $key."
    }
}

fun <T : Parcelable> Intent.getParcelableExtra(key: String, default: T): T {
    return (getParcelableExtra(key) ?: default)
}

fun <T : Parcelable> Intent.getParcelableOrThrow(key: String): T {
    return checkNotNull(getParcelableExtra(key)) {
        "The intent does not contain a parcelable value with the key: $key."
    }
}

fun Intent.singleTop(): Intent = apply {
    addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
}

fun Intent.newTask(): Intent = apply {
    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
}

fun Intent.multipleTask(): Intent = apply {
    addFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK)
}

fun Intent.clearTop(): Intent = apply {
    addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
}

fun Intent.clearTask(): Intent = apply {
    addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
}
