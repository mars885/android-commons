/*
 * Copyright 2021 Paul Rybitskyi, paul.rybitskyi.work@gmail.com
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

@file:Suppress("ClassName")

import org.gradle.api.JavaVersion

object appConfig {

    const val compileSdkVersion = 32
    const val targetSdkVersion = 32
    const val minSdkVersion = 21
    const val applicationId = "com.paulrybitskyi.commons.sample"

    val javaCompatibilityVersion = JavaVersion.VERSION_1_8
    val kotlinCompatibilityVersion = JavaVersion.VERSION_1_8
}

object versions {

    const val kotlin = "1.7.0" // also in buildSrc build.gradle.kts file
    const val gradlePlugin = "7.2.2" // also in buildSrc build.gradle.kts file
    const val detektPlugin = "1.20.0"
    const val ktlintPlugin = "10.3.0"
    const val gradleVersionsPlugin = "0.42.0"
    const val dokkaPlugin = "1.7.0"
    const val appCompat = "1.4.2"
    const val lifecycle = "2.5.1"
    const val constraintLayout = "2.1.4"
    const val viewPager2 = "1.0.0"
    const val coreKtx = "1.8.0"
    const val fragmentKtx = "1.5.1"
    const val recyclerView = "1.2.1"
    const val materialComponents = "1.6.1"
    const val navigation = "2.5.1"
}

object publishingConfig {

    const val artifactGroupId = "com.paulrybitskyi.commons"
    const val artifactWebsite = "https://github.com/mars885/android-commons"
    const val artifactDescription = "A part of Android libraries suite that contains common " +
        "functionality found in Android projects."

    const val mavenPublicationName = "release"

    const val licenseName = "The Apache Software License, Version 2.0"
    const val licenseUrl = "http://www.apache.org/licenses/LICENSE-2.0.txt"
    const val developerId = "mars885"
    const val developerName = "Paul Rybitskyi"
    const val developerEmail = "paul.rybitskyi.work@gmail.com"
    const val siteUrl = "https://github.com/mars885/android-commons"
    const val gitUrl = "https://github.com/mars885/android-commons.git"

    const val hostRepoName = "sonatype"
    const val hostRepoUrl = "https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/"

    // Artifact specifics
    const val commonsCoreArtifactName = "commons-core"
    const val commonsCoreArtifactVersion = "1.0.4"

    const val commonsDeviceInfoArtifactName = "commons-device-info"
    const val commonsDeviceInfoArtifactVersion = "1.0.2"

    const val commonsKtxArtifactName = "commons-ktx"
    const val commonsKtxArtifactVersion = "1.0.4"

    const val commonsListenersArtifactName = "commons-listeners"
    const val commonsListenersArtifactVersion = "1.0.3"

    const val commonsMaterialArtifactName = "commons-material"
    const val commonsMaterialArtifactVersion = "1.0.3"

    const val commonsNavigationArtifactName = "commons-navigation"
    const val commonsNavigationArtifactVersion = "1.0.3"

    const val commonsNetworkArtifactName = "commons-network"
    const val commonsNetworkArtifactVersion = "1.0.3"

    const val commonsRecyclerViewArtifactName = "commons-recyclerview"
    const val commonsRecyclerViewArtifactVersion = "1.0.2"

    const val commonsWidgetsArtifactName = "commons-widgets"
    const val commonsWidgetsArtifactVersion = "1.0.3"

    const val commonsWindowAnimsArtifactName = "commons-window-anims"
    const val commonsWindowAnimsArtifactVersion = "1.0.2"

    var artifactName = ""
    var artifactVersion = ""
}

object deps {

    object plugins {

        const val androidGradle = "com.android.tools.build:gradle:${versions.gradlePlugin}"
        const val kotlinGradle = "org.jetbrains.kotlin:kotlin-gradle-plugin:${versions.kotlin}"
        const val gradleVersions = "com.github.ben-manes:gradle-versions-plugin:${versions.gradleVersionsPlugin}"
        const val dokka = "org.jetbrains.dokka:dokka-gradle-plugin:${versions.dokkaPlugin}"
    }

    object local {

        const val commons = ":commons"
        const val commonsKtx = ":commons-ktx"
        const val commonsWindowAnims = ":commons-window-anims"
        const val commonsRecyclerView = ":commons-recyclerview"
        const val commonsMaterial = ":commons-material"
        const val commonsNavigation = ":commons-navigation"
        const val commonsDeviceInfo = ":commons-device-info"
        const val commonsNetwork = ":commons-network"
        const val commonsWidgets = ":commons-widgets"
        const val commonsListeners = ":commons-listeners"
    }

    const val appCompat = "androidx.appcompat:appcompat:${versions.appCompat}"
    const val lifecycleCommonJava8 = "androidx.lifecycle:lifecycle-common-java8:${versions.lifecycle}"
    const val lifecycleViewModel = "androidx.lifecycle:lifecycle-viewmodel:${versions.lifecycle}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${versions.constraintLayout}"
    const val viewPager2 = "androidx.viewpager2:viewpager2:${versions.viewPager2}"
    const val recyclerView = "androidx.recyclerview:recyclerview:${versions.recyclerView}"
    const val coreKtx = "androidx.core:core-ktx:${versions.coreKtx}"
    const val fragmentKtx = "androidx.fragment:fragment-ktx:${versions.fragmentKtx}"
    const val materialComponents = "com.google.android.material:material:${versions.materialComponents}"
    const val navigation = "androidx.navigation:navigation-fragment-ktx:${versions.navigation}"
}
