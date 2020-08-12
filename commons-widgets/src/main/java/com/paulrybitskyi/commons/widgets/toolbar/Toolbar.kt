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

package com.paulrybitskyi.commons.widgets.toolbar

import android.content.Context
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.view.ViewPropertyAnimator
import android.widget.FrameLayout
import androidx.annotation.ColorInt
import androidx.core.content.withStyledAttributes
import androidx.core.view.isVisible
import androidx.core.view.setPadding
import com.paulrybitskyi.commons.ktx.*
import com.paulrybitskyi.commons.ktx.views.setTextSizeInPx
import com.paulrybitskyi.commons.utils.observeChanges
import com.paulrybitskyi.commons.widgets.R
import com.paulrybitskyi.commons.widgets.toolbar.TitleGravity.Companion.asTitleGravity
import com.paulrybitskyi.commons.widgets.toolbar.configs.ButtonConfig
import com.paulrybitskyi.commons.widgets.toolbar.configs.TitleConfig
import kotlinx.android.synthetic.main.view_toolbar.view.*
import kotlin.math.max

class Toolbar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {


    private val defaultToolbarHeight = getDimensionPixelSize(R.dimen.toolbar_height)
    private val defaultButtonConfig = ButtonConfig(
        buttonContainerSize = getDimensionPixelSize(R.dimen.toolbar_button_container_size),
        buttonIconSize = getDimensionPixelSize(R.dimen.toolbar_button_icon_size),
        buttonIconPadding = getDimensionPixelSize(R.dimen.toolbar_button_icon_padding)
    )
    private val defaultTitleConfig = TitleConfig(
        horizontalPaddingWithoutIcon = getDimensionPixelSize(R.dimen.toolbar_title_horizontal_padding_without_icon),
        horizontalPaddingWithIcon = getDimensionPixelSize(R.dimen.toolbar_title_horizontal_padding_with_icon)
    )

    var isLeftButtonVisible: Boolean
        set(value) {
            leftBtnContainer.isVisible = value
            onLeftButtonVisibilityChanged()
        }
        get() = leftBtnContainer.isVisible

    var isRightButtonVisible: Boolean
        set(value) {
            rightBtnContainer.isVisible = value
            onRightButtonVisibilityChanged()
        }
        get() = rightBtnContainer.isVisible

    var isExtraRightButtonVisible: Boolean
        set(value) {
            extraRightBtnContainer.isVisible = value
            onRightButtonVisibilityChanged()
        }
        get() = extraRightBtnContainer.isVisible

    private val areBothRightButtonsVisible: Boolean
        get() = (isRightButtonVisible && isExtraRightButtonVisible)

    @get:ColorInt
    var buttonIconColor: Int = getColor(R.color.toolbar_button_icon_color)
        set(@ColorInt value) {
            field = value
            leftButtonIcon = leftButtonIcon
            extraRightButtonIcon = extraRightButtonIcon
            rightButtonIcon = rightButtonIcon
        }

    @get:ColorInt
    var buttonRippleColor: Int = getColor(R.color.toolbar_button_ripple_color)
        set(@ColorInt value) {
            field = value
            leftBtnIv.background = leftBtnIv.background?.setColor(value)
            rightBtnIv.background = rightBtnIv.background?.setColor(value)
            extraRightBtnIv.background = extraRightBtnIv.background?.setColor(value)
        }

    @get:ColorInt
    var titleTextColor: Int = getColor(R.color.toolbar_title_text_color)
        set(@ColorInt value) {
            field = value
            titleTv.setTextColor(value)
        }

    var titleTextSize: Float
        set(value) { titleTv.setTextSizeInPx(value) }
        get() = titleTv.textSize

    /**
     * A field to access the text gravity of the title.
     *
     * Note: When both right buttons are visible, setting any other value except
     * TitleGravity.LEFT will throw an exception.
     *
     * @see TitleGravity
     */
    var titleTextGravity by observeChanges(TitleGravity.CENTER) { _, newValue ->
        checkNewTitleGravity(newValue)
        titleTv.gravity = (newValue.gravity or Gravity.CENTER_VERTICAL)
        updateTitleHorizontalPadding()
    }

