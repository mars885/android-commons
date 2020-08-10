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

package com.paulrybitskyi.commons.device.info

import android.content.Context
import android.content.res.Configuration
import android.os.Build
import android.util.DisplayMetrics
import com.paulrybitskyi.commons.device.info.model.DeviceInfo
import com.paulrybitskyi.commons.device.info.model.ProductInfo
import com.paulrybitskyi.commons.device.info.model.screen.ScreenMetrics
import com.paulrybitskyi.commons.device.info.model.screen.ScreenScalingFactors
import com.paulrybitskyi.commons.device.info.model.screen.ScreenDensity.Companion.asScreenDensity
import com.paulrybitskyi.commons.device.info.model.screen.ScreenDimension
import com.paulrybitskyi.commons.device.info.model.screen.ScreenSizeCategory
import com.paulrybitskyi.commons.device.info.model.screen.ScreenSizeCategory.Companion.asScreenSizeCategory
import com.paulrybitskyi.commons.ktx.configuration
import com.paulrybitskyi.commons.ktx.displayMetrics
import com.paulrybitskyi.commons.ktx.pxToDp


val Context.deviceInfo: DeviceInfo
    get() = DeviceInfo(
        productInfo = productInfo,
        screenMetrics = screenMetrics
    )

val Context.productInfo: ProductInfo
    get() = ProductInfo(
        modelName = Build.MODEL,
        productName = Build.PRODUCT,
        manufacturerName = Build.MANUFACTURER
    )

val Context.screenMetrics: ScreenMetrics
    get() = ScreenMetrics(
        width = displayMetrics.getScreenWidth(this),
        height = displayMetrics.getScreenHeight(this),
        sizeCategory = configuration.getScreenSizeCategory(),
        density = displayMetrics.densityDpi.asScreenDensity(),
        scalingFactors = displayMetrics.getScreenScalingFactors(),
        smallestWidthInDp = configuration.smallestScreenWidthDp
    )


private fun DisplayMetrics.getScreenWidth(context: Context): ScreenDimension {
    return ScreenDimension(
        sizeInPixels = widthPixels,
        sizeInDp = widthPixels.toFloat().pxToDp(context)
    )
}


private fun DisplayMetrics.getScreenHeight(context: Context): ScreenDimension {
    return ScreenDimension(
        sizeInPixels = heightPixels,
        sizeInDp = heightPixels.toFloat().pxToDp(context)
    )
}


private fun DisplayMetrics.getScreenScalingFactors(): ScreenScalingFactors {
    return ScreenScalingFactors(
        pixelScalingFactor = density,
        textPixelScalingFactor = scaledDensity
    )
}


private fun Configuration.getScreenSizeCategory(): ScreenSizeCategory {
    return (screenLayout and Configuration.SCREENLAYOUT_SIZE_MASK).asScreenSizeCategory()
}