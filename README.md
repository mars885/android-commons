# Android Commons
A suite of Android libraries that contain common functionality I use in projects.

![](https://img.shields.io/badge/API-21%2B-orange.svg?style=flat)
[![Platform](https://img.shields.io/badge/platform-Android-green.svg)](http://developer.android.com/index.html)
[![Build](https://github.com/mars885/android-commons/workflows/Build/badge.svg?branch=master)](https://github.com/mars885/android-commons/actions)
![](https://travis-ci.org/mars885/android-commons.svg?branch=master)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

## Contents
* [commons-core](https://github.com/mars885/android-commons/tree/master/commons/src/main/java/com/paulrybitskyi/commons)
* [commons-ktx](https://github.com/mars885/android-commons/tree/master/commons-ktx/src/main/java/com/paulrybitskyi/commons/ktx)
* [commons-window-anims](https://github.com/mars885/android-commons/tree/master/commons-window-anims/src/main/java/com/paulrybitskyi/commons/window/anims)
* [commons-recyclerview](https://github.com/mars885/android-commons/tree/master/commons-recyclerview/src/main/java/com/paulrybitskyi/commons/recyclerview)
* [commons-material](https://github.com/mars885/android-commons/tree/master/commons-material/src/main/java/com/paulrybitskyi/commons/material)
* [commons-navigation](https://github.com/mars885/android-commons/tree/master/commons-navigation/src/main/java/com/paulrybitskyi/commons/navigation)
* [commons-device-info](https://github.com/mars885/android-commons/tree/master/commons-device-info/src/main/java/com/paulrybitskyi/commons/device/info)
* [commons-network](https://github.com/mars885/android-commons/tree/master/commons-network/src/main/java/com/paulrybitskyi/commons/network)
* [commons-widgets](https://github.com/mars885/android-commons/tree/master/commons-widgets/src/main/java/com/paulrybitskyi/commons/widgets)
* [commons-listeners](https://github.com/mars885/android-commons/tree/master/commons-listeners/src/main/java/com/paulrybitskyi/commons/listeners)

## Installation
1. Make sure that you've added the `jcenter()` repository to your top-level `build.gradle` file.

````groovy
buildscript {
    //...
    repositories {
        //...
        jcenter()
    }
    //...
}
````

2. Add the specific library dependency to your module-level `build.gradle` file.

````groovy
dependencies {
    //...
    implementation "com.paulrybitskyi.commons:commons-core:1.0.1"
    implementation "com.paulrybitskyi.commons:commons-ktx:1.0.1"
    implementation "com.paulrybitskyi.commons:commons-window-anims:1.0.0"
    implementation "com.paulrybitskyi.commons:commons-recyclerview:1.0.0"
    implementation "com.paulrybitskyi.commons:commons-material:1.0.1"
    implementation "com.paulrybitskyi.commons:commons-navigation:1.0.1"
    implementation "com.paulrybitskyi.commons:commons-device-info:1.0.0"
    implementation "com.paulrybitskyi.commons:commons-network:1.0.0"
    implementation "com.paulrybitskyi.commons:commons-widgets:1.0.1"
    implementation "com.paulrybitskyi.commons:commons-listeners:1.0.0"
    //...
}
````

## License

Android-Commons is licensed under the [Apache 2.0 License](LICENSE).