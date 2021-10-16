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

plugins {
    androidLibrary()
    kotlinAndroid()
}

android {
    compileSdkVersion(appConfig.compileSdkVersion)

    defaultConfig {
        minSdkVersion(appConfig.minSdkVersion)
        targetSdkVersion(appConfig.targetSdkVersion)

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    buildFeatures {
        viewBinding = true
    }

    compileOptions {
        sourceCompatibility = appConfig.javaCompatibilityVersion
        targetCompatibility = appConfig.javaCompatibilityVersion
    }

    kotlinOptions {
        jvmTarget = appConfig.kotlinCompatibilityVersion.toString()
    }
}

dependencies {
    implementation(project(deps.local.commons))
    implementation(project(deps.local.commonsKtx))

    implementation(deps.appCompat)
    implementation(deps.coreKtx)
}

publishingConfig.artifactName = publishingConfig.commonsWidgetsArtifactName
publishingConfig.artifactVersion = publishingConfig.commonsWidgetsArtifactVersion

apply(from = "../publishing.gradle.kts")
