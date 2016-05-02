package com.foresee.demo.custominvite;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.foresee.sdk.ForeSee;
import com.foresee.sdk.cxMeasure.tracker.listeners.IDefaultInviteListener;

public class DefaultInviteActivity extends AppCompatActivity {

    private static final String TAG = "DefaultInviteActivity";

    private EditText contactField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_default_invite);

        contactField = (EditText)findViewById(R.id.contactField);
        if (ForeSee.getContactDetails() != null) {
            contactField.setText(ForeSee.getContactDetails());
        }

        //Set Listeners (optional)
        ForeSee.setInviteListener(new IDefaultInviteListener() {
            @Override
            public void onInvitePresented() {
                Log.d(TAG, "onInvitePresented");
                Toast.makeText(getApplicationContext(), "Invite presented", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onInviteCompleteWithAccept() {
                Log.d(TAG, "onInviteAccepted");
                Toast.makeText(getApplicationContext(), "A survey will be sent to " + ForeSee.getContactDetails(), Toast.LENGTH_SHORT).show();

                //Reset
                ForeSee.resetState();
            }

            @Override
            public void onInviteCompleteWithDecline() {
                Log.d(TAG, "onInviteDeclined");
                Toast.makeText(getApplicationContext(), "Invitation declined by user", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSurveyPresented() {
                Log.d(TAG, "onSurveyPresented");
                Toast.makeText(getApplicationContext(), "Survey presented", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSurveyCompleted() {
                Log.d(TAG, "onSurveyCompleted");
                Toast.makeText(getApplicationContext(), "Survey completed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSurveyCancelled() {
                Log.d(TAG, "onSurveyCancelled");
                Toast.makeText(getApplicationContext(), "Survey cancelled", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onInviteCancelledWithNetworkError() {
                Log.d(TAG, "onInviteCancelledWithNetworkError");
                Toast.makeText(getApplicationContext(), "Invitation cancelled with network error", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onInviteNotShownWithNetworkError() {
                Log.d(TAG, "onInviteNotShownWithNetworkError");
                Toast.makeText(getApplicationContext(), "Invitation not shown with network error", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onInviteNotShownWithEligibilityFailed() {
                Log.d(TAG, "onInviteNotShownWithEligibilityFailed");
                Toast.makeText(getApplicationContext(), "Invitation not shown with eligibility failed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onInviteNotShownWithSamplingFailed() {
                Log.d(TAG, "onInviteNotShownWithSamplingFailed");
                Toast.makeText(getApplicationContext(), "Invitation not shown with sampling failed", Toast.LENGTH_SHORT).show();
            }
        });

    }


    public void launchDefaultInvite(View view)
    {
        // Launch an invite as a demo
        ForeSee.showInviteForSurveyID("app_test_1");
    }

    public void launchDefaultInviteWithContactDetails(View view)
    {
        // Launch an invite as a demo
        ForeSee.setContactDetails(contactField.getText().toString());
        ForeSee.showInviteForSurveyID("app_test_1");
    }
}
