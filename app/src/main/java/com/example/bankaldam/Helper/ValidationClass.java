package com.example.bankaldam.Helper;

import android.app.Activity;
import android.support.design.widget.TextInputLayout;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class ValidationClass {

    public static boolean checkPassword(String pass, Activity activity) {
        if (pass.length() < 3) {
            Toast.makeText(activity.getApplicationContext(), "phone cant be less than 3 char", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }

    public static boolean checkPhoneLength(String phone, Activity activity) {
        if (phone.length() < 11) {
            Toast.makeText(activity.getApplicationContext(), "phone cant be less than 11 char", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }

    }
    public static void chekEmptyField(EditText editText){
        if (editText == null) {
            return;
        }
    }
    public static void chekEmptyField(TextInputLayout textInputLayout){
        if (textInputLayout == null) {
            return;
        }
    }
    public static void chekEmptyField(Spinner spinner){
        if (spinner == null) {
            return;
        }
    }

}




