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

package com.paulrybitskyi.commons.widgets

import android.content.Context
import android.text.TextWatcher
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText

class AdvancedEditText @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = R.attr.editTextStyle
) : AppCompatEditText(context, attrs, defStyleAttr) {

    private val listeners = mutableListOf<TextWatcher>()

    fun setInputType(type: Int, shouldNotifyListeners: Boolean = true) {
        performAction(shouldNotifyListeners) {
            super.setInputType(type)
        }
    }

    fun setText(text: CharSequence, shouldNotifyListeners: Boolean = true) {
        performAction(shouldNotifyListeners) {
            super.setText(text)
        }
    }

    override fun addTextChangedListener(listener: TextWatcher) {
        listeners.add(listener)

        super.addTextChangedListener(listener)
    }

    override fun removeTextChangedListener(listener: TextWatcher) {
        if (listeners.isEmpty()) return

        listeners.indexOf(listener)
            .takeIf { it >= 0 }
            ?.let { listeners.removeAt(it) }

        super.removeTextChangedListener(listener)
    }

    fun clearTextChangedListeners() {
        if (listeners.isEmpty()) return

        for (listener in listeners) {
            super.removeTextChangedListener(listener)
        }

        listeners.clear()
    }

    private fun performAction(
        shouldNotifyListeners: Boolean,
        action: (() -> Unit)
    ) {
        if (shouldNotifyListeners || listeners.isEmpty()) {
            action()
            return
        }

        val listenersCopy = listeners.toMutableList()

        clearTextChangedListeners()
        action()

        for (listener in listenersCopy) {
            addTextChangedListener(listener)
        }
    }
}
