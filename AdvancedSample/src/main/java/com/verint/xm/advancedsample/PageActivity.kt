package com.verint.xm.advancedsample

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.verint.xm.sdk.SurveyManagement

class PageActivity : BaseActivity() {

    companion object {
        var pageId = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_page)

        if (pageId >= 3) {
            val button: Button = findViewById(R.id.nextButton)
            button.text = "Done"
            button.setOnClickListener {
                this.popToRootActivity()
            }
        }

        val textView: TextView = findViewById(R.id.pageViewCountTextView)
        textView.text = "Page #: " + pageId++

        // action bar
        setupToolbar(showBackButton = true)
        supportActionBar?.title = "Page Views Sample"
    }

    override fun onResume() {
        super.onResume()

        SurveyManagement.checkIfEligibleForSurvey()
    }

    @Suppress("UNUSED_VARIABLE")
    fun onNextClicked(@Suppress("UNUSED_PARAMETER") view: View) {
        // Invite will be triggered after 3 page views
        val intent = Intent(this, PageActivity::class.java)
        startActivity(intent)
    }
}
