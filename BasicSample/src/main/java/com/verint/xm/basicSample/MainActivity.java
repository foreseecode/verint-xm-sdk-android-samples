package com.verint.xm.basicSample;


import android.os.Build;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.util.TypedValue;
import android.view.View;

import com.verint.xm.sdk.Core;
import com.verint.xm.sdk.SurveyManagement;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
