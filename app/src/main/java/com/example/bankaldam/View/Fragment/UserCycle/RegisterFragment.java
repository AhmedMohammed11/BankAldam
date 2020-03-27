package com.example.bankaldam.View.Fragment.UserCycle;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bankaldam.Adapter.CustomSpinnerAdapter;
import com.example.bankaldam.Data.Api.ApiService;
import com.example.bankaldam.Data.Model.DateModel;
import com.example.bankaldam.Data.Model.auth.Auth;
import com.example.bankaldam.Helper.HelperMethod;
import com.example.bankaldam.R;
import com.example.bankaldam.View.Fragment.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.bankaldam.Data.Api.RetrofitClient.getClient;
import static com.example.bankaldam.Data.Local.SharedPreferencesManger.REMEMBER;
import static com.example.bankaldam.Data.Local.SharedPreferencesManger.SaveData;
import static com.example.bankaldam.Data.Local.SharedPreferencesManger.USER_DATA;
import static com.example.bankaldam.Helper.HelperMethod.showCalender;
import static com.example.bankaldam.Helper.MultipleMethodModel.getBloodTypes;
import static com.example.bankaldam.Helper.MultipleMethodModel.getGovernment;


public class RegisterFragment extends BaseFragment {

    @BindView(R.id.register_fragment_tv_title)
    TextView registerFragmentTvTitle;
    @BindView(R.id.register_fragment_et_user_name)
    EditText registerFragmentEtUserName;
    @BindView(R.id.register_fragment_et_email)
    EditText registerFragmentEtEmail;
    @BindView(R.id.register_fragment_tv_birthday)
    TextView registerFragmentTvBirthday;
    @BindView(R.id.register_fragment_sp_blood_types)
    Spinner registerFragmentSpBloodTypes;
    @BindView(R.id.register_fragment_tv_last_donation_date)
    TextView registerFragmentTvLastDonationDate;
    @BindView(R.id.register_fragment_sp_government)
    Spinner registerFragmentSpGovernment;
    @BindView(R.id.register_fragment_sp_Cities)
    Spinner registerFragmentSpCities;
    @BindView(R.id.register_fragment_et_phone)
    EditText registerFragmentEtPhone;
    @BindView(R.id.register_fragment_et_password)
    EditText registerFragmentEtPassword;
    @BindView(R.id.register_fragment_et_confirm_password)
    EditText registerFragmentEtConfirmPassword;
    @BindView(R.id.register_fragment_rl_container_Cities)
    RelativeLayout registerFragmentRlContainerCities;
    Unbinder unbinder;

    private ApiService apiService;
    private CustomSpinnerAdapter bloodTypesAdapter, governmentsAdapter, citesAdapter;

    List<String> bloodTypesNames = new ArrayList<>();
    List<String> governmentsNames = new ArrayList<>();
    List<String> citesNames = new ArrayList<>();

    private DateModel birthday, lastTime;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setUpActivity();
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        unbinder = ButterKnife.bind(this, view);

        apiService = getClient().create(ApiService.class);

        getBloodTypes(getActivity(), apiService, registerFragmentSpBloodTypes);
        getGovernment(getActivity(), apiService, registerFragmentSpGovernment, registerFragmentSpCities
                , registerFragmentRlContainerCities);


        birthday = new DateModel("01", "01", "1970", "1970-01-01");

        lastTime = new DateModel("01", "01", "1970", "1970-01-01");

        return view;
    }


//    private void getGovernment() {
//        apiService.getGovernment().enqueue(new Callback<GeneralResponse>() {
//            @Override
//            public void onResponse(Call<GeneralResponse> call, Response<GeneralResponse> response) {
//                try {
//                    if (response.body().getStatus() == 1) {
//
//                        response.body().getData().add(0, new GeneralResponseData(0, getString(R.string.government)));
//
//                        governmentsNames = new ArrayList<>();
//
//                        for (int i = 0; i < response.body().getData().size(); i++) {
//                            governmentsNames.add(response.body().getData().get(i).getName());
//                        }
//
//                        governmentsAdapter =
//                                new CustomSpinnerAdapter(getActivity(), R.layout.item_custom_spanner
//                                        , R.id.custom_spanner_adapter_tv_names, response.body().getData(), governmentsNames);
//
//                        registerFragmentSpGovernment.setAdapter(governmentsAdapter);
//
//                        registerFragmentSpGovernment.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//                            @Override
//                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                                if (position != 0) {
//                                    getCites(governmentsAdapter.selectId);
//                                } else {
//                                    registerFragmentRlContainerCities.setVisibility(View.GONE);
//                                }
//                            }
//
//                            @Override
//                            public void onNothingSelected(AdapterView<?> parent) {
//
//                            }
//                        });
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
//                try {
//                    Log.d("", "onFailure: " + t.toString());
//                } catch (Exception e) {
//
//                }
//            }
//        });
//    }


//    private void getCites(int selectId) {
//        apiService.getCities(selectId).enqueue(new Callback<GeneralResponse>() {
//            @Override
//            public void onResponse(Call<GeneralResponse> call, Response<GeneralResponse> response) {
//                try {
//                    if (response.body().getStatus() == 1) {
//                        registerFragmentRlContainerCities.setVisibility(View.VISIBLE);
//
//                        response.body().getData().add(0, new GeneralResponseData(0, getString(R.string.cites)));
//
//                        citesNames = new ArrayList<>();
//
//                        for (int i = 0; i < response.body().getData().size(); i++) {
//                            citesNames.add(response.body().getData().get(i).getName());
//                        }
//
//                        citesAdapter =
//                                new CustomSpinnerAdapter(getActivity(), R.layout.item_custom_spanner
//                                        , R.id.custom_spanner_adapter_tv_names, response.body().getData(), citesNames);
//
//                        registerFragmentSpCities.setAdapter(citesAdapter);
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
//                try {
//                    Log.d("", "onFailure: " + t.toString());
//                } catch (Exception e) {
//
//                }
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

    @OnClick({R.id.register_fragment_tv_birthday, R.id.register_fragment_tv_last_donation_date
            , R.id.register_fragment_btn_Register, R.id.register_fragment_rl_sub_view})
    public void onViewClicked(View view) {
        HelperMethod.disappearKeypad(getActivity(), view);
        switch (view.getId()) {
            case R.id.register_fragment_tv_birthday:
                showCalender(getActivity(), getString(R.string.birth_date), registerFragmentTvBirthday, birthday);
                break;
            case R.id.register_fragment_tv_last_donation_date:
                showCalender(getActivity(), getString(R.string.last_time), registerFragmentTvLastDonationDate, lastTime);
                break;
            case R.id.register_fragment_btn_Register:
                try {
                    onRegister();
                } catch (Exception e) {

                }

                break;
            case R.id.register_fragment_rl_sub_view:
                break;
        }
    }

    private void onRegister() {

        String name = registerFragmentEtUserName.getText().toString();
        String email = registerFragmentEtEmail.getText().toString();
        String birth_date = registerFragmentTvBirthday.getText().toString();
        int city_id = citesAdapter.selectId;
        String phone = registerFragmentEtPhone.getText().toString();
        String donation_last_date = registerFragmentTvLastDonationDate.getText().toString();
        String password = registerFragmentEtPassword.getText().toString();
        String password_confirmation = registerFragmentEtConfirmPassword.getText().toString();
        int blood_type_id = bloodTypesAdapter.selectId;

        apiService.onRegister(name, email, birth_date, city_id, phone, donation_last_date, password,
                password_confirmation, blood_type_id).enqueue(new Callback<Auth>() {
            @Override
            public void onResponse(Call<Auth> call, Response<Auth> response) {
                try {
                    if (response.body().getStatus() == 1) {
                        SaveData(getActivity(), USER_DATA, response.body().getData());
                        SaveData(getActivity(), REMEMBER, true);
                    }

                    Toast.makeText(getActivity(), response.body().getMsg(), Toast.LENGTH_SHORT).show();
                } catch (Exception e) {

                }
            }

            @Override
            public void onFailure(Call<Auth> call, Throwable t) {

            }
        });

    }
}
