package com.example.bankaldam.View.Fragment.homeCycle;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.example.bankaldam.Adapter.CustomSpinnerAdapter;
import com.example.bankaldam.Data.Api.ApiService;
import com.example.bankaldam.R;
import com.example.bankaldam.View.Activity.MapsActivity;
import com.example.bankaldam.View.Fragment.BaseFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.example.bankaldam.Data.Api.RetrofitClient.getClient;
import static com.example.bankaldam.Helper.MultipleMethodModel.getBloodTypes;
import static com.example.bankaldam.Helper.MultipleMethodModel.getGovernment;


public class RequestFragment extends BaseFragment {

    ApiService apiService;
    @BindView(R.id.fragment_request_et_name)
    TextInputEditText fragmentRequestEtName;
    @BindView(R.id.fragment_request_et_age)
    TextInputEditText fragmentRequestEtAge;
    @BindView(R.id.fragment_request_sp_bloodtypes)
    Spinner fragmentRequestSpBloodtypes;
    @BindView(R.id.fragment_request_sp_count)
    Spinner fragmentRequestSpCount;
    @BindView(R.id.fragment_request_et_hospital_name)
    TextInputEditText fragmentRequestEtHospitalName;
    @BindView(R.id.fragment_request_et_hospital_address)
    TextInputEditText fragmentRequestEtHospitalAddress;
    @BindView(R.id.fragment_request_im_place)
    ImageView fragmentRequestImPlace;
    @BindView(R.id.fragment_request_sp_government)
    Spinner fragmentRequestSpGovernment;
    @BindView(R.id.fragment_request_sp_city)
    Spinner fragmentRequestSpCity;
    @BindView(R.id.fragment_request_et_phone_number)
    TextInputEditText fragmentRequestEtPhoneNumber;
    @BindView(R.id.fragment_request_et_notes)
    TextInputEditText fragmentRequestEtNotes;
    @BindView(R.id.fragment_request_btn_send_request)
    Button fragmentRequestBtnSendRequest;
    Unbinder unbinder;
    @BindView(R.id.fragment_request_rl_container)
    RelativeLayout fragmentRequestRlContainer;

    private List<String> bloodTypesName;
    private CustomSpinnerAdapter BloodTypesAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setUpActivity();
        setUpHomeActivity();
        apiService = getClient().create(ApiService.class);
        View view = inflater.inflate(R.layout.fragment_request, container, false);
        unbinder = ButterKnife.bind(this, view);
        getBloodTypes(getActivity(), apiService, fragmentRequestSpBloodtypes);
        getGovernment(getActivity(), apiService, fragmentRequestSpGovernment, fragmentRequestSpCity
                , fragmentRequestRlContainer);
        return view;
    }

//    private void getBloodTypes() {
//        apiService.getBloodTypes().enqueue(new Callback<GeneralResponse>() {
//            @Override
//            public void onResponse(Call<GeneralResponse> call, Response<GeneralResponse> response) {
//                try {
//                    if (response.body().getStatus() == 1) {
//
//                        response.body().getData().add(0, new GeneralResponseData(0, getString(R.string.blood_type)));
//
//                        bloodTypesName = new ArrayList<>();
//
//                        for (int i = 0; i < response.body().getData().size(); i++) {
//                            bloodTypesName.add(response.body().getData().get(i).getName());
//                        }
//
//                        BloodTypesAdapter =
//                                new CustomSpinnerAdapter(getActivity(), R.layout.item_custom_spanner
//                                        , R.id.custom_spanner_adapter_tv_names, response.body().getData(), bloodTypesName);
//
//                        fragmentRequestSpBloodtypes.setAdapter(BloodTypesAdapter);
//
//                    } else {
//                        Toast.makeText(getActivity(), response.body().getMsg(), Toast.LENGTH_SHORT).show();
//                    }
//                } catch (Exception e) {
//
//                }
//            }
//
//            @Override
//            public void onFailure(Call<GeneralResponse> call, Throwable t) {
//
//            }
//        });
//    }

    @Override
    public void onBack() {
        super.onBack();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @OnClick({R.id.fragment_request_im_place, R.id.fragment_request_btn_send_request})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fragment_request_im_place:
                Intent intent = new Intent(getContext(), MapsActivity.class);
                startActivity(intent);
                break;
            case R.id.fragment_request_btn_send_request:
                String name = fragmentRequestEtName.getText().toString();
                String age = fragmentRequestEtAge.getText().toString();
                int bloodType = fragmentRequestSpBloodtypes.getId();
                //String bagsNum = fragmentRequestSpCount.
                String hospitalName = fragmentRequestEtHospitalName.getText().toString();
                String hospitalAddress = fragmentRequestEtHospitalAddress.getText().toString();
               // int cityId = fragmentRequestSpCity.;


                //apiService.createDonationRequest()
                break;
        }
    }
}
