package com.example.bankaldam.View.Fragment.homeCycle;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.bankaldam.R;
import com.example.bankaldam.View.Fragment.BaseFragment;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class DetailsRequstFragment extends BaseFragment implements OnMapReadyCallback {

    GoogleMap map;
    @BindView(R.id.fragment_dtails_request_tv_name)
    TextView fragmentDtailsRequestTvName;
    @BindView(R.id.fragment_dtails_request_tv_age)
    TextView fragmentDtailsRequestTvAge;
    @BindView(R.id.fragment_dtails_request_tv_blood_type)
    TextView fragmentDtailsRequestTvBloodType;
    @BindView(R.id.fragment_dtails_request_tv_number_of_packs)
    TextView fragmentDtailsRequestTvNumberOfPacks;
    @BindView(R.id.fragment_dtails_request_tv_hospital)
    TextView fragmentDtailsRequestTvHospital;
    @BindView(R.id.fragment_dtails_request_tv_hospital_address)
    TextView fragmentDtailsRequestTvHospitalAddress;
    @BindView(R.id.fragment_dtails_request_tv_phone_number)
    TextView fragmentDtailsRequestTvPhoneNumber;
    @BindView(R.id.fragment_dtails_request_tv_details)
    TextView fragmentDtailsRequestTvDetails;
    @BindView(R.id.mapView)
    MapView mapView;
    @BindView(R.id.fragment_dtails_request_btn_call)
    Button fragmentDtailsRequestBtnCall;
    Unbinder unbinder;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        setUpActivity();
//        setUpHomeActivity();
        View view = inflater.inflate(R.layout.fragment_details_request, container, false);
        unbinder = ButterKnife.bind(this, view);
        mapView.getMapAsync(this);
        mapView.onCreate(savedInstanceState);
        return view;
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.map = googleMap;
    }

    @Override
    public void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }



    @Override
    public void onBack() {
        super.onBack();
    }


}
