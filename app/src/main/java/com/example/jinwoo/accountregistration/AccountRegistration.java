package com.example.jinwoo.accountregistration;

import android.content.Context;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.Gravity;
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
    private LinearLayout fieldContainer;    // The layout that will include all of the registration fields.
    ViewGroup.LayoutParams linearLayoutParams, layoutParamsWrap, layoutParamsMatch, layoutParams;   // TODO: hårdkodat

    // Account data variables.
    private String password, username, email, company, firstName, lastName, day, month, year, Gender;
    private Vector<Boolean> inputComplete;
    private Vector<Boolean> isMandatory;
    private int index = 0;

    /*
        TODO: TROR INDEX ÄR FEL, kolla print outsen.

        TODO: Getters för alla variabler.


     */


    public AccountRegistration(Context context) {
        super(context);
        init();
    }

    public AccountRegistration(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public AccountRegistration(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public AccountRegistration(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init(){
        password = "";
        username = "";
        email = "";
        company = "";
        firstName = "";
        lastName = "";
        day = "";
        month = "";
        year = "";

        inputComplete = new Vector<Boolean>();
        isMandatory = new Vector<Boolean>();

        // Layout parameters.
        linearLayoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        layoutParamsWrap = new ViewGroup.LayoutParams(270, ViewGroup.LayoutParams.WRAP_CONTENT); //TODO: hårdkodat
        layoutParamsMatch = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams = new ViewGroup.LayoutParams(750, ViewGroup.LayoutParams.WRAP_CONTENT);

        // Create a vertical linear layout which the fields will be added into.
        fieldContainer = new LinearLayout(getContext());
        fieldContainer.setOrientation(LinearLayout.VERTICAL);
        fieldContainer.setLayoutParams(linearLayoutParams);
        fieldContainer.setHorizontalGravity(Gravity.CENTER);    // Needed to center the register button.

        // Init listeners.

    }

    // Return the account registration layout.
    public LinearLayout getRegistrationLayout(){

        // Add the "register" button.
        Button registerButton = new Button(getContext());
        registerButton.setText("Register");
        registerButton.setGravity(Gravity.CENTER);
        ViewGroup.LayoutParams buttonParams = new ViewGroup.LayoutParams(600, ViewGroup.LayoutParams.WRAP_CONTENT);
        registerButton.setLayoutParams(buttonParams);

        // Button listener.
        registerButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // If all of the mandatory fields have been inputted then save the inputs.
                for(int i = 0; i < isMandatory.size(); i++){

                    // TODO: Basically, if all of the inputComplete are true at the indexes that should be true, save input.
                    if(isMandatory.get(i)){
                        if(inputComplete.get(i)){
                            System.out.println("Saved user inputs.");
                            // TODO: BUT HOW? när editText.getText är inne i createTextField?
                        }

                    }
                }

            }
        });

        // Add the button to the view.
        fieldContainer.addView(registerButton);

        return fieldContainer;
    }

    public void createTextField(String label, final boolean mandatory){
        // TODO: Vector<Strings> so we can add variables dynamically.

        // Keep track of the mandatory fields.
        isMandatory.add(index, mandatory);

        // Temp linear layout.
        LinearLayout linearLayout = new LinearLayout(getContext());
        linearLayout.setOrientation(HORIZONTAL);
        linearLayout.setLayoutParams(layoutParamsMatch);

        // The TextView and the EditText.
        TextView textView = new TextView(getContext());
        textView.setText(label);
        textView.setPadding(10, 5, 5, 5);
        textView.setLayoutParams(layoutParamsWrap);

        EditText editText = new EditText(getContext());
        editText.setPadding(10, 5, 5, 5);
        editText.setLayoutParams(layoutParams);

        // If it's a text field for a password or an email.
        if(label == "Password"){
            editText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        }
        else if(label == "Email"){
            editText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS | InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        }

        // Add a listener.
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // If the user input has been inputted.
                if(s.length() == 4) {
                    System.out.println("User input complete!");
                    inputComplete.addElement(true);
                }
                else{
                    System.out.println("Index = " + index);
                    if(!inputComplete.isEmpty() && inputComplete.get(index) == null){
                        inputComplete.addElement(false);
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        // Add the views the layout.
        linearLayout.addView(textView);
        linearLayout.addView(editText);
        fieldContainer.addView(linearLayout);

        index++;
    }

    public void createEmailField(boolean isMandatory){
        createTextField("Email", isMandatory);
    }

    public void createPasswordField(boolean isMandatory){
        createTextField("Password", isMandatory);
    }

    public void createBirthField(boolean isMandatory){
        // Parse string to ints.
    }
}
