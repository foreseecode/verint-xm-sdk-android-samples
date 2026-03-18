package com.verint.xm.contactSurvey;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.util.TypedValue;
import android.view.View;

import com.verint.xm.sdk.Core;
import com.verint.xm.sdk.SurveyManagement;


public class MainActivity extends AppCompatActivity {
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // Do normal UI setup
        setContentView(R.layout.main);

        // Apply actionBarSize padding for Android 15+
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.VANILLA_ICE_CREAM) {
            View rootView = findViewById(R.id.main_layout);
            TypedValue tv = new TypedValue();
            if (getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
                int actionBarSize = TypedValue.complexToDimensionPixelSize(tv.data, getResources().getDisplayMetrics());
                rootView.setPadding(
                        rootView.getPaddingLeft(),
                        actionBarSize,
                        rootView.getPaddingRight(),
                        rootView.getPaddingBottom()
                );
            }
        }

        // Back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Launch an invite as a demo if we're eligible
        SurveyManagement.checkIfEligibleForSurvey();
    }

    public void launchInvite(View view) {
        // Increment the significant event count so that we're eligible for an invite
        // based on the criteria in exp_configuration.json
        SurveyManagement.incrementSignificantEventCountWithKey("instant_invite");

        // Launch an invite as a demo if we're eligible
        SurveyManagement.checkIfEligibleForSurvey();
    }

    public void resetCounters(View view) {
    	// Reset the state of the ForeSee SDK
    	Core.resetState();
    }

    public void setContactDetails(View view) {
        startActivity(new Intent(this, SetContactDetailsActivity.class));
    }
}
