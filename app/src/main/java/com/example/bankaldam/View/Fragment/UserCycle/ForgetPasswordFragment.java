package com.example.bankaldam.View.Fragment.UserCycle;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bankaldam.Data.Api.ApiService;
import com.example.bankaldam.Data.Model.reset.ResetPassword;
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


public class ForgetPasswordFragment extends BaseFragment {

    @BindView(R.id.fragment_forget_password_et_phone_number)
    EditText fragmentForgetPasswordEtPhoneNumber;
    @BindView(R.id.fragment_forget_password_btn_send_)
    Button fragmentForgetPasswordBtnSend;
    Unbinder unbinder;
    ApiService apiService;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        apiService = getClient().create(ApiService.class);

        setUpActivity();
        View view = inflater.inflate(R.layout.fragment_forget_password, container, false);
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

    @OnClick(R.id.fragment_forget_password_btn_send_)
    public void onViewClicked() {
        String phone = fragmentForgetPasswordEtPhoneNumber.getText().toString();
        resetPassword(phone);
    }

    private void resetPassword(final String phone) {
        apiService.resetPassword(phone).enqueue(new Callback<ResetPassword>() {
            @Override
            public void onResponse(Call<ResetPassword> call, Response<ResetPassword> response) {
                try {
                    if (response.body().getStatus() == 1) {
                        EditePasswordFragment editePasswordFragment = new EditePasswordFragment();
                        editePasswordFragment.phone = phone;
                        HelperMethod.ReplaceFragment(getActivity().getSupportFragmentManager(),editePasswordFragment,R.id.frame,null,null);
                        Toast.makeText(getActivity().getApplicationContext(), "check your phone", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception ex) {

                }

            }

            @Override
            public void onFailure(Call<ResetPassword> call, Throwable t) {

            }
        });
    }
}
