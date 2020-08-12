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
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.Gravity
import android.widget.LinearLayout
import androidx.annotation.ColorInt
import androidx.core.content.withStyledAttributes
import androidx.core.view.isVisible
import com.paulrybitskyi.commons.ktx.*
import com.paulrybitskyi.commons.ktx.views.setTextSizeInPx
import kotlinx.android.synthetic.main.view_info.view.*

class InfoView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {


    var isDescriptionTextVisible: Boolean
        set(value) { descriptionTv.isVisible = value }
        get() = descriptionTv.isVisible

    var iconSize: Int = getDimensionPixelSize(R.dimen.info_view_icon_size)
        set(value) {
            field = value
            iconIv.setLayoutParamsSize(value)
        }

    var titleTextTopMargin: Int
        set(value) { titleTv.topMargin = value }
        get() = titleTv.topMargin

    var descriptionTextTopMargin: Int
        set(value) { descriptionTv.topMargin = value }
        get() = descriptionTv.topMargin

    @get:ColorInt
    var iconColor: Int = getColor(R.color.info_view_icon_color)
        set(@ColorInt value) {
            field = value
            icon = icon
        }

    @get:ColorInt
    var titleTextColor: Int
        set(@ColorInt value) { titleTv.setTextColor(value) }
        get() = titleTv.currentTextColor

    @get:ColorInt
    var descriptionTextColor: Int
        set(@ColorInt value) { descriptionTv.setTextColor(value) }
        get() = descriptionTv.currentTextColor

    var titleTextSize: Float
        set(value) { titleTv.setTextSizeInPx(value) }
        get() = titleTv.textSize

    var descriptionTextSize: Float
        set(value) { descriptionTv.setTextSizeInPx(value) }
        get() = descriptionTv.textSize

    var titleTextTypeface: Typeface
        set(value) { titleTv.typeface = value }
        get() = titleTv.typeface

    var descriptionTextTypeface: Typeface
        set(value) { descriptionTv.typeface = value }
        get() = descriptionTv.typeface

    var titleText: CharSequence
        set(value) { titleTv.text = value}
        get() = titleTv.text

    var descriptionText: CharSequence
        set(value) {
            isDescriptionTextVisible = value.isNotBlank()
            descriptionTv.text = value
        }
        get() = descriptionTv.text

    var icon: Drawable?
        set(value) { iconIv.setImageDrawable(value?.setColor(iconColor)) }
        get() = iconIv.drawable


    init {
        orientation = VERTICAL
        gravity = Gravity.CENTER

        inflateView()

        attrs?.let { extractAttributes(it, defStyleAttr) }
    }


    private fun inflateView() {
        inflateView(
            layoutResourceId = R.layout.view_info,
            root = this,
            attachToRoot = true
        )
    }


    private fun extractAttributes(attrs: AttributeSet, defStyleAttr: Int) {
        context.withStyledAttributes(
            set = attrs,
            attrs = R.styleable.InfoView,
            defStyleAttr = defStyleAttr
        ) {
            iconSize = getDimensionPixelSize(R.styleable.InfoView_infoView_iconSize, iconSize)
            titleTextSize = getDimension(R.styleable.InfoView_infoView_titleTextSize, titleTextSize)
            descriptionTextSize = getDimension(R.styleable.InfoView_infoView_descriptionTextSize, descriptionTextSize)
            titleTextTopMargin = getDimensionPixelSize(R.styleable.InfoView_infoView_titleTextTopMargin, titleTextTopMargin)
            descriptionTextTopMargin = getDimensionPixelSize(R.styleable.InfoView_infoView_descriptionTextTopMargin, descriptionTextTopMargin)
            iconColor = getColor(R.styleable.InfoView_infoView_iconColor, iconColor)
            titleTextColor = getColor(R.styleable.InfoView_infoView_titleTextColor, titleTextColor)
            descriptionTextColor = getColor(R.styleable.InfoView_infoView_descriptionTextColor, descriptionTextColor)
            titleTextTypeface = getFont(context, R.styleable.InfoView_infoView_titleTextFont, titleTextTypeface)
            descriptionTextTypeface = getFont(context, R.styleable.InfoView_infoView_descriptionTextFont, descriptionTextTypeface)
            icon = getDrawable(R.styleable.InfoView_infoView_icon)
            titleText = getString(R.styleable.InfoView_infoView_titleText, titleText)
            descriptionText = getString(R.styleable.InfoView_infoView_descriptionText, descriptionText)
        }
    }


}