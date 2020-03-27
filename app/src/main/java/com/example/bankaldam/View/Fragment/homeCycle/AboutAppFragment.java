package com.example.bankaldam.View.Fragment.homeCycle;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bankaldam.R;
import com.example.bankaldam.View.Activity.HomeCycleActivity;
import com.example.bankaldam.View.Fragment.BaseFragment;


public class AboutAppFragment extends BaseFragment {
     HomeCycleActivity homeCycleActivity;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setUpActivity();
        setUpHomeActivity();


        return inflater.inflate(R.layout.fragment_about_app, container, false);
    }

    @Override
    public void onBack() {
        super.onBack();
    }
}
