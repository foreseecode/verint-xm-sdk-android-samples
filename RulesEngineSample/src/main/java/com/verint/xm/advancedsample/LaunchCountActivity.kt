package com.verint.xm.advancedsample

import android.os.Bundle
import com.verint.xm.sdk.SurveyManagement

class LaunchCountActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch_count)

        setupToolbar(showBackButton = true)
        supportActionBar?.title = "Launch Count Sample"
    }

    override fun onResume() {
        super.onResume()

        SurveyManagement.checkIfEligibleForSurvey()
    }
}
