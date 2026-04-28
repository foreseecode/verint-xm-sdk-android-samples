package com.verint.xm.rulesenginesample

import android.os.Bundle

class LaunchCountActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch_count)

        setupToolbar(showBackButton = true)
        supportActionBar?.title = "Launch Count Sample"
    }

    override fun onResume() {
        super.onResume()
    }
}
