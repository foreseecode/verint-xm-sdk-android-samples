package com.verint.xm.contactSurvey;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.verint.xm.sdk.SurveyManagement;
import com.verint.xm.sdk.common.configuration.ContactType;

/**
 * Created by alan.wang on 11/20/18.
 */

public class SetContactDetailsActivity extends BaseActivity {
    // Variables
    private EditText contactInfoEmail;
    private EditText contactInfoPhone;
    private RadioGroup preferredContactType;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Do normal UI setup
        setContentView(R.layout.contact_details);

        setupToolbar(true);

        // Find UI components
        contactInfoEmail = (EditText)findViewById(R.id.contactInfoEmail);
        contactInfoPhone = (EditText)findViewById(R.id.contactInfoPhoneNumber);
        preferredContactType = (RadioGroup)findViewById(R.id.preferredContactType);
    }

    /**
     * Dispatch onResume() to fragments.  Note that for better inter-operation
     * with older versions of the platform, at the point of this call the
     * fragments attached to the activity are <em>not</em> resumed.  This means
     * that in some cases the previous state may still be saved, not allowing
     * fragment transactions that modify the state.  To correctly interact
     * with fragments in their proper state, you should instead override
     * {@link #onResumeFragments()}.
     */
    @Override
    protected void onResume() {
        super.onResume();

        // Setup UI components
        contactInfoEmail.setText(SurveyManagement.getContactDetails(ContactType.Email));
        contactInfoPhone.setText(SurveyManagement.getContactDetails(ContactType.PhoneNumber));
        ContactType type = SurveyManagement.getPreferredContactType();
        if (type != null) {
            switch (type) {
                case Email:
                    preferredContactType.check(R.id.preferredContactTypeEmail);
                    break;
                case PhoneNumber:
                    preferredContactType.check(R.id.preferredContactTypePhoneNumber);
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * Take care of popping the fragment back stack or finishing the activity
     * as appropriate.
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();

        SurveyManagement.setContactDetails(ContactType.Email,
                contactInfoEmail.getText().toString());
        SurveyManagement.setContactDetails(ContactType.PhoneNumber,
                contactInfoPhone.getText().toString());

        int radioButtonId = preferredContactType.getCheckedRadioButtonId();
        if (radioButtonId == R.id.preferredContactTypeEmail) {
            SurveyManagement.setPreferredContactType(ContactType.Email);
        } else if (radioButtonId == R.id.preferredContactTypePhoneNumber) {
            SurveyManagement.setPreferredContactType(ContactType.PhoneNumber);
        }

    }

}
