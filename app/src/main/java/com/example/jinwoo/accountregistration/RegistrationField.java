package com.example.jinwoo.accountregistration;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Jinwoo on 2016-05-22.
 */
public class RegistrationField extends LinearLayout {
    private TextView textView;
    private EditText editText;
    private boolean isMandatory;
    private boolean inputComplete;

    public RegistrationField(Context context, String label, boolean mandatory) {
        super(context);
        isMandatory = mandatory;
        if(mandatory) label = label + "*";  // If it's a mandatory field then add the "*".
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

        // Listeners for the EditText.
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // If the field has received the correct input.
                if (s.length() > 4 && isMandatory) {  // TODO: ändra condition för sann input.
                    inputComplete = true;
                } else {
                    inputComplete = false;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    public boolean getInputComplete(){return this.inputComplete;}
}
