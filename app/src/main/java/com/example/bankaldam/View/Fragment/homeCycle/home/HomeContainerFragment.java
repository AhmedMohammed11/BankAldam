package com.example.bankaldam.View.Fragment.homeCycle.home;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bankaldam.Adapter.FragmentAdapter;
import com.example.bankaldam.R;
import com.example.bankaldam.View.Fragment.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class HomeContainerFragment extends BaseFragment {

    @BindView(R.id.home_fragment_tl_tab)
    TabLayout homeFragmentTlTab;
    @BindView(R.id.home_fragment_vb_viewpager)
    ViewPager homeFragmentVbViewpager;
    Unbinder unbinder;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setUpActivity();
        setUpHomeActivity();
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        unbinder = ButterKnife.bind(this, view);
        homeCycleActivity.setTitle("Home");

        iniTabLayOutAdapter();
        return view;
    }

    private void iniTabLayOutAdapter() {
        FragmentAdapter adapter = new FragmentAdapter(getChildFragmentManager());
        adapter.addPage(new PostsFragment(), getString(R.string.posts));
        adapter.addPage(new DonationRequestFragment(), getString(R.string.donations));
        homeFragmentVbViewpager.setAdapter(adapter);
        homeFragmentTlTab.setupWithViewPager(homeFragmentVbViewpager);
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
}
