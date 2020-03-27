package com.example.bankaldam.View.Activity;

import android.support.v7.app.AppCompatActivity;

import com.example.bankaldam.View.Fragment.BaseFragment;

public class BaseActivity extends AppCompatActivity {

    public BaseFragment baseFragment;

    public void superBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onBackPressed() {
        baseFragment.onBack();
    }
}

