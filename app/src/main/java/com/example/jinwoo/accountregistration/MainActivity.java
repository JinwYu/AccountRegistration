package com.example.jinwoo.accountregistration;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import java.util.Hashtable;

public class MainActivity extends AppCompatActivity {
    public Hashtable<String, String> accountInformation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // The registration form.
        final AccountRegistration accountRegistration = new AccountRegistration(this, 4);
        accountRegistration.addField("test", false);
        accountRegistration.addEmailField(true);
        accountRegistration.addPasswordField(true);
        accountRegistration.addNumericField("Age", false);

        // Finally add the register button.
        accountRegistration.addRegisterButton();

        accountRegistration.setMandatoryFieldsListener(new AccountRegistration.mandatoryFieldsListener() {
            @Override
            public void onAccountInformationSaved(Hashtable<String, String> theAccountInformation) {
                accountInformation = theAccountInformation;
                // DEBUG, print the data from the hashtable.
                for (String key : accountInformation.keySet()) {System.out.println("Key = " + key);}
                for (String value : accountInformation.values()) {System.out.println("Value = " + value);}
            }
        });

        setContentView(accountRegistration.getAccountRegistrationForm());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
