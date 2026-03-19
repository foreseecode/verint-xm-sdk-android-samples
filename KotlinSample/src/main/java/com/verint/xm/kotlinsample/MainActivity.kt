package com.verint.xm.kotlinsample

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.graphics.Insets
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.verint.xm.sdk.Core
import com.verint.xm.sdk.SurveyManagement

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        // Toolbar is used as the ActionBar to show the activity title
        setSupportActionBar(toolbar)
        // Expand the Toolbar top padding to equal the status bar height, so the Toolbar background fills behind the status bar.
        // Required by API 35+.
        ViewCompat.setOnApplyWindowInsetsListener(toolbar) { v, windowInsets ->
            val statusBar = windowInsets.getInsets(WindowInsetsCompat.Type.statusBars())
            v.setPadding(v.paddingLeft, statusBar.top, v.paddingRight, v.paddingBottom)
            windowInsets
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
