package com.example.bankaldam.View.Fragment.UserCycle;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bankaldam.Data.Api.ApiService;
import com.example.bankaldam.Data.Local.SharedPreferencesManger;
import com.example.bankaldam.Data.Model.auth.Auth;
import com.example.bankaldam.Data.Model.auth.AuthData;
import com.example.bankaldam.Helper.HelperMethod;
import com.example.bankaldam.Helper.ValidationClass;
import com.example.bankaldam.R;
import com.example.bankaldam.View.Activity.HomeCycleActivity;
import com.example.bankaldam.View.Fragment.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.bankaldam.Data.Api.RetrofitClient.getClient;


public class LoginFragment extends BaseFragment {

    @BindView(R.id.login_fragment_til_phone)
    TextInputLayout loginFragmentTilPhone;
    @BindView(R.id.login_fragment_til_password)
    TextInputLayout loginFragmentTilPassword;
    @BindView(R.id.login_fragment_cb_remember_me)
    CheckBox loginFragmentCbRememberMe;
    @BindView(R.id.login_fragment_tv_forgit_password)
    TextView loginFragmentTvForgitPassword;
    Unbinder unbinder;

    private ApiService apiService;
    private String apitoken;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        apiService = getClient().create(ApiService.class);
        setUpActivity();
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        SharedPreferencesManger.setSharedPreferences(getActivity());
        unbinder = ButterKnife.bind(this, view);

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

    @OnClick({R.id.login_fragment_tv_forgit_password, R.id.login_fragment_btn_create_new_account, R.id.login_fragment_btn_login, R.id.login_fragment_rl_sub_view})
    public void onViewClicked(View view) {
        HelperMethod.disappearKeypad(getActivity(), view);
        switch (view.getId()) {
            case R.id.login_fragment_tv_forgit_password:
                HelperMethod.ReplaceFragment(getActivity().getSupportFragmentManager(), new ForgetPasswordFragment()
                        , R.id.frame, null, null);
                break;
            case R.id.login_fragment_btn_create_new_account:
                HelperMethod.ReplaceFragment(getActivity().getSupportFragmentManager(), new RegisterFragment()
                        , R.id.frame, null, null);
                break;
            case R.id.login_fragment_btn_login:
                if (loginFragmentCbRememberMe.isChecked()) {
                    AuthData data = new AuthData();
                    SharedPreferencesManger.SaveData(getActivity(), SharedPreferencesManger.REMEMBER, loginFragmentCbRememberMe.isChecked());
                    SharedPreferencesManger.SaveData(getActivity(), SharedPreferencesManger.USER_DATA, data);
                }
                getLoginFields();
                break;

        }
    }

    private void getLoginFields() {
        String phone = loginFragmentTilPhone.getEditText().getText().toString();
        String pass = loginFragmentTilPassword.getEditText().getText().toString();

        if (!ValidationClass.checkPhoneLength(phone, getActivity())) {
            return;
        }

        if (!ValidationClass.checkPassword(pass, getActivity())) {
            return;
        }


        onLogin(phone, pass);
    }

    private void onLogin(String phone, String pass) {
        apiService.onLogin(phone, pass).enqueue(new Callback<Auth>() {
            @Override
            public void onResponse(Call<Auth> call, Response<Auth> response) {
                try {
                    if (response.body().getStatus() == 1) {
                        apitoken = response.body().getData().getApiToken();
                        SharedPreferencesManger.SaveData(getActivity(), "apitoken", apitoken);
                        Intent intent = new Intent(getActivity().getApplicationContext(), HomeCycleActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getActivity().getApplicationContext(), "Wrong Password Or Phone Number", Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception ex) {

                }
            }

            @Override
            public void onFailure(Call<Auth> call, Throwable t) {

            }
        });
    }

}
