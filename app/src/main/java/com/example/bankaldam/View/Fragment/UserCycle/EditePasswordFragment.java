package com.example.bankaldam.View.Fragment.UserCycle;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bankaldam.Data.Api.ApiService;
import com.example.bankaldam.Data.Model.changepassword.ChangePassword;
import com.example.bankaldam.Helper.HelperMethod;
import com.example.bankaldam.R;
import com.example.bankaldam.View.Fragment.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.bankaldam.Data.Api.RetrofitClient.getClient;


public class EditePasswordFragment extends BaseFragment {

    @BindView(R.id.fra_edit_password_code_et)
    EditText fraEditPasswordCodeEt;
    @BindView(R.id.fra_edit_password_new_password_et)
    EditText fraEditPasswordNewPasswordEt;
    @BindView(R.id.fra_edit_password_confirm_password_et)
    EditText fraEditPasswordConfirmPasswordEt;
    Unbinder unbinder;

    public String phone;
    ApiService apiService;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setUpActivity();
        apiService = getClient().create(ApiService.class);
        View view = inflater.inflate(R.layout.fragment_edite_password, container, false);
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

    @OnClick(R.id.fra_edit_password_edit_btn)
    public void onViewClicked() {
        String pinCode = fraEditPasswordCodeEt.getText().toString();
        String pass = fraEditPasswordNewPasswordEt.getText().toString();
        String confirmpass = fraEditPasswordConfirmPasswordEt.getText().toString();
        getCode(pinCode, pass, confirmpass);
    }

    private void getCode(String pinCode, String pass, String confirmpass) {
        apiService.changePassword(pass, confirmpass, pinCode, phone).enqueue(new Callback<ChangePassword>() {
            @Override
            public void onResponse(Call<ChangePassword> call, Response<ChangePassword> response) {
                try {
                    if (response.body().getStatus() == 1) {
                        LoginFragment loginFragment = new LoginFragment();
                        Toast.makeText(getActivity().getApplicationContext(), "Password Changed Successfully", Toast.LENGTH_SHORT).show();
                        HelperMethod.ReplaceFragment(getActivity().getSupportFragmentManager(),loginFragment,R.id.frame,null,null);
                    }
                } catch (Exception ex) {

                }

            }

            @Override
            public void onFailure(Call<ChangePassword> call, Throwable t) {

            }
        });
    }

}
