package com.example.bankaldam.View.Fragment.homeCycle;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.bankaldam.Data.Api.ApiService;
import com.example.bankaldam.Data.Local.SharedPreferencesManger;
import com.example.bankaldam.Data.Model.contactus.ContactUs;
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


public class ContactUsFragment extends BaseFragment {

    @BindView(R.id.contact_us_fragment_til_name)
    TextInputLayout contactUsFragmentTilName;
    @BindView(R.id.contact_us_fragment_til_email)
    TextInputLayout contactUsFragmentTilEmail;
    @BindView(R.id.contact_us_fragment_til_phone)
    TextInputLayout contactUsFragmentTilPhone;
    @BindView(R.id.contact_us_fragment_til_title)
    TextInputLayout contactUsFragmentTilTitle;
    @BindView(R.id.contact_us_fragment_til_subject)
    TextInputLayout contactUsFragmentTilSubject;
    @BindView(R.id.contact_us_fragment_btn_send)
    Button contactUsFragmentBtnSend;
    Unbinder unbinder;
    private ApiService apiService;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setUpActivity();
            apiService = getClient().create(ApiService.class);
        View view = inflater.inflate(R.layout.fragment_contact_us, container, false);
        unbinder = ButterKnife.bind(this, view);
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

    private void onContactUs(){
        String title = contactUsFragmentTilTitle.getEditText().getText().toString();
        String subject = contactUsFragmentTilSubject.getEditText().getText().toString();
        apiService.contact(SharedPreferencesManger.LoadStringData(getActivity(),"apitoken"),title,subject).enqueue(new Callback<ContactUs>() {
            @Override
            public void onResponse(Call<ContactUs> call, Response<ContactUs> response) {
                Toast.makeText(getActivity(), response.body().getMsg(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ContactUs> call, Throwable t) {


            }
        });
    }

    @OnClick(R.id.contact_us_fragment_btn_send)
    public void onViewClicked() {
        onContactUs();
    }
}
