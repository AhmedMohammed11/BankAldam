package com.example.bankaldam.View.Fragment.UserCycle;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bankaldam.R;
import com.example.bankaldam.View.Fragment.BaseFragment;


public class CreateAccountFragment extends BaseFragment {





    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setUpActivity();
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

    @Override
    public void onBack() {
        getActivity().finish();
    }
}