    var titleTextTypeface: Typeface
        set(value) { titleTv.typeface = value }
        get() = titleTv.typeface

    var titleText: CharSequence
        set(value) { titleTv.text = value }
        get() = titleTv.text

    var leftButtonIcon: Drawable?
        set(value) {
            leftBtnIv.setImageDrawable(value?.setColor(buttonIconColor))
            isLeftButtonVisible = (value != null)
        }
        get() = leftBtnIv.drawable

    var rightButtonIcon: Drawable?
        set(value) {
            rightBtnIv.setImageDrawable(value?.setColor(buttonIconColor))
            isRightButtonVisible = (value != null)
        }
        get() = rightBtnIv.drawable

    var extraRightButtonIcon: Drawable?
        set(value) {
            extraRightBtnIv.setImageDrawable(value?.setColor(buttonIconColor))
            isExtraRightButtonVisible = (value != null)
        }
        get() = extraRightBtnIv.drawable

    val leftButtonAnimator: ViewPropertyAnimator
        get() = extraRightBtnIv.animate()

    val rightButtonAnimator: ViewPropertyAnimator
        get() = rightBtnIv.animate()

    val extraRightButtonAnimator: ViewPropertyAnimator
        get() = rightBtnIv.animate()

    val titleAnimator: ViewPropertyAnimator
        get() = titleTv.animate()

    var buttonConfig by observeChanges(defaultButtonConfig) { _, newValue ->
        onButtonConfigChanged(newValue)
    }

    var titleConfig by observeChanges(defaultTitleConfig) { _, _ ->
        onTitleConfigChanged()
    }

    private val buttonInfos = mutableListOf<ButtonInfo>()

    var onLeftButtonClickListener: ((View) -> Unit)? = null
        set(value) {
            field = value
            leftBtnContainer.onClick { field?.invoke(it) }
        }

    var onRightButtonClickListener: ((View) -> Unit)? = null
        set(value) {
            field = value
            rightBtnContainer.onClick { field?.invoke(it) }
        }

    var onExtraRightButtonClickListener: ((View) -> Unit)? = null
        set(value) {
            field = value
            extraRightBtnContainer.onClick { field?.invoke(it) }
        }


    init {
        elevation = getDimension(R.dimen.toolbar_elevation)

        inflateView()
        initButtonInfos()
        initDefaults()

        attrs?.let { extractAttributes(it, defStyleAttr) }
    }


    private fun inflateView() {
        inflateView(
            layoutResourceId = R.layout.view_toolbar,
            root = this,
            attachToRoot = true
        )
    }


    private fun initButtonInfos() {
        buttonInfos.apply {
            add(ButtonInfo(
                containerView = leftBtnContainer,
                iconView = leftBtnIv
            ))
            add(ButtonInfo(
                containerView = rightBtnContainer,
                iconView = rightBtnIv
            ))
            add(ButtonInfo(
                containerView = extraRightBtnContainer,
                iconView = extraRightBtnIv
            ))
        }
    }


    private fun initDefaults() {
        setBackgroundColor(getColor(R.color.toolbar_background_color))
        buttonRippleColor = buttonRippleColor
        leftButtonIcon = leftButtonIcon
        rightButtonIcon = rightButtonIcon
        extraRightButtonIcon = extraRightButtonIcon
        titleTextGravity = titleTextGravity
        buttonConfig = buttonConfig
        titleConfig = titleConfig
    }


    private fun extractAttributes(attrs: AttributeSet, defStyleAttr: Int) {
        context.withStyledAttributes(
            set = attrs,
            attrs = R.styleable.CustomToolbar,
            defStyleAttr = defStyleAttr
        ) {
            buttonIconColor = getColor(R.styleable.CustomToolbar_toolbar_buttonIconColor, buttonIconColor)
            buttonRippleColor = getColor(R.styleable.CustomToolbar_toolbar_buttonRippleColor, buttonRippleColor)
            leftButtonIcon = getDrawable(R.styleable.CustomToolbar_toolbar_leftButtonIcon)
            rightButtonIcon = getDrawable(R.styleable.CustomToolbar_toolbar_rightButtonIcon)
            extraRightButtonIcon = getDrawable(R.styleable.CustomToolbar_toolbar_extraRightButtonIcon)
            titleText = getString(R.styleable.CustomToolbar_toolbar_titleText, titleText)
            titleTextColor = getColor(R.styleable.CustomToolbar_toolbar_titleTextColor, titleTextColor)
            titleTextSize = getDimension(R.styleable.CustomToolbar_toolbar_titleTextSize, titleTextSize)
            titleTextTypeface = getFont(context, R.styleable.CustomToolbar_toolbar_titleTextFont, titleTextTypeface)
            titleTextGravity = getInt(R.styleable.CustomToolbar_toolbar_titleTextGravity, titleTextGravity.id).asTitleGravity()
        }
    }


