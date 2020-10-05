package com.chinmay.githubapidemo.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.util.Log
import androidx.lifecycle.LiveData

class NetworkConnection(context: Context) : LiveData<NetWorkConnectionType>() {
    private var connectivityManager: ConnectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    init {
        val activeNetwork = connectivityManager.activeNetwork
        val networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork)
        //4
        if (networkCapabilities != null &&
            networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        ) {
            Log.d("mytag", "internet is available")
        } else {
            Log.d("mytag", "internet not available")
        }
    }


    private lateinit var networkCallback: ConnectivityManager.NetworkCallback

    init {
        initConnectivityManagerCallback()
    }

    override fun onActive() {
        connectivityManager.registerNetworkCallback(
            NetworkRequest.Builder().build(),
            networkCallback
        )
    }

    override fun onInactive() {
        connectivityManager.unregisterNetworkCallback(networkCallback)
    }

    private fun initConnectivityManagerCallback() {
        networkCallback = object : ConnectivityManager.NetworkCallback() {
            override fun onLost(network: Network) {
                super.onLost(network)
                postValue(NetWorkConnectionType.NONE)
            }

            override fun onAvailable(network: Network) {
                super.onAvailable(network)
                val nw: Network? = connectivityManager.activeNetwork
                val actNw = connectivityManager.getNetworkCapabilities(nw)
                actNw?.let {
                    var isCellular =
                        actNw.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) && actNw.hasTransport(
                            NetworkCapabilities.TRANSPORT_CELLULAR
                        )
                    if (isCellular) {
                        postValue(NetWorkConnectionType.CELLULAR)
                    } else {
                        postValue(NetWorkConnectionType.WIFI)
                    }
                }


            }

        }
    }


//    private fun isNetworkAvailable(context: Context) {
//        val nw: Network? = connectivityManager.activeNetwork
//        if (nw == null) {
//            postValue(NetWorkConnectionType.NONE)
//            return
//        }
//
//        val actNw = connectivityManager.getNetworkCapabilities(nw)
//        if (actNw == null) {
////            postValue("NetWork not capable 4")
//            return
//        }
//        if (actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) or actNw.hasTransport(
//                NetworkCapabilities.TRANSPORT_CELLULAR
//            )
//            or actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) or actNw.hasTransport(
//                NetworkCapabilities.TRANSPORT_BLUETOOTH
//            )
//        ) {
////            postValue("NetWork Available 5")
//        } else {
////            postValue("Network Lost 6")
//
//
//        }
//    }

}

enum class NetWorkConnectionType {
    NONE,
    CELLULAR,
    WIFI
}