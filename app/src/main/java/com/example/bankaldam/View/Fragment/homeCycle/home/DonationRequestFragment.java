package com.example.bankaldam.View.Fragment.homeCycle.home;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.bankaldam.Adapter.DonationRequestesAdapter;
import com.example.bankaldam.Data.Api.ApiService;
import com.example.bankaldam.Data.Local.SharedPreferencesManger;
import com.example.bankaldam.Data.Model.donation.DonationReq;
import com.example.bankaldam.Data.Model.donation.RequestesData;
import com.example.bankaldam.Helper.OnEndless;
import com.example.bankaldam.R;
import com.example.bankaldam.View.Fragment.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.bankaldam.Data.Api.RetrofitClient.getClient;
import static com.example.bankaldam.Helper.MultipleMethodModel.getBloodTypes;
import static com.example.bankaldam.Helper.MultipleMethodModel.getGovernment;

public class DonationRequestFragment extends BaseFragment {

    ApiService apiService;
    List<RequestesData> dataList = new ArrayList<RequestesData>();
    @BindView(R.id.fragment_donation_requestes_rv_allrequestes)
    RecyclerView fragmentDonationRequestesRvAllrequestes;
    Unbinder unbinder;
    @BindView(R.id.fragment_donation_request_sp_blood_type)
    Spinner fragmentDonationRequestSpBloodType;
    @BindView(R.id.fragment_donation_request_rl_sp_container_blood_type)
    RelativeLayout fragmentDonationRequestRlSpContainerBloodType;
    @BindView(R.id.fragment_donation_request_sp_government)
    Spinner fragmentDonationRequestSpGovernment;
    @BindView(R.id.fragment_donation_request_rl_sp_container_government)
    RelativeLayout fragmentDonationRequestRlSpContainerGovernment;
    @BindView(R.id.fragment_donation_request_im_search)
    ImageView fragmentDonationRequestImSearch;

    private int maxPage;
    private LinearLayoutManager linearLayoutManager;
    private DonationRequestesAdapter donationRequestesAdapter;
    private OnEndless onEndless;
    private int previousPage;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_donation_requestes, container, false);
        unbinder = ButterKnife.bind(this, view);
        apiService = getClient().create(ApiService.class);
        getBloodTypes(getActivity(), apiService, fragmentDonationRequestSpBloodType);
        getGovernment(getActivity(), apiService, fragmentDonationRequestSpGovernment);
        try{
            homeCycleActivity.setVisibilityTextView(view.GONE);
        }catch (Exception e){
        }

        iniRecyclerView();
        return view;
    }


    private void iniRecyclerView() {
        linearLayoutManager = new LinearLayoutManager(getActivity());
        fragmentDonationRequestesRvAllrequestes.setLayoutManager(linearLayoutManager);

        onEndless = new OnEndless(linearLayoutManager, 1) {
            @Override
            public void onLoadMore(int current_page) {

                if (maxPage != 0) {
                    getallRequestes(current_page);
                }

                if (current_page <= maxPage) {
                    if (maxPage != 0 && current_page != 1)
                        previousPage = current_page;
                    getallRequestes(current_page);
                } else {
                    onEndless.current_page = previousPage;
                }
            }
        };

        donationRequestesAdapter = new DonationRequestesAdapter(getActivity(), getActivity(), dataList, baseActivity);
        fragmentDonationRequestesRvAllrequestes.setAdapter(donationRequestesAdapter);

        fragmentDonationRequestesRvAllrequestes.addOnScrollListener(onEndless);
        getallRequestes(1);

    }

    private void getallRequestes(int i) {
        apiService.getRequestes(SharedPreferencesManger.LoadStringData(getActivity(), "apitoken"), i).enqueue(new Callback<DonationReq>() {
            @Override
            public void onResponse(Call<DonationReq> call, Response<DonationReq> response) {
                try {
                    if (response.body().getStatus() == 1) {
                        maxPage = response.body().getData().getLastPage();
                        dataList.addAll(response.body().getData().getData());
                        donationRequestesAdapter.notifyDataSetChanged();
                    } else {

                    }
                } catch (Exception e) {

                }

            }

            @Override
            public void onFailure(Call<DonationReq> call, Throwable t) {

            }
        });
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
