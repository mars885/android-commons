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

package com.paulrybitskyi.commons.listeners

import com.paulrybitskyi.commons.listeners.adapters.TextWatcherAdapter

/**
 * An implementation of the TextWatcher listener that provides
 * a callback to get notified when the query is entered and removed.
 */
class QueryListener(private val callback: Callback) : TextWatcherAdapter {

    override fun onTextChanged(text: CharSequence, start: Int, before: Int, count: Int) {
        if (text.isNotEmpty()) {
            callback.onQueryEntered(text.toString())
        } else {
            callback.onQueryRemoved()
        }
    }

    /**
     * A helper callback to get notified when a query is entered or removed.
     */
    interface Callback {

        /**
         * Gets called whenever a character is entered.
         *
         * @param query The query entered
         */
        fun onQueryEntered(query: String)

        /**
         * Gets called whenever a query is removed.
         */
        fun onQueryRemoved()
    }
}
