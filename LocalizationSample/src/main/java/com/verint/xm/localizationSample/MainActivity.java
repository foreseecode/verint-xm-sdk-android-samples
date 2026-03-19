package com.verint.xm.localizationSample;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.view.View;

import com.verint.xm.sdk.Core;
import com.verint.xm.sdk.SurveyManagement;

public class MainActivity extends AppCompatActivity {
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // Do normal UI setup
        setContentView(R.layout.main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        // Toolbar is used as the ActionBar to show the activity title
        setSupportActionBar(toolbar);
        // Expand the Toolbar top padding to equal the status bar height, so the Toolbar background fills behind the status bar.
        // Required by API 35+.
        ViewCompat.setOnApplyWindowInsetsListener(toolbar, (v, windowInsets) -> {
            Insets statusBar = windowInsets.getInsets(WindowInsetsCompat.Type.statusBars());
            v.setPadding(v.getPaddingLeft(), statusBar.top,
                    v.getPaddingRight(), v.getPaddingBottom());
            return windowInsets;
        });
     }

    public void launchInvite(View view)
    {
        // Increment the significant event count so that we're eligible for an invite
        // based on the criteria in exp_configuration.json
        SurveyManagement.incrementSignificantEventCountWithKey("instant_invite");

        // Launch an invite as a demo
        SurveyManagement.checkIfEligibleForSurvey();
    }

    public void resetState(View view) {
        // Reset the state of the ForeSee SDK
        Core.resetState();
    }
}
