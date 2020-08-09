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

@file:JvmName("BundleUtils")
@file:Suppress("UNCHECKED_CAST")

package com.paulrybitskyi.commons.ktx

import android.os.Bundle
import android.os.Parcelable


fun Bundle.getBundle(key: String, default: Bundle): Bundle {
    return (getBundle(key) ?: default)
}


fun Bundle.getBundleOrThrow(key: String): Bundle {
    return checkNotNull(getBundle(key)) {
        "The bundle does not contain a bundle value with the key: $key."
    }
}


fun <T> Bundle.getSerializable(key: String, default: T): T {
    return ((getSerializable(key) as? T) ?: default)
}


fun <T : Any> Bundle.getSerializableOrThrow(key: String): T {
    return checkNotNull(getSerializable(key) as? T) {
        "The bundle does not contain a serializable value with the key: $key."
    }
}


fun <T : Parcelable> Bundle.getParcelable(key: String, default: T): T {
    return (getParcelable(key) ?: default)
}


fun <T : Parcelable> Bundle.getParcelableOrThrow(key: String): T {
    return checkNotNull(getParcelable(key)) {
        "The bundle does not contain a parcelable value with the key: $key."
    }
}