package com.example.bankaldam.View.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.example.bankaldam.View.Activity.BaseActivity;
import com.example.bankaldam.View.Activity.HomeCycleActivity;


public class BaseFragment extends Fragment {
    public BaseActivity baseActivity;
    public HomeCycleActivity homeCycleActivity;

    public void setUpActivity() {
        baseActivity = (BaseActivity) getActivity();
        baseActivity.baseFragment = this;
    }

    public void setUpHomeActivity() {
        try {
            homeCycleActivity = (HomeCycleActivity) getActivity();
        } catch (Exception e) {

        }
    }

    public void onBack() {
        baseActivity.superBackPressed();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        setUpActivity();
        setUpHomeActivity();
        super.onViewCreated(view, savedInstanceState);
    }
}
