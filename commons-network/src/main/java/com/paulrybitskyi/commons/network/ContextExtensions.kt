/*
 * Copyright 2020 Paul Rybitskyi, oss@paulrybitskyi.com
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

@file:JvmName("ContextUtils")

package com.paulrybitskyi.commons.network

import android.Manifest
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities.TRANSPORT_CELLULAR
import android.net.NetworkCapabilities.TRANSPORT_ETHERNET
import android.net.NetworkCapabilities.TRANSPORT_WIFI
import android.net.NetworkRequest
import androidx.annotation.RequiresPermission
import com.paulrybitskyi.commons.SdkInfo
import com.paulrybitskyi.commons.ktx.getSystemService
import com.paulrybitskyi.commons.network.model.NetworkInfo
import com.paulrybitskyi.commons.network.model.NetworkType
import com.paulrybitskyi.commons.network.utils.LegacyNetworkTypeProvider
import com.paulrybitskyi.commons.network.utils.NetworkCallback
import com.paulrybitskyi.commons.network.utils.NetworkListener
import com.paulrybitskyi.commons.network.utils.NetworkTypeProvider
import com.paulrybitskyi.commons.network.utils.NewNetworkTypeProvider

@get:Suppress("DEPRECATION")
@get:RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
val Context.isConnectedToNetwork: Boolean
    get() = (getSystemService<ConnectivityManager>().activeNetworkInfo?.isConnected == true)

@get:RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
val Context.isNetworkConnectionMetered: Boolean
    get() = getSystemService<ConnectivityManager>().isActiveNetworkMetered

@get:RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
val Context.activeNetworkType: NetworkType
    get() = when {
        !isConnectedToNetwork -> NetworkType.UNDEFINED
        else -> networkTypeProvider.getActiveNetworkType()
    }

@get:RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
val Context.networkTypeProvider: NetworkTypeProvider
    get() = getSystemService<ConnectivityManager>()
        .let { connectivityManager ->
            if (SdkInfo.IS_AT_LEAST_MARSHMALLOW) {
                NewNetworkTypeProvider(connectivityManager)
            } else {
                LegacyNetworkTypeProvider(connectivityManager)
            }
        }

@get:RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
val Context.networkInfo: NetworkInfo
    get() = NetworkInfo(
        isConnectedToNetwork = isConnectedToNetwork,
        isNetworkConnectionMetered = isNetworkConnectionMetered,
        activeNetworkType = activeNetworkType
    )

@RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
fun Context.registerNetworkListener(
    networkListener: NetworkListener
): ConnectivityManager.NetworkCallback {
    return registerNetworkListener(
        networkRequest = buildDefaultNetworkRequest(),
        networkListener = networkListener
    )
}

@RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
fun Context.registerNetworkListener(
    networkRequest: NetworkRequest,
    networkListener: NetworkListener
): ConnectivityManager.NetworkCallback {
    val networkCallback = NetworkCallback(
        networkTypeProvider = networkTypeProvider,
        listener = networkListener
    )

    return getSystemService<ConnectivityManager>()
        .apply { registerNetworkCallback(networkRequest, networkCallback) }
        .let { networkCallback }
}

@RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
inline fun Context.registerNetworkListener(
    crossinline onNetworkConnected: (NetworkType) -> Unit = {},
    crossinline onNetworkDisconnected: (NetworkType) -> Unit = {}
): ConnectivityManager.NetworkCallback {
    return object : NetworkListener {
        override fun onNetworkConnected(networkType: NetworkType) = onNetworkConnected(networkType)
        override fun onNetworkDisconnected(networkType: NetworkType) = onNetworkDisconnected(networkType)
    }
    .let(::registerNetworkListener)
}

private fun buildDefaultNetworkRequest(): NetworkRequest {
    return NetworkRequest.Builder()
        .addTransportType(TRANSPORT_WIFI)
        .addTransportType(TRANSPORT_CELLULAR)
        .addTransportType(TRANSPORT_ETHERNET)
        .build()
}
