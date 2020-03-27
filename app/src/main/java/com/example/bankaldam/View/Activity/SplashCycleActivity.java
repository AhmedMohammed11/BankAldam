package com.example.bankaldam.View.Activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.example.bankaldam.Helper.HelperMethod;
import com.example.bankaldam.R;
import com.example.bankaldam.View.Fragment.splashCycle.SliderFragment;
import com.example.bankaldam.View.Fragment.splashCycle.SplashFragment;

public class SplashCycleActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_cycle);

       HelperMethod.ReplaceFragment(getSupportFragmentManager(),new SplashFragment()
                ,R.id.frame,null,null);

        // اجراء موقت للتجربه
//       Intent intent = new Intent(getApplicationContext(),HomeCycleActivity.class);
//        startActivity(intent);
    }
}
