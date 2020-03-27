package com.example.bankaldam.View.Fragment.splashCycle;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bankaldam.Helper.HelperMethod;
import com.example.bankaldam.R;
import com.example.bankaldam.View.Activity.HomeCycleActivity;
import com.example.bankaldam.View.Fragment.BaseFragment;

import static com.example.bankaldam.Data.Local.SharedPreferencesManger.LoadBooleanData;
import static com.example.bankaldam.Data.Local.SharedPreferencesManger.REMEMBER;
import static com.example.bankaldam.Data.Local.SharedPreferencesManger.loadUserData;


public class SplashFragment extends BaseFragment implements Runnable {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setUpActivity();
        View view = inflater.inflate(R.layout.fragment_splash, container, false);
        Handler handler = new Handler();
        handler.postDelayed(this, 3000);

        return view;
    }

    @Override
    public void run() {
        if (LoadBooleanData(getActivity(), REMEMBER) && loadUserData(getActivity()) != null) {
            Intent intent = new Intent(getActivity(), HomeCycleActivity.class);
            getActivity().startActivity(intent);
            getActivity().finish();
        } else {
            SliderFragment sliderFragment = new SliderFragment();
            HelperMethod.ReplaceFragment(getActivity().getSupportFragmentManager(), sliderFragment
                    , R.id.frame, null, null);
        }
    }

    @Override
    public void onBack() {
        getActivity().finish();
    }

}
