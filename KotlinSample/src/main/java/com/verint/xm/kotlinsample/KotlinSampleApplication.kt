package com.verint.xm.kotlinsample

import android.app.Application
import android.util.Log
import com.verint.xm.sdk.Core

class KotlinSampleApplication : Application(), Core.VerintSDKListener {

    override fun onCreate() {
        super.onCreate()
        // Enable debug logs (not typically used in production, but helpful
        // when testing)
        Core.setDebugLogEnabled(true)

        // Set a VerintSDKListener (used to determine when the SDK has started (or if
        // it fails to start)
        Core.setSDKListener(this)

        // Set the configuration container. This defaults to "live" and only
        // needs to be supplied when testing with other containers (e.g. "draft").
        Core.setConfigurationContainer("live")

        // Start the SDK with this app's site key
        Core.startWithSiteKey(this, "mobsdk-basicsample")
    }

    // Core.VerintSDKListener

    override fun onSDKStarted() {
        Log.i("VerintSDKListener", "onSDKStarted")
    }

    override fun onSDKStarted(verintError: Core.VerintError, errorDescription: String) {
        Log.i(
            "VerintSDKListener",
            "onSDKStarted with error: " + verintError.name + ": " + errorDescription
        )
    }

    override fun onSDKFailedToStart(verintError: Core.VerintError, errorDescription: String) {
        Log.i(
            "VerintSDKListener",
            "onSDKFailedToStart with error: " + verintError.name + ": " + errorDescription)
    }
}

class MyVerintSDKListener : Core.VerintSDKListener {
    override fun onSDKStarted() {
        Log.i("VerintSDKListener", "onSDKStarted")
    }

    override fun onSDKStarted(verintError: Core.VerintError, errorDescription: String) {
        Log.i(
            "VerintSDKListener",
            "onSDKStarted with error: " + verintError.name + ": " + errorDescription
        )
    }

    override fun onSDKFailedToStart(verintError: Core.VerintError, errorDescription: String) {
        Log.i(
            "VerintSDKListener",
            "onSDKFailedToStart with error: " + verintError.name + ": " + errorDescription)
    }
}