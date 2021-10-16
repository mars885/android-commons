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

@file:JvmName("EditTextUtils")

package com.paulrybitskyi.commons.listeners.utils

import android.text.TextWatcher
import android.widget.EditText
import com.paulrybitskyi.commons.listeners.QueryListener

inline fun EditText.addQueryListener(
    crossinline onQueryEntered: (String) -> Unit = {},
    crossinline onQueryRemoved: () -> Unit = {}
): TextWatcher {
    val callback = object : QueryListener.Callback {

        override fun onQueryEntered(query: String) = onQueryEntered(query)
        override fun onQueryRemoved() = onQueryRemoved()
    }

    return QueryListener(callback)
}
