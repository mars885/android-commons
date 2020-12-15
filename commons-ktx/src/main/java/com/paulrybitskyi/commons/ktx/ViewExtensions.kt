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

@file:JvmName("ViewUtils")
@file:Suppress("UNCHECKED_CAST")

package com.paulrybitskyi.commons.ktx

import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.*
import androidx.core.view.*
import com.paulrybitskyi.commons.utils.observeChanges
import kotlin.properties.ReadWriteProperty


val View.hasLayoutParams: Boolean
    get() = (layoutParams != null)

var View.layoutParamsWidth: Int
    set(value) { setLayoutParamsSize(width = value) }
    get() = (layoutParams?.width ?: 0)

var View.layoutParamsHeight: Int
    set(value) { setLayoutParamsSize(height = value) }
    get() = (layoutParams?.height ?: 0)

@get:Px
var View.startMargin: Int
    set(@Px value) { setMargins(startMargin = value) }
    get() = marginStart

@get:Px
var View.topMargin: Int
    set(@Px value) { setMargins(topMargin = value) }
    get() = marginTop

@get:Px
var View.endMargin: Int
    set(@Px value) { setMargins(endMargin = value) }
    get() = marginEnd

@get:Px
var View.bottomMargin: Int
    set(@Px value) { setMargins(bottomMargin = value) }
    get() = marginBottom

@get:Px
val View.horizontalMargin: Int
    get() = (startMargin + endMargin)

@get:Px
val View.verticalMargin: Int
    get() = (topMargin + bottomMargin)

@get:Px
var View.startPadding: Int
    set(@Px value) { updatePadding(startPadding = value) }
    get() = paddingStart

@get:Px
var View.topPadding: Int
    set(@Px value) { updatePadding(topPadding = value) }
    get() = paddingTop

@get:Px
var View.endPadding: Int
    set(@Px value) { updatePadding(endPadding = value) }
    get() = paddingEnd

@get:Px
var View.bottomPadding: Int
    set(@Px value) { updatePadding(bottomPadding = value) }
    get() = paddingBottom

@get:Px
val View.horizontalPadding: Int
    get() = (startPadding + endPadding)

@get:Px
val View.verticalPadding: Int
    get() = (topPadding + bottomPadding)


fun View.setLayoutParamsSize(size: Int) {
    setLayoutParamsSize(width = size, height = size)
}


fun View.setLayoutParamsSize(
    width: Int = layoutParamsWidth,
    height: Int = layoutParamsHeight
) {
    if(!hasLayoutParams) return

    updateLayoutParams {
        this.width = width
        this.height = height
    }
}


fun View.setMargins(
    @Px startMargin: Int = this.startMargin,
    @Px topMargin: Int = this.topMargin,
    @Px endMargin: Int = this.endMargin,
    @Px bottomMargin: Int = this.bottomMargin
) {
    if(layoutParams !is ViewGroup.MarginLayoutParams) {
        return
    }

    updateLayoutParams<ViewGroup.MarginLayoutParams> {
        this.marginStart = startMargin
        this.topMargin = topMargin
        this.marginEnd = endMargin
        this.bottomMargin = bottomMargin
    }
}


fun View.setHorizontalMargin(@Px margin: Int) {
    setMargins(startMargin = margin, endMargin = margin)
}


fun View.setVerticalMargin(@Px margin: Int) {
    setMargins(topMargin = margin, bottomMargin = margin)
}


fun View.clearMargins() {
    setMargins(
        startMargin = 0,
        topMargin = 0,
        endMargin = 0,
        bottomMargin = 0
    )
}


fun View.clearHorizontalMargins() {
    setHorizontalMargin(0)
}


fun View.clearVerticalMargins() {
    setVerticalMargin(0)
}


fun View.clearStartMargin() {
    startMargin = 0
}


fun View.clearTopMargin() {
    topMargin = 0
}


fun View.clearEndMargin() {
    endMargin = 0
}


fun View.clearBottomMargin() {
    bottomMargin = 0
}


fun View.updatePadding(
    @Px startPadding: Int = this.paddingStart,
    @Px topPadding: Int = this.paddingTop,
    @Px endPadding: Int = this.paddingEnd,
    @Px bottomPadding: Int = this.paddingBottom
) {
    setPaddingRelative(startPadding, topPadding, endPadding, bottomPadding)
}


fun View.setHorizontalPadding(@Px padding: Int) {
    updatePadding(startPadding = padding, endPadding = padding)
}


fun View.setVerticalPadding(@Px padding: Int) {
    updatePadding(topPadding = padding, bottomPadding = padding)
}


fun View.clearPadding() {
    updatePadding(
        startPadding = 0,
        topPadding = 0,
        endPadding = 0,
        bottomPadding = 0
    )
}


fun View.clearHorizontalPadding() {
    setHorizontalPadding(0)
}


