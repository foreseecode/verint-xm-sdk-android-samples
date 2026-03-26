package com.verint.xm.rulesenginesample

import android.app.Activity
import android.app.Application
import android.content.Intent
import com.verint.xm.sdk.Core

class RulesEngineSampleApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        // Notify ForeSee SDK of application start
        Core.setDebugLogEnabled(true)

        // Set this to true to use the "draft" container, or "false" to use the "live" container.
        var debugBuild = false;

        if (debugBuild) {
            // Set the container to "draft" to use the draft configuration.
            // This allows you to quickly test changes to the configuration without affecting production users.
            // If you don't set this, the SDK will use the "live" container by default.
            Core.setConfigurationContainer("draft")
        }

        // Start using a site key. This can be changed to any site key that has been set up in DJ Studio
        Core.startWithSiteKey(this, "mobsdk-rulesenginesample")
    }
}

fun Activity.popToRootActivity() {
    val intent = Intent(this, MainActivity::class.java)
    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
    startActivity(intent)
}