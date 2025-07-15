package com.verint.xm.advancedsample

import android.app.Activity
import android.app.Application
import android.content.Intent
import android.util.Log
import com.verint.xm.advancedsample.ProductsActivity.Companion.TAG
import com.verint.xm.sdk.Core
import com.verint.xm.sdk.Core.VerintSDKListener

class AdvancedSampleApplication : Application(), VerintSDKListener {

    override fun onCreate() {
        super.onCreate()

        // Notify ForeSee SDK of application start
        Core.setDebugLogEnabled(true)
        Core.startWithSiteKey(this, "virginialotterymobileapp")
    }

    override fun onSDKStarted() {
        Log.d(TAG, "onSDKStarted")
    }

    override fun onSDKStarted(p0: Core.VerintError?, p1: String?) {
        Log.d(TAG, "onSDKStarted (with error)")
    }

    override fun onSDKFailedToStart(p0: Core.VerintError?, p1: String?) {
        Log.d(TAG, "onSDKFailedToStart")
    }
}

fun Activity.popToRootActivity() {
    val intent = Intent(this, MainActivity::class.java)
    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
    startActivity(intent)
}