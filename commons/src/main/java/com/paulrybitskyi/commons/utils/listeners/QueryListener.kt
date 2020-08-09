package com.paulrybitskyi.commons.utils.listeners

import com.paulrybitskyi.commons.utils.listeners.adapters.TextWatcherAdapter

/**
 * An implementation of the TextWatcher listener that provides
 * a callback to get notified when the query is entered and removed.
 */
class QueryListener(private val callback: Callback) : TextWatcherAdapter {


    override fun onTextChanged(text: CharSequence, start: Int, before: Int, count: Int) {
        if(text.isNotEmpty()) {
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