    private fun onLeftButtonVisibilityChanged() {
        updateTitleHorizontalPadding()
    }


    private fun onRightButtonVisibilityChanged() {
        updateExtraRightButtonContainerPosition()
        updateTitleHorizontalPadding()
        updateTitleGravity()
    }


    private fun updateExtraRightButtonContainerPosition() {
        if(!isExtraRightButtonVisible) return

        if(isRightButtonVisible) {
            extraRightBtnContainer.endMargin = buttonConfig.buttonContainerSize
        } else {
            extraRightBtnContainer.clearEndMargin()
        }
    }


    private fun updateTitleHorizontalPadding() {
        val leftPadding = calculateTitleLeftPadding()
        val rightPadding = calculateTitleRightPadding()

        if(titleTextGravity == TitleGravity.CENTER) {
            titleTv.setHorizontalPadding(max(leftPadding, rightPadding))
        } else {
            titleTv.updatePadding(
                startPadding = leftPadding,
                endPadding = rightPadding
            )
        }
    }


    private fun calculateTitleLeftPadding(): Int {
        return if(isLeftButtonVisible) {
            (buttonConfig.buttonContainerSize + titleConfig.horizontalPaddingWithIcon)
        } else {
            titleConfig.horizontalPaddingWithoutIcon
        }
    }


    private fun calculateTitleRightPadding(): Int {
        var totalPadding = 0
        val buttonContainerSize = buttonConfig.buttonContainerSize

        if(isRightButtonVisible) {
            totalPadding += buttonContainerSize
        }

        if(isExtraRightButtonVisible) {
            totalPadding += buttonContainerSize
        }

        totalPadding += if(isRightButtonVisible || isExtraRightButtonVisible) {
            titleConfig.horizontalPaddingWithIcon
        } else {
            titleConfig.horizontalPaddingWithoutIcon
        }

        return totalPadding
    }


    private fun updateTitleGravity() {
        if(!areBothRightButtonsVisible) return

        // When both right buttons are visible, resetting
        // the gravity to the LEFT value to avoid bad UI
        titleTextGravity = TitleGravity.LEFT
    }


    private fun checkNewTitleGravity(newTitleGravity: TitleGravity) {
        val canNotAssignNewTitleGravity = (
            areBothRightButtonsVisible &&
            (newTitleGravity != TitleGravity.LEFT)
        )

        if(canNotAssignNewTitleGravity) {
            throw IllegalStateException(
                """
                Toolbar does not support setting any other title gravity
                except LEFT when both right buttons are visible.
                """.trimIndent()
            )
        }
    }


    private fun onButtonConfigChanged(newConfig: ButtonConfig) {
        buttonInfos.forEach {
            it.containerView.setLayoutParamsSize(newConfig.buttonContainerSize)
            it.iconView.setLayoutParamsSize(newConfig.buttonIconSize)
            it.iconView.setPadding(newConfig.buttonIconPadding)
        }

        updateExtraRightButtonContainerPosition()
        updateTitleHorizontalPadding()
    }


    private fun onTitleConfigChanged() {
        updateTitleHorizontalPadding()
    }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(
            widthMeasureSpec,
            MeasureSpec.makeMeasureSpec(
                resolveSize(calculateHeight(), heightMeasureSpec),
                MeasureSpec.EXACTLY
            )
        )
    }


    private fun calculateHeight(): Int {
        return (defaultToolbarHeight + verticalPadding)
    }


}