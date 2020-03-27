package com.example.bankaldam.View.Activity;

import android.os.Bundle;

import com.example.bankaldam.Helper.HelperMethod;
import com.example.bankaldam.R;
import com.example.bankaldam.View.Fragment.UserCycle.LoginFragment;
import com.example.bankaldam.View.Fragment.splashCycle.SplashFragment;

public class UserCycleActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_cycle);

        HelperMethod.ReplaceFragment(getSupportFragmentManager(),new LoginFragment()
                ,R.id.frame,null,null);
    }
}
