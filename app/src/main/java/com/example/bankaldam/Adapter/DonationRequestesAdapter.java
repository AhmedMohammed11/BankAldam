package com.example.bankaldam.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.bankaldam.Data.Model.donation.RequestesData;
import com.example.bankaldam.Helper.HelperMethod;
import com.example.bankaldam.R;
import com.example.bankaldam.View.Activity.BaseActivity;
import com.example.bankaldam.View.Activity.MapsActivity;
import com.example.bankaldam.View.Fragment.homeCycle.DetailsRequstFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DonationRequestesAdapter extends RecyclerView.Adapter<DonationRequestesAdapter.ViewHolder> {

    private Context context;
    private Activity activity;
    List<RequestesData> dataList;
    BaseActivity baseActivity;

    public DonationRequestesAdapter(Context context, Activity activity, List<RequestesData> dataList,BaseActivity baseActivity) {
        this.context = context;
        this.activity = activity;
        this.dataList = dataList;
        this.baseActivity = (BaseActivity) activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_view_donation_requests, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RequestesData item = dataList.get(position);
        holder.cardViewDonatonRequestesTxtBloodType.setText(item.getBloodType().getName());
        holder.cardViewDonatonRequestesTxtGovernment.setText(context.getString(R.string.cites) + " " + item.getCity().getName());
        holder.cardViewDonatonRequestesTxtHospital.setText(context.getString(R.string.hospital) + " " + item.getHospitalName());
        holder.cardViewDonatonRequestesTxtPationName.setText(context.getString(R.string.pation_name) + " :" + item.getPatientName());
        holder.cardViewDonatonRequestesBtnDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DetailsRequstFragment fragment = new DetailsRequstFragment();
                HelperMethod.ReplaceFragment(baseActivity.getSupportFragmentManager(), fragment, R.id.fram_homecycle, null, null);
            }
        });
        holder.cardViewDonatonRequestesBtnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.card_view_donaton_requestes_txt_blood_type)
        TextView cardViewDonatonRequestesTxtBloodType;
        @BindView(R.id.card_view_donaton_requestes_txt_pation_name)
        TextView cardViewDonatonRequestesTxtPationName;
        @BindView(R.id.card_view_donaton_requestes_txt_hospital)
        TextView cardViewDonatonRequestesTxtHospital;
        @BindView(R.id.card_view_donaton_requestes_txt_Government)
        TextView cardViewDonatonRequestesTxtGovernment;
        @BindView(R.id.card_view_donaton_requestes_btn_details)
        Button cardViewDonatonRequestesBtnDetails;
        @BindView(R.id.card_view_donaton_requestes_btn_call)
        Button cardViewDonatonRequestesBtnCall;
        View view;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, itemView);
        }
    }
}
