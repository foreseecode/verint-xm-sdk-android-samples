package com.verint.xm.rulesenginesample

import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.verint.xm.sdk.StoryEngine

class PropertyActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_property)

        StoryEngine.setIntProperty("custom_property", 0)

        // action bar
        setupToolbar(showBackButton = true)
        supportActionBar?.title = "Property Sample"
    }

    fun onIncrementClicked(@Suppress("UNUSED_PARAMETER") view: View) {
        // Increment the property count so that we're eligible for an invite
        // based on the criteria in the configuration
        var propertyCount = StoryEngine.getProperty("custom_property") as Int
        propertyCount += 1
        StoryEngine.setIntProperty("custom_property", propertyCount)

        val textView: TextView = findViewById(R.id.propertyTextView)
        textView.text = "Custom property value = " + StoryEngine.getProperty("custom_property") as Int
    }
}
