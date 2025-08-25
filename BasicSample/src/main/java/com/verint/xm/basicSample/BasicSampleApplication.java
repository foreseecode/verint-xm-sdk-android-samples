package com.verint.xm.basicSample;

import android.app.Application;
import android.util.Log;

import com.verint.xm.sdk.Core;

public class BasicSampleApplication extends Application implements Core.VerintSDKListener {
	@Override
	public void onCreate() {
		super.onCreate();
		// Enable debug logs (not typically used in production, but helpful
		// when testing)
		Core.setDebugLogEnabled(true);

		// Set a VerintSDKListener (used to determine when the SDK has started (or if
		// it fails to start)
		Core.setSDKListener(this);

		// Set the configuration container. This defaults to "live" and only
		// needs to be supplied when testing with other containers (e.g. "draft").
		Core.setConfigurationContainer("live");

		// Start the SDK with this app's site key
		Core.startWithSiteKey(this, "bca_app");
	}

	// Core.VerintSDKListener

	@Override
	public void onSDKStarted() {
		Log.i("VerintSDKListener", "onSDKStarted");
	}

	@Override
	public void onSDKStarted(Core.VerintError verintError, String errorDescription) {
		Log.i("VerintSDKListener", "onSDKStarted with error: " + verintError.name() + ": " + errorDescription);
	}

	@Override
	public void onSDKFailedToStart(Core.VerintError verintError, String errorDescription) {
		Log.i("VerintSDKListener", "onSDKFailedToStart with error: " + verintError.name() + ": " + errorDescription);
	}
}