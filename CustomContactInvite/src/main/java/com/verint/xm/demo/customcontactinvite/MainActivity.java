package com.verint.xm.demo.customcontactinvite;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.util.TypedValue;
import android.view.View;
import com.verint.xm.sdk.Core;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

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

    public void launchCustomInvite1Activity(View view) {
        startActivity(new Intent(this, CustomInvite1Activity.class));
    }

    public void launchCustomInvite2Activity(View view) {
        startActivity(new Intent(this, CustomInvite2Activity.class));
    }

    public void resetState(View view) {
        // Reset the state of the ForeSee SDK. So that we may be eligible for a new invite
        // based on the criteria in exp_configuration.json
        Core.resetState();
    }
}
