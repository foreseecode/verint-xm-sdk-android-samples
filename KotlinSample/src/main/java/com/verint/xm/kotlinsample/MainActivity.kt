package com.verint.xm.kotlinsample

import android.os.Build
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.verint.xm.sdk.Core
import com.verint.xm.sdk.SurveyManagement

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)


        // Apply actionBarSize padding for Android 15+
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.VANILLA_ICE_CREAM) {
            val rootView = findViewById<View?>(R.id.main_layout)
            val tv = TypedValue()
            if (getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
                val actionBarSize = TypedValue.complexToDimensionPixelSize(
                    tv.data,
                    getResources().getDisplayMetrics()
                )
                rootView.setPadding(
                    rootView.getPaddingLeft(),
                    actionBarSize,
                    rootView.getPaddingRight(),
                    rootView.getPaddingBottom()
                )
            }
        }
    }


    fun checkEligibility(@Suppress("UNUSED_PARAMETER") view: View) {
        // Show a survey invitation to eligible users
        SurveyManagement.checkIfEligibleForSurvey()
    }

    fun resetCounters(@Suppress("UNUSED_PARAMETER") view: View) {
        // Reset the state of the SDK (for example after showing an invite, so that
        // the user is eligible to see another one). You wouldn't typically do this
        // in a production app, but it's useful when testing.
        Core.resetState()
    }
}
