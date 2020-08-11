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

package com.paulrybitskyi.commons.network.utils

import android.Manifest
import android.net.ConnectivityManager
import android.net.ConnectivityManager.*
import android.net.Network
import android.net.NetworkCapabilities.*
import android.net.NetworkInfo
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.annotation.RequiresPermission
import com.paulrybitskyi.commons.network.model.NetworkType


interface NetworkTypeProvider {

    fun getActiveNetworkType(): NetworkType

    fun getNetworkType(network: Network): NetworkType
    
}


internal class NewNetworkTypeProvider(
    private val connectivityManager: ConnectivityManager
) : NetworkTypeProvider {


    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    @RequiresApi(Build.VERSION_CODES.M)
    override fun getActiveNetworkType(): NetworkType {
        return connectivityManager.activeNetwork
            ?.run { toNetworkType() }
            ?: NetworkType.UNDEFINED
    }


    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    override fun getNetworkType(network: Network): NetworkType {
        return network.toNetworkType()
    }


    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    private fun Network.toNetworkType(): NetworkType {
        val capabilities = (connectivityManager.getNetworkCapabilities(this) ?: return NetworkType.UNDEFINED)

        return when {
            capabilities.hasTransport(TRANSPORT_WIFI) -> NetworkType.WIFI
            capabilities.hasTransport(TRANSPORT_CELLULAR) -> NetworkType.CELLULAR
            capabilities.hasTransport(TRANSPORT_ETHERNET) -> NetworkType.ETHERNET

            else -> NetworkType.UNDEFINED
        }
    }
    
    
}


internal class LegacyNetworkTypeProvider(
    private val connectivityManager: ConnectivityManager
) : NetworkTypeProvider {


    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    override fun getActiveNetworkType(): NetworkType {
        return connectivityManager.activeNetworkInfo
            ?.run { toNetworkType() }
            ?: NetworkType.UNDEFINED
    }


    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    override fun getNetworkType(network: Network): NetworkType {
        return connectivityManager.getNetworkInfo(network)
            ?.run { toNetworkType() }
            ?: NetworkType.UNDEFINED
    }


    private fun NetworkInfo.toNetworkType(): NetworkType {
        return when(type) {
            TYPE_WIFI -> NetworkType.WIFI
            TYPE_MOBILE -> NetworkType.CELLULAR
            TYPE_ETHERNET -> NetworkType.ETHERNET

            else -> NetworkType.UNDEFINED
        }
    }
    
    
}