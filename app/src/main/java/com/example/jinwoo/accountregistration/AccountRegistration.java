package com.example.jinwoo.accountregistration;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Created by Jinwoo on 2016-05-08. SKRIV OM KLASSEN HÄR
 */
public class AccountRegistration extends LinearLayout {
    private String password, username, email, company, firstName, lastName;
    // Gender
    // birth date

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

    }

    public void createPassField(){

    }

    public void createTextField(String label, boolean isMandatory){

    }

    public void setNewProperty(){

    }

    /*
        Användaren ska själv kunna lägga in egna fält som ska fyllas i och som kan vara obligatoriska att fylla i. Det är viktigt

        Fixa alla variabler som kan behövs, strings, array/vector med account objekt i?

        Ha en varsin metod för alla olika textfält, tex skapa ett epostfält

        På nåt sätt välja vilka som är obligatoriska, ingen aning hur man gör det, google? bools?


        Tankar: det går egentligen inte att göra som i password och skapa allt i xmlen och sen
        bara importera det? Eftersom vi själva vill skapa allt dynamiskt, eller låta användaren välja



     */



}
