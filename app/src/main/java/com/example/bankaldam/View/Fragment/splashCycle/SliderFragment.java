package com.example.bankaldam.View.Fragment.splashCycle;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bankaldam.Adapter.SliderViewPagerAdapter;
import com.example.bankaldam.R;
import com.example.bankaldam.View.Activity.UserCycleActivity;
import com.example.bankaldam.View.Fragment.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import me.relex.circleindicator.CircleIndicator;


public class SliderFragment extends BaseFragment {

    SliderViewPagerAdapter myPager;
    @BindView(R.id.slider_fragment_vp_slider)
    ViewPager sliderFragmentVpSlider;
    Unbinder unbinder;
    @BindView(R.id.slider_fragment_cr_slider)
    CircleIndicator sliderFragmentCrSlider;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setUpActivity();
        View view = inflater.inflate(R.layout.fragment_slider, container, false);
        unbinder = ButterKnife.bind(this, view);
        myPager = new SliderViewPagerAdapter(getActivity().getApplicationContext());
        sliderFragmentVpSlider.setAdapter(myPager);
        sliderFragmentCrSlider.setViewPager(sliderFragmentVpSlider);

        return view;
    }

    @Override
    public void onBack() {
        getActivity().finish();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.slider_fragment_btn_skip)
    public void onViewClicked() {
        Intent intent = new Intent(getActivity(), UserCycleActivity.class);
        getActivity().startActivity(intent);
        getActivity().finish();
    }

}

