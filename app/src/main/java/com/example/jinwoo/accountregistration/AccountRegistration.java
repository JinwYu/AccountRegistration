package com.example.jinwoo.accountregistration;

import android.content.Context;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Vector;

/**
 * TODO: SKRIV OM KLASSEN HÄR JAVADOC
 */
public class AccountRegistration extends LinearLayout {
    // View variables.
    private LinearLayout linearLayout;

    // Account data variables.
    private String[] accountData;
    private int nrfields;
    private int counterMandatoryFields = 0;
    private boolean allMandatoryFieldsFilled = false;
//    private boolean[] inputComplete;
//    private boolean[] isMandatory;

    public AccountRegistration(Context context, int theNrfields) {
        super(context);
        nrfields = theNrfields;
        init();
    }

    /* 5. Sist i accountregistraton sätter du en register knapp.
     */
    private void init() {
        // Linear layout parameters. All of the fields will be added to this linear layout.
        linearLayout = new LinearLayout(getContext());
        ViewGroup.LayoutParams linearLayoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        linearLayout.setLayoutParams(linearLayoutParams);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setHorizontalGravity(Gravity.CENTER);

        accountData = new String[nrfields]; // The String with the account information.
    }
    public LinearLayout getAccountRegistrationForm(){return this.linearLayout;}

    // TODO: Add "*" for mandatory fields.
    public void addField(String label, boolean mandatory){
        // Create a new field.
        RegistrationField registrationField = new RegistrationField(getContext(), label, mandatory);
        // Add it to the linear layout.
        linearLayout.addView(registrationField);
        if(mandatory) counterMandatoryFields++;
    }

    public void addEmailField(boolean mandatory){
        RegistrationField emailField = new RegistrationField(getContext(), "Email", mandatory);
        linearLayout.addView(emailField);
        if(mandatory) counterMandatoryFields++;
    }

    public void addPasswordField(boolean mandatory){
        RegistrationField passwordField = new RegistrationField(getContext(), "Password", mandatory);
        linearLayout.addView(passwordField);
        if(mandatory) counterMandatoryFields++;
    }

    public void addBirthField(boolean isMandatory){
        // Parse string to ints.
    }

    public void addRegisterButton(){
        Button registerButton = new Button(getContext());
        registerButton.setText("Register");

        // If the register button is clicked.
        registerButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
//                registerUser();
                int nrMandatoryFieldsFilled = 0;

                // Loop through all of the fields and get the input
                // and save it if all of the mandatory fields are true.
                for(int i = 0; i < nrfields; i++){
                    RegistrationField tempRegField = ((RegistrationField) linearLayout.getChildAt(i));
                    if(tempRegField.getInputComplete()){
                        nrMandatoryFieldsFilled++;
                    }

                    if(nrMandatoryFieldsFilled == counterMandatoryFields){
                        // Save account information.
                        for(int k = 0; k < nrfields; k++){
                            RegistrationField tempField = ((RegistrationField) linearLayout.getChildAt(k));
                            EditText tempEditText = (EditText) tempField.findViewById(R.id.editText);
                            TextView tempTextView = (TextView) tempField.findViewById(R.id.textView);
                            System.out.println(tempEditText.getText().toString());

                            // TODO: Get the text from the TextView and use as a key for the information?

                            // Save the text from the EditText field.
                            accountData[k] = tempEditText.getText().toString();
                            System.out.println("accountData[" + k + "] = " + accountData[k]);
                        }
                    }
                }
            }
        });

        linearLayout.addView(registerButton);
    }

    public void registerUser(){


    }
}
