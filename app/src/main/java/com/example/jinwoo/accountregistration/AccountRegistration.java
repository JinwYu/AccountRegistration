package com.example.jinwoo.accountregistration;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.Hashtable;

/**
 * AccountRegistration is a class that creates a registration form.
 * The user can create new fields and choose the text on the left of
 * a field. When an object of this class is created, the user has to
 * decide how many fields the form will have. The used can also choose
 * if a field is mandatory or not. When all of the fields are created
 * the user has to create a register button by using the function
 * "addRegisterButton". When the register button is clicked all of the
 * data filled by the user will be saved in a Hashtable.
 *
 * @author Jinwoo Yu
 * @version 2016.05.22
 */
public class AccountRegistration extends LinearLayout {
    // View variables.
    private LinearLayout linearLayout;
    private TextView errorMessage;
    TextView registrationSuccessful;

    // Account data variables.
    private Hashtable<String, String> allAccountData;
    private Hashtable<String, Integer> numericAccountData;
    private int nrfields;
    private int counterMandatoryFields = 0;

    // Misc variables.
    private mandatoryFieldsListener listener;

    public AccountRegistration(Context context, int theNrfields) {
        super(context);
        nrfields = theNrfields + 1; // Plus one is for the TextView for the error message at the top of the linear layout.
        init();
    }

    public interface mandatoryFieldsListener {
        // When the account information can be saved.
        public void onAccountInformationSaved(Hashtable<String, String> accountInformation);
    }

    private void init() {
        // Linear layout parameters. All of the fields will be added to this linear layout.
        linearLayout = new LinearLayout(getContext());
        ViewGroup.LayoutParams linearLayoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        linearLayout.setLayoutParams(linearLayoutParams);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setHorizontalGravity(Gravity.CENTER);

        // Error message textView.
        errorMessage = new TextView(getContext());
        errorMessage.setGravity(Gravity.CENTER);
        linearLayout.addView(errorMessage);

        // To show that the registration was successful. Added to the view below the register button.
        registrationSuccessful = new TextView(getContext());
        registrationSuccessful.setGravity(Gravity.CENTER);

        allAccountData = new Hashtable<String, String>();
        numericAccountData = new Hashtable<String, Integer>();
    }

    public LinearLayout getAccountRegistrationForm(){return this.linearLayout;}

    /**
     * Get the account data saved from the filled form.
     * @return A Hashtable<String, String> with the account information.
     */
    public Hashtable getAccountData(){return this.allAccountData;}

    /**
     * Create a field. Choose the label and decide if it is a mandatory field.
     * @param label The label that will be displayed left of the EditText-field.
     * @param mandatory If the field is mandatory.
     */
    public void addField(String label, boolean mandatory){
        RegistrationField registrationField = new RegistrationField(getContext(), label, mandatory);
        linearLayout.addView(registrationField);
        // Keep track of how many mandatory fields there are.
        if(mandatory) counterMandatoryFields++;
    }

    /**
     * Create a field that will take a numerical input from the user for example "age".
     * @param label The label that will be displayed left of the EditText-field.
     * @param mandatory If the field is mandatory.
     */
    public void addNumericField(String label, boolean mandatory){
        RegistrationField numericRegistrationField = new RegistrationField(getContext(), label, mandatory, true);
        linearLayout.addView(numericRegistrationField);
        if(mandatory) counterMandatoryFields++;
    }

    /**
     * Create a field for email.
     * @param mandatory If the field is mandatory.
     */
    public void addEmailField(boolean mandatory){
        RegistrationField emailField = new RegistrationField(getContext(), "Email", mandatory);
        linearLayout.addView(emailField);
        if(mandatory) counterMandatoryFields++;
    }

    /**
     * Create a field for a password.
     * @param mandatory If the field is mandatory.
     */
    public void addPasswordField(boolean mandatory){
        RegistrationField passwordField = new RegistrationField(getContext(), "Password", mandatory);
        linearLayout.addView(passwordField);
        if(mandatory) counterMandatoryFields++;
    }

    /**
     * Create a button to register the account. When clicked on all of the data from the form
     * will be saved into a Hashtable.
     */
    public void addRegisterButton(){
        Button registerButton = new Button(getContext());
        registerButton.setText("Register");

        // If the register button is clicked.
        registerButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                int nrMandatoryFieldsFilled = 0;

                // Loop through all of the fields and get the input and
                // save the data if all of the mandatory fields are true.
                for (int i = 1; i < nrfields; i++) {
                    RegistrationField tempRegField = ((RegistrationField) linearLayout.getChildAt(i));

                    // If a mandatory input has been filled, increment the counter for nr of completed mandatory fields.
                    if (tempRegField.getInputComplete()) nrMandatoryFieldsFilled++;

                    // If all of the mandatory fields have been filled.
                    if (nrMandatoryFieldsFilled == counterMandatoryFields) {
                        // Save account information.
                        for (int k = 1; k < nrfields; k++) {
                            RegistrationField tempField = ((RegistrationField) linearLayout.getChildAt(k));
                            EditText tempEditText = (EditText) tempField.findViewById(R.id.editText);
                            TextView tempTextView = (TextView) tempField.findViewById(R.id.textView);
                            System.out.println(tempEditText.getText().toString());

                            // Save the text from the EditText field.
                            String key = tempTextView.getText().toString();
                            String data = tempEditText.getText().toString();
                            allAccountData.put(key, data);
                            System.out.println("accountData[" + key + "] = " + data);

                            // Keep track of which keys that store numeric data.
                            if (tempField.getIsNumeric() && tempEditText.getText().length() != 0) {
                                numericAccountData.put(key, parseNumericData(key));
                                System.out.println("numericAccountData[" + key + "] = " + parseNumericData(key));
                            }

                            // Fire the listener and send the account information.
                            if (listener != null) {
                                listener.onAccountInformationSaved(allAccountData);
                            }

                            // Update the message text fields.
                            errorMessage.setText("");   // Delete the error message.
                            registrationSuccessful.setText("The registration was successful, welcome!");
                            registrationSuccessful.setTextColor(Color.GREEN);
                        }
                    } else {
                        // Update the message text fields.
                        errorMessage.setText("Please fill the required fields.");
                        errorMessage.setTextColor(Color.RED);
                        registrationSuccessful.setText("The registration failed.");
                        registrationSuccessful.setTextColor(Color.RED);
                    }
                }
            }
        });

        // Add the TextView to show that a registration has failed/succeeded below the register button.
        linearLayout.addView(registerButton);
        linearLayout.addView(registrationSuccessful);
    }

    /**
     * Convert the String data to ints. Called from the listener in the function "addRegisterButton".
     * @param key The key for where the the String that will be parsed is in the Hashtabel for the account.
     * @return The integer value from the string.
     */
    public int parseNumericData(String key){
        int tempInteger = Integer.parseInt(allAccountData.get(key));
        return tempInteger;
    }

    /**
     * Saves the account information if all mandatory fields are filled.
     * @param listener
     */
    public void setMandatoryFieldsListener(mandatoryFieldsListener listener) {this.listener = listener;}
}
