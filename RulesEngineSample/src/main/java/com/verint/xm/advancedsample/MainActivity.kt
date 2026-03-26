package com.verint.xm.rulesenginesample

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.verint.xm.sdk.Core
import com.verint.xm.sdk.StoryEngine

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)

        setupToolbar()
    }

    fun resetCounters(@Suppress("UNUSED_PARAMETER") view: View) {
        // Reset the state of the ForeSee SDK
        Core.resetState()
    }

    fun onPageViewsClicked(@Suppress("UNUSED_PARAMETER") view: View) {
        Core.resetState()

        PageActivity.pageId = 1
        // Invite will be triggered after 3 page views
        val intent = Intent(this, PageActivity::class.java)
        startActivity(intent)
    }

    fun onLaunchCountClicked(@Suppress("UNUSED_PARAMETER") view: View) {
        Core.resetState()

        // Invite will be triggered after 5 launch count
        val intent = Intent(this, LaunchCountActivity::class.java)
        startActivity(intent)
    }

    fun onPropertyClicked(@Suppress("UNUSED_PARAMETER") view: View) {
        Core.resetState()

        // Invite will be triggered after 5 launch count
        val intent = Intent(this, PropertyActivity::class.java)
        startActivity(intent)
    }

    fun onManualSurveyClicked(@Suppress("UNUSED_PARAMETER") view: View) {
        StoryEngine.runStory("manual_survey")
    }
}
