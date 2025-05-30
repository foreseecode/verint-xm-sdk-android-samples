package com.verint.xm.basicSample;


import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;

import com.verint.xm.sdk.Core;
import com.verint.xm.sdk.SurveyManagement;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onCheckEligibilityClicked(View view) {
        // Show a survey invitation to eligible users
        SurveyManagement.checkIfEligibleForSurvey();
    }

    public void resetCounters(View view) {
        // Reset the state of the SDK (for example after showing an invite, so that
        // the user is eligible to see another one). You wouldn't typically do this
        // in a production app, but it's useful when testing.
        Core.resetState();
    }
}
