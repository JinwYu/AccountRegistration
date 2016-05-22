package com.example.jinwoo.accountregistration;

import android.content.Context;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * RegistrationField is a class that creates a single field for a registration form.
 * The user can decide what label the field will have. The user can create also create
 * a field for a password, email or numeric values. The fields can be set to be mandatory.
 *
 * @author Jinwoo Yu
 * @version 2016.05.22
 */
public class RegistrationField extends LinearLayout {
    private TextView textView;
    private EditText editText;
    private boolean isMandatory;
    private boolean inputComplete;
    boolean isEmail;
    boolean isPassword;
    boolean isNumeric;

    public RegistrationField(Context context, String label, boolean mandatory) {
        super(context);
        isMandatory = mandatory;
        if(mandatory) label = label + "*";  // If it's a mandatory field then add the "*".
        isNumeric = false;
        init(label);
    }

    public RegistrationField(Context context, String label, boolean mandatory, boolean numeric){
        super(context);
        isNumeric = numeric;
        isMandatory = mandatory;
        if(mandatory) label = label + "*";
        init(label);
    }

    private void init(String label){
        // Inflate registration_line.xml.
        LayoutInflater inflater = (LayoutInflater)
                getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.registration_line, this);

        // The different components from the file "password_layout.xml".
        editText = (EditText)findViewById(R.id.editText);
        textView = (TextView) findViewById(R.id.textView);

        // Set the label
        textView.setText(label);

        // If it's a EditText for a password, email or if it's numeric.
        if(label.equals("Password" )|| label.equals("Password*")){
            editText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            isPassword = true;
        }
        else if(label.equals("Email")|| label.equals("Email*")){
            editText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS | InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
            isEmail = true;
        }
        else if(isNumeric){
            editText.setInputType(InputType.TYPE_CLASS_NUMBER);
        }

        // Listeners for the EditText.
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // If text field.
                if(!isNumeric){
                    // If the field has received the correct input.
                    if (s.length() > 0 && isMandatory && !isNumeric) {
                        inputComplete = true;

                        // Check if it's an email.
                        if (isEmail && s.toString().matches(".*[@].*")) {
                            inputComplete = true;
                        } else {
                            inputComplete = false;
                        }
                        // Check if it's a password.
                        if (isPassword) {
                            inputComplete = true;
                        }
//                    else {
//                        inputComplete = false;
//                    }
                    } else {
                        inputComplete = false;
                    }
                }

                // If numeric field.
                if(isNumeric){
                    if( s.length() > 0 && isNumeric && isMandatory){
                        inputComplete = true;
                    }
                    else if(s.length() == 0){
                        inputComplete = false;
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    /**
     * Returns the boolean that says if the mandatory field has been filled.
     * @return boolean "inputComplete" that tells if a mandatory field has been filled.
     */
    public boolean getInputComplete(){return this.inputComplete;}

    /**
     * Returns the boolean that says if a field takes a numeric value.
     * @return boolean "isNumeric" that tells if a field takes a numeric value.
     */
    public boolean getIsNumeric(){return this.isNumeric;}
}