fun View.clearVerticalPadding() {
    setVerticalPadding(0)
}


fun View.clearStartPadding() {
    startPadding = 0
}


fun View.clearTopPadding() {
    topPadding = 0
}


fun View.clearEndPadding() {
    endPadding = 0
}


fun View.clearBottomPadding() {
    bottomPadding = 0
}


fun <T : ViewGroup.LayoutParams> View.toLayoutParams(): T {
    return (layoutParams as T)
}


fun View.getColor(@ColorRes colorId: Int): Int {
    return context.getCompatColor(colorId)
}


fun View.getDimensionPixelSize(@DimenRes dimenId: Int): Int {
    return context.getDimensionPixelSize(dimenId)
}


fun View.getDimension(@DimenRes dimenId: Int): Float {
    return context.getDimension(dimenId)
}


fun View.getString(@StringRes stringId: Int): String {
    return context.getString(stringId)
}


fun View.getString(@StringRes stringId: Int, vararg args: Any): String {
    return context.getString(stringId, *args)
}


fun View.getDrawable(@DrawableRes drawableId: Int): Drawable? {
    return context.getCompatDrawable(drawableId)
}


fun View.getColoredDrawable(@DrawableRes drawableId: Int, @ColorInt color: Int): Drawable? {
    return context.getColoredDrawable(drawableId, color)
}


fun View.getColoredStrokeDrawable(
    @DrawableRes drawableId: Int,
    @ColorInt strokeColor: Int,
    strokeWidth: Int
): Drawable? {
    return context.getCompatDrawable(drawableId)
        ?.run { mutate() as? GradientDrawable }
        ?.apply { setStroke(strokeWidth, strokeColor) }
}


fun View.inflateView(
    @LayoutRes layoutResourceId: Int,
    root: ViewGroup?,
    attachToRoot: Boolean = true
): View {
    return context.inflateView(
        layoutResourceId = layoutResourceId,
        root = root,
        attachToRoot = attachToRoot
    )
}


fun View.showShortToast(message: CharSequence): Toast {
    return context.showShortToast(message)
}


fun View.showLongToast(message: CharSequence): Toast {
    return context.showLongToast(message)
}


fun View.makeVisible() {
    isVisible = true
}


fun View.makeInvisible() {
    isInvisible = true
}


fun View.makeGone() {
    isGone = true
}


fun View.removeElevation() {
    elevation = 0f
}


fun View.onClick(action: (View) -> Unit) {
    setOnClickListener(action)
}


/**
 * Enables the view by setting its [View.isEnabled] property
 * to true and, optionally, changing its alpha.
 *
 * @param changeAlpha Whether to change the alpha of the view.
 * Default is false.
 * @param alpha The new alpha value for the view if [changeAlpha]
 * parameter is true. Default is 0.5f.
 * @param childrenToo Whether to enable children as well
 * Default is false.
 */
fun View.enable(
    changeAlpha: Boolean = false,
    alpha: Float = 1f,
    childrenToo: Boolean = false
) {
    if(!isEnabled) {
        isEnabled = true

        if(changeAlpha) {
            setAlpha(alpha)
        }

        if(childrenToo && (this is ViewGroup)) {
            for(child in children) {
                child.enable(changeAlpha, alpha, childrenToo)
            }
        }
    }
}


/**
 * Disables the view by setting its [View.isEnabled] property
 * to false and, optionally, changing its alpha.
 *
 * @param changeAlpha Whether to change the alpha of the view.
 * Default is false.
 * @param alpha The new alpha value for the view if [changeAlpha]
 * parameter is true. Default is 0.5f.
 * @param childrenToo Whether to disable children as well.
 * Default is false.
 */
fun View.disable(
    changeAlpha: Boolean = false,
    alpha: Float = 0.5f,
    childrenToo: Boolean = false
) {
    if(isEnabled) {
        isEnabled = false

        if(changeAlpha) {
            setAlpha(alpha)
        }

        if(childrenToo && (this is ViewGroup)) {
            for(child in children) {
                child.disable(changeAlpha, alpha, childrenToo)
            }
        }
    }
}


/**
 * Sets the horizontal and vertical scale of the view.
 *
 * @param scale The new scale
 */
fun View.setScale(scale: Float) {
    scaleX = scale
    scaleY = scale
}


fun View.postAction(action: () -> Unit) {
    post(action)
}


fun View.postActionDelayed(timeInMillis: Long, action: () -> Unit) {
    postDelayed(action, timeInMillis)
}


fun <T> View.invalidateOnChange(initialValue: T): ReadWriteProperty<Any, T> {
    return observeChanges(initialValue) {  _, _ -> invalidate() }
}


fun <T> View.relayoutOnChange(initialValue: T): ReadWriteProperty<Any, T> {
    return observeChanges(initialValue) {  _, _ -> requestLayout() }
}