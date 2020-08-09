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

package com.paulrybitskyi.commons.window.anims

import android.app.Activity


/**
 * Overrides the animations of the entering window.
 *
 * @param windowAnimations The animations to use for the entering window
 */
fun Activity.overrideEnterTransition(windowAnimations: WindowAnimations) {
    if(windowAnimations.id == WindowAnimations.DEFAULT_ANIMATIONS.id) {
        return
    }

    overridePendingTransition(
        windowAnimations.windowBEnterAnimation,
        windowAnimations.windowAExitAnimation
    )
}


/**
 * Overrides the animations of the exiting window.
 *
 * @param windowAnimations The animations to use for the exiting window
 */
fun Activity.overrideExitTransition(windowAnimations: WindowAnimations) {
    if(windowAnimations.id == WindowAnimations.DEFAULT_ANIMATIONS.id) {
        return
    }

    overridePendingTransition(
        windowAnimations.windowAEnterAnimation,
        windowAnimations.windowBExitAnimation
    )
}