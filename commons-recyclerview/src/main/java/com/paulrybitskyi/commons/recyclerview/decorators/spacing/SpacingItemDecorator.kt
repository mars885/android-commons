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

package com.paulrybitskyi.commons.recyclerview.decorators.spacing

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.paulrybitskyi.commons.ktx.containsBits

open class SpacingItemDecorator @JvmOverloads constructor(
    protected val spacing: Int,
    protected val sideFlags: Int,
    protected val itemExclusionPolicies: List<ItemExclusionPolicy> = listOf()
) : RecyclerView.ItemDecoration() {

    companion object {

        const val SIDE_LEFT = 0b0001
        const val SIDE_TOP = 0b0010
        const val SIDE_RIGHT = 0b0100
        const val SIDE_BOTTOM = 0b1000
        const val SIDE_ALL = 0b1111
    }

    constructor(
        spacing: Int,
        sideFlags: Int,
        itemExclusionPolicy: ItemExclusionPolicy
    ) : this(
        spacing = spacing,
        sideFlags = sideFlags,
        itemExclusionPolicies = listOf(itemExclusionPolicy)
    )

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        for (itemExclusionPolicy in itemExclusionPolicies) {
            if (itemExclusionPolicy.shouldExclude(view, parent)) {
                return
            }
        }

        if (sideFlags.containsBits(SIDE_LEFT) && shouldAssignSpacing(view, parent)) {
            outRect.left = spacing
        }

        if (sideFlags.containsBits(SIDE_TOP) && shouldAssignSpacing(view, parent)) {
            outRect.top = spacing
        }

        if (sideFlags.containsBits(SIDE_RIGHT) && shouldAssignSpacing(view, parent)) {
            outRect.right = spacing
        }

        if (sideFlags.containsBits(SIDE_BOTTOM) && shouldAssignSpacing(view, parent)) {
            outRect.bottom = spacing
        }
    }

    open fun shouldAssignSpacing(view: View, parent: RecyclerView): Boolean = true

    interface ItemExclusionPolicy {

        fun shouldExclude(view: View, parent: RecyclerView): Boolean
    }
}
