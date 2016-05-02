package com.foresee.demo.custominvite;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.internal.view.ContextThemeWrapper;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.foresee.sdk.ForeSee;
import com.foresee.sdk.cxMeasure.tracker.listeners.IContactInviteResultListener;
import com.foresee.sdk.cxMeasure.tracker.listeners.ICustomContactInviteListener;

import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class CustomInvite2Activity extends AppCompatActivity {

    private static final String TAG = "CustomInvite2Activity";

    private Snackbar snackbarInvite;
    private Timer snackBarTimer;
    private Timer snackBarUpdateTimer;
    private int snackBarLifetime;
    private ProgressDialog progressDialog;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_invite_2);
    }

    public void launchCustomInvite2(View view) {

        ForeSee.setInviteListener(new ICustomContactInviteListener() {
            @Override
            public void showInvite(final IContactInviteResultListener iContactInviteResultListener) {
                Log.d(TAG, "showInvite");

                snackbarInvite = Snackbar.make(findViewById(R.id.coordinator_layout), getSnackbarMessage(), Snackbar.LENGTH_INDEFINITE);
                snackbarInvite.setAction("OK, sure", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showProgress();

                        iContactInviteResultListener.contactInviteAccepted();
                    }
                });

                setSnackBarUpdateTimers();

                // Every way of exiting the invite should result in a call to iContactInviteResultListener.contactInviteAccepted() or iContactInviteResultListener.contactInviteDeclined();
                // having accurate numbers on accepts and declines helps us track the success rate of your invite strategy

                // In this case, the Snackbar will dismiss after 15 seconds so we need to call iContactInviteResultListener.contactInviteDeclined() when it does
                snackbarInvite.setCallback(new Snackbar.Callback() {
                    @Override
                    public void onDismissed(Snackbar snackbar, int event) {
                        super.onDismissed(snackbar, event);
                        if (event == Snackbar.Callback.DISMISS_EVENT_TIMEOUT
                                || event == Snackbar.Callback.DISMISS_EVENT_MANUAL
                                || event == Snackbar.Callback.DISMISS_EVENT_CONSECUTIVE
                                || event == Snackbar.Callback.DISMISS_EVENT_SWIPE) {

                            showProgress();

                            // Call the iContactInviteResultListener.contactInviteDeclined() method whenever the custom invite is dismissed
                            iContactInviteResultListener.contactInviteDeclined();
                        }
                    }
                });

                snackbarInvite.show();
            }

            @Override
            public void onContactFormatError(IContactInviteResultListener iContactInviteResultListener) {
                Log.d(TAG, "onContactFormatError");

                hideProgress();

                showInputDialog("Please ensure your contact details", getApplicationContext().getString(R.string.FORESEE_invalidFormat), iContactInviteResultListener);
            }

            @Override
            public void onContactMissing(IContactInviteResultListener iContactInviteResultListener) {
                Log.d(TAG, "onContactMissing");

                hideProgress();

                showInputDialog("Please enter some contact details", null, iContactInviteResultListener);
            }

            @Override
            public void onInviteCompleteWithAccept() {
                Log.d(TAG, "onCompleteWithAccept");
                // By this point the SDK is finished with the invite process, this is for information only
                Toast.makeText(getApplicationContext(), "A survey will be sent to " + ForeSee.getContactDetails(), Toast.LENGTH_SHORT).show();

                hideProgress();

                //Reset
                ForeSee.resetState();
            }

            @Override
            public void onInviteCompleteWithDecline() {
                Log.d(TAG, "onCompleteWithDecline");
                Toast.makeText(getApplicationContext(), "Invitation declined by user", Toast.LENGTH_SHORT).show();

                hideProgress();
            }

            @Override
            public void onCancelledWithNetworkError() {
                Log.d(TAG, "onCancelledWithNetworkError");
                Toast.makeText(getApplicationContext(), "Invitation cancelled with network error", Toast.LENGTH_SHORT).show();

                hideProgress();
            }

            @Override
            public void onInviteNotShownWithNetworkError() {
                Log.d(TAG, "onInviteNotShownWithNetworkError");
                Toast.makeText(getApplicationContext(), "Invitation not shown with network error", Toast.LENGTH_SHORT).show();

                hideProgress();
            }

            @Override
            public void onInviteNotShownWithEligibilityFailed() {
                Log.d(TAG, "onInviteNotShownWithEligibilityFailed");
                Toast.makeText(getApplicationContext(), "Invitation not shown with eligibility failed", Toast.LENGTH_SHORT).show();

                hideProgress();
            }

            @Override
            public void onInviteNotShownWithSamplingFailed() {
                Log.d(TAG, "onInviteNotShownWithSamplingFailed");
                Toast.makeText(getApplicationContext(), "Invitation not shown with sampling failed", Toast.LENGTH_SHORT).show();

                hideProgress();
            }
        });


        // Launch an invite as a demo
        ForeSee.showInviteForSurveyID("app_test_1");
    }


    private void setSnackBarUpdateTimers()
    {
        if (snackBarTimer != null) {
            snackBarTimer.cancel();
        }
        if (snackBarUpdateTimer != null) {
            snackBarUpdateTimer.cancel();
        }

        snackBarLifetime = 15;

        snackBarTimer = new Timer();
        snackBarTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                snackBarUpdateTimer.cancel();
                snackbarInvite.dismiss();
            }
        }, snackBarLifetime * 1000);

        snackBarUpdateTimer = new Timer();
        snackBarUpdateTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        snackBarLifetime--;
                        snackbarInvite.setText(getSnackbarMessage());
                    }
                });
            }
        }, 0, 1000);
    }


    private String getSnackbarMessage()
    {
        String snackbarMessage = "Would you like to take a survey?\nThis popup will close in %d second%s";
        return String.format(Locale.US, snackbarMessage, snackBarLifetime, snackBarLifetime > 1 ? "s" : "");
    }

    private void showProgress()
    {
        if (progressDialog == null || (progressDialog != null && !progressDialog.isShowing())) {
            progressDialog = ProgressDialog.show(this, "", "Please wait...", true, true);
        }
    }

    private void hideProgress()
    {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    private void showInputDialog(String messageText, String errorMessage, final IContactInviteResultListener iContactInviteResultListener)
    {
        AlertDialog.Builder builder  = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.CustomTheme_Dialog));
        View                rootView = getLayoutInflater().inflate(R.layout.dialog_contact, null);
        builder.setView(rootView);
        builder.setTitle("Contact details");

        final TextView        messageView = (TextView) rootView.findViewById(R.id.message);
        final TextInputLayout inputLayout = (TextInputLayout) rootView.findViewById(R.id.contactInputLayout);
        final EditText        input       = (EditText) rootView.findViewById(R.id.contactInput);

        messageView.setText(messageText);

        if (iContactInviteResultListener.getContactDetails() != null) {
            input.setText(iContactInviteResultListener.getContactDetails());
        }

        if (errorMessage != null) {
            inputLayout.setError(errorMessage);
        } else {
            inputLayout.setErrorEnabled(false);
        }

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ForeSee.setContactDetails(input.getText().toString());
                showProgress();
                iContactInviteResultListener.contactInviteAccepted();

            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                showProgress();
                iContactInviteResultListener.contactInviteDeclined();

            }
        });

        builder.show();

    }

}
