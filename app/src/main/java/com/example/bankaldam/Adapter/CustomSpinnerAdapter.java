package com.example.bankaldam.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.bankaldam.Data.Model.generalResposnse.GeneralResponseData;
import com.example.bankaldam.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CustomSpinnerAdapter extends ArrayAdapter<String> {


    private List<GeneralResponseData> generalResponseDataList = new ArrayList<>();
    @BindView(R.id.custom_spanner_adapter_tv_names)
    TextView customSpannerAdapterTvNames;
    LayoutInflater flater;
    public int selectId = 0;

    public CustomSpinnerAdapter(Activity context, int resouceId
            , int textviewId
            , List<GeneralResponseData> generalResponseDataList, List<String> names) {

        super(context, resouceId, textviewId, names);
        this.generalResponseDataList = generalResponseDataList;
        flater = context.getLayoutInflater();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        GeneralResponseData generalResponseData = generalResponseDataList.get(position);

        View view = flater.inflate(R.layout.item_custom_spanner, null, true);
        ButterKnife.bind(this, view);

        customSpannerAdapterTvNames.setText(generalResponseData.getName());
        selectId = generalResponseData.getId();

        return view;
    }

}