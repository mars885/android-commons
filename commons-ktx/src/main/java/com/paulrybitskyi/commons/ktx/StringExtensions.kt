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

@file:JvmName("StringUtils")

package com.paulrybitskyi.commons.ktx

import android.graphics.Color
import java.util.Locale

val String.isColor: Boolean
    get() = try {
        Color.parseColor(this)
        true
    } catch (ignore: Throwable) {
        false
    }

/**
 * Truncates the string to the specified limit with the option
 * to ellipsize the ending of the string.
 *
 * @param characterLimit The number of characters to truncate
 * @param ellipsize Whether to ellipsize the ending or not
 *
 * @return The truncated string
 */
fun String.truncate(characterLimit: Int, ellipsize: Boolean = true): String {
    if (isBlank() || (length <= characterLimit)) {
        return this
    }

    return (substring(0, characterLimit) + (if (ellipsize) "…" else ""))
}

/**
 * Capitalizes the first letter of the string.
 *
 * @param locale The current locale
 *
 * @return The capitalized string
 */
fun String.capitalize(locale: Locale): String {
    if (isBlank()) {
        return this
    }

    return substring(0, 1).toUpperCase(locale) + substring(1)
}
