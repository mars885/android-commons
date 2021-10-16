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

package com.paulrybitskyi.commons

import android.os.Build

object SdkInfo {

    @JvmField val SDK_VERSION = Build.VERSION.SDK_INT

    @JvmField val IS_AT_LEAST_KITKAT = (SDK_VERSION >= Build.VERSION_CODES.KITKAT)
    @JvmField val IS_AT_LEAST_LOLLIPOP = (SDK_VERSION >= Build.VERSION_CODES.LOLLIPOP)
    @JvmField val IS_AT_LEAST_LOLLIPOP_MR1 = (SDK_VERSION >= Build.VERSION_CODES.LOLLIPOP_MR1)
    @JvmField val IS_AT_LEAST_MARSHMALLOW = (SDK_VERSION >= Build.VERSION_CODES.M)
    @JvmField val IS_AT_LEAST_NOUGAT = (SDK_VERSION >= Build.VERSION_CODES.N)
    @JvmField val IS_AT_LEAST_NOUGAT_MR1 = (SDK_VERSION >= Build.VERSION_CODES.N_MR1)
    @JvmField val IS_AT_LEAST_OREO = (SDK_VERSION >= Build.VERSION_CODES.O)
    @JvmField val IS_AT_LEAST_OREO_MR1 = (SDK_VERSION >= Build.VERSION_CODES.O_MR1)
    @JvmField val IS_AT_LEAST_PIE = (SDK_VERSION >= Build.VERSION_CODES.P)
    @JvmField val IS_AT_LEAST_10 = (SDK_VERSION >= Build.VERSION_CODES.Q)
    @JvmField val IS_AT_LEAST_11 = (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R)

}
