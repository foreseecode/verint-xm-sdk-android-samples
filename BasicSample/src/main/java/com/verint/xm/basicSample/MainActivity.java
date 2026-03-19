package com.verint.xm.basicSample;


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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
