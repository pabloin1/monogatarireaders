package com.monogatari.app.core.data.local.network

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import com.monogatari.app.core.utils.network.NetworkUtils

object NetworkManager {

    private lateinit var networkUtils: NetworkUtils

    fun init(context: Context) {
        networkUtils = NetworkUtils(context)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun isOnline(): Boolean {
        return networkUtils.isOnline()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun observeNetworkState() = networkUtils.observeNetworkState()
}