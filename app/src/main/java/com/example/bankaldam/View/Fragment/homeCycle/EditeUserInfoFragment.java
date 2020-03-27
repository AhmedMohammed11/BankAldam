package com.example.bankaldam.View.Fragment.homeCycle;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bankaldam.Adapter.CustomSpinnerAdapter;
import com.example.bankaldam.Data.Api.ApiService;
import com.example.bankaldam.Data.Local.SharedPreferencesManger;
import com.example.bankaldam.Data.Model.DateModel;
import com.example.bankaldam.Data.Model.editeuserinfo.EditUserInfo;
import com.example.bankaldam.Data.Model.generalResposnse.GeneralResponse;
import com.example.bankaldam.Data.Model.generalResposnse.GeneralResponseData;
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


public class EditeUserInfoFragment extends BaseFragment {


    @BindView(R.id.edit_user_info_fragment_tb_tool_bar)
    Toolbar editUserInfoFragmentTbToolBar;
    @BindView(R.id.edit_user_info_fragment_et_user_name)
    EditText editUserInfoFragmentEtUserName;
    @BindView(R.id.edit_user_info_fragment_et_email)
    EditText editUserInfoFragmentEtEmail;
    @BindView(R.id.edit_user_info_fragment_tv_birthday)
    TextView editUserInfoFragmentTvBirthday;
    @BindView(R.id.edit_user_info_fragment_sp_blood_types)
    Spinner editUserInfoFragmentSpBloodTypes;
    @BindView(R.id.edit_user_info_fragment_tv_last_donation_date)
    TextView editUserInfoFragmentTvLastDonationDate;
    @BindView(R.id.edit_user_info_fragment_sp_government)
    Spinner editUserInfoFragmentSpGovernment;
    @BindView(R.id.edit_user_info_fragment_sp_Cities)
    Spinner editUserInfoFragmentSpCities;
    @BindView(R.id.edit_user_info_fragment_et_phone)
    EditText editUserInfoFragmentEtPhone;
    @BindView(R.id.edit_user_info_fragment_et_password)
    EditText editUserInfoFragmentEtPassword;
    @BindView(R.id.edit_user_info_fragment_et_confirm_password)
    EditText editUserInfoFragmentEtConfirmPassword;
    @BindView(R.id.edit_user_info_fragment_btn_edit)
    Button editUserInfoFragmentBtnRegister;
    Unbinder unbinder;
    @BindView(R.id.edit_user_info_fragment_rl_container_Cities)
    RelativeLayout editUserInfoFragmentRlContainerCities;
    private DateModel birthday, lastTime;
    private ApiService apiService;

    private CustomSpinnerAdapter bloodTypesAdapter, governmentsAdapter, citesAdapter;

    List<String> bloodTypesNames = new ArrayList<>();
    List<String> governmentsNames = new ArrayList<>();
    List<String> citesNames = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setUpActivity();

        View view = inflater.inflate(R.layout.fragment_edit_user_info, container, false);
//        HomeCycleActivity homeCycleActivity = new HomeCycleActivity();
//        homeCycleActivity.setVisibilityBack(view.GONE);
//        homeCycleActivity.setVisibilityOpenNavigation(view.GONE);
//        homeCycleActivity.setVisibilityOpenNotification(view.GONE);
        unbinder = ButterKnife.bind(this, view);
        apiService = getClient().create(ApiService.class);
        getBloodTypes();
        getGovernment();

        birthday = new DateModel("01", "01", "1970", "1970-01-01");
        lastTime = new DateModel("01", "01", "1970", "1970-01-01");
        return view;

    }

    @Override
    public void onBack() {
        super.onBack();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @OnClick({R.id.edit_user_info_fragment_tv_birthday, R.id.edit_user_info_fragment_tv_last_donation_date, R.id.edit_user_info_fragment_btn_edit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.edit_user_info_fragment_tv_birthday:
                showCalender(getActivity(), getString(R.string.birth_date), editUserInfoFragmentTvBirthday, birthday);
                break;
            case R.id.edit_user_info_fragment_tv_last_donation_date:
                showCalender(getActivity(), getString(R.string.birth_date), editUserInfoFragmentTvLastDonationDate, lastTime);
                break;
            case R.id.edit_user_info_fragment_btn_edit:
                editUserInfoClick();
                break;
        }
    }

    public void editUserInfoClick() {
        String name = editUserInfoFragmentEtUserName.getText().toString();
        String email = editUserInfoFragmentEtEmail.getText().toString();
        String birthDate = editUserInfoFragmentTvBirthday.getText().toString();
        int cityId = citesAdapter.selectId;
        String phone = editUserInfoFragmentEtPhone.getText().toString();
        String donationLastDate = editUserInfoFragmentTvLastDonationDate.getText().toString();
        String password = editUserInfoFragmentEtPassword.getText().toString();
        String confirmPassword = editUserInfoFragmentEtConfirmPassword.getText().toString();
        int bloodTypeId = bloodTypesAdapter.selectId;
        String apitoken = SharedPreferencesManger.LoadStringData(getActivity(), "apitoken");
        apiService.editUserInfo(name, email, birthDate, cityId, phone, donationLastDate, password, confirmPassword, bloodTypeId, apitoken).enqueue(new Callback<EditUserInfo>() {
            @Override
            public void onResponse(Call<EditUserInfo> call, Response<EditUserInfo> response) {
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
            public void onFailure(Call<EditUserInfo> call, Throwable t) {

            }
        });

    }
    private void getBloodTypes() {

        apiService.getBloodTypes().enqueue(new Callback<GeneralResponse>() {
            @Override
            public void onResponse(Call<GeneralResponse> call, Response<GeneralResponse> response) {
                try {
                    if (response.body().getStatus() == 1) {

                        response.body().getData().add(0, new GeneralResponseData(0, getString(R.string.blood_type)));

                        bloodTypesNames = new ArrayList<>();

                        for (int i = 0; i < response.body().getData().size(); i++) {
                            bloodTypesNames.add(response.body().getData().get(i).getName());
                        }

                        bloodTypesAdapter =
                                new CustomSpinnerAdapter(getActivity(), R.layout.item_custom_spanner
                                        , R.id.custom_spanner_adapter_tv_names, response.body().getData(), bloodTypesNames);

                        editUserInfoFragmentSpBloodTypes.setAdapter(bloodTypesAdapter);

                    } else {
                        Toast.makeText(getActivity(), response.body().getMsg(), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {

                }
            }

            @Override
            public void onFailure(Call<GeneralResponse> call, Throwable t) {
                try {
                    Log.d("", "onFailure: " + t.toString());
                } catch (Exception e) {

                }
            }
        });

    }
    private void getGovernment() {
        apiService.getGovernment().enqueue(new Callback<GeneralResponse>() {
            @Override
            public void onResponse(Call<GeneralResponse> call, Response<GeneralResponse> response) {
                try {
                    if (response.body().getStatus() == 1) {

                        response.body().getData().add(0, new GeneralResponseData(0, getString(R.string.government)));

                        governmentsNames = new ArrayList<>();

                        for (int i = 0; i < response.body().getData().size(); i++) {
                            governmentsNames.add(response.body().getData().get(i).getName());
                        }

                        governmentsAdapter =
                                new CustomSpinnerAdapter(getActivity(), R.layout.item_custom_spanner
                                        , R.id.custom_spanner_adapter_tv_names, response.body().getData(), governmentsNames);

                        editUserInfoFragmentSpGovernment.setAdapter(governmentsAdapter);

                        editUserInfoFragmentSpGovernment.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                if (position != 0) {
                                    getCites(governmentsAdapter.selectId);
                                } else {
                                    editUserInfoFragmentRlContainerCities.setVisibility(View.GONE);
                                }
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });

                    } else {
                        Toast.makeText(getActivity(), response.body().getMsg(), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {

                }
            }

            @Override
            public void onFailure(Call<GeneralResponse> call, Throwable t) {
                try {
                    Log.d("", "onFailure: " + t.toString());
                } catch (Exception e) {

                }
            }
        });
    }

    private void getCites(int selectId) {
        apiService.getCities(selectId).enqueue(new Callback<GeneralResponse>() {
            @Override
            public void onResponse(Call<GeneralResponse> call, Response<GeneralResponse> response) {
                try {
                    if (response.body().getStatus() == 1) {
                        editUserInfoFragmentRlContainerCities.setVisibility(View.VISIBLE);

                        response.body().getData().add(0, new GeneralResponseData(0, getString(R.string.cites)));

                        citesNames = new ArrayList<>();

                        for (int i = 0; i < response.body().getData().size(); i++) {
                            citesNames.add(response.body().getData().get(i).getName());
                        }

                        citesAdapter =
                                new CustomSpinnerAdapter(getActivity(), R.layout.item_custom_spanner
                                        , R.id.custom_spanner_adapter_tv_names, response.body().getData(), citesNames);

                        editUserInfoFragmentSpCities.setAdapter(citesAdapter);

                    } else {
                        Toast.makeText(getActivity(), response.body().getMsg(), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {

                }
            }

            @Override
            public void onFailure(Call<GeneralResponse> call, Throwable t) {
                try {
                    Log.d("", "onFailure: " + t.toString());
                } catch (Exception e) {

                }
            }
        });
    }

}
