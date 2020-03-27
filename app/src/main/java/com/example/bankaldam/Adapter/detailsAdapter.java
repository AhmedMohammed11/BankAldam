package com.example.bankaldam.Adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bankaldam.Data.Model.postes.PostesData;
import com.example.bankaldam.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.bankaldam.Helper.HelperMethod.onLoadImageFromUrl;

public class detailsAdapter extends RecyclerView.Adapter<detailsAdapter.ViewHolder> {
    private Context context;
    private Activity activity;
    private List<PostesData> postesDataList = new ArrayList<>();
    public detailsAdapter(Context context, Activity activity, List<PostesData> postesDataList) {
        this.context = context;
        this.activity = activity;
        this.postesDataList = postesDataList;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_view_postes,
                parent, false);
        ViewHolder viewHolder=new ViewHolder(view);
        return  viewHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return postesDataList.size();
    }



    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.card_view_postes_tv_title)
        TextView cardViewPostesTvTitle;
        @BindView(R.id.card_view_postes_cb_checkbox)
        CheckBox cardViewPostesCbCheckbox;
        @BindView(R.id.card_view_img_imageview)
        ImageView cardViewImgImageview;
        @BindView(R.id.fragment_post_details_im_image)
        ImageView fragmentPostDetailsImImage;
        @BindView(R.id.fragment_post_details_tv_text)
        TextView fragmentPostDetailsTvText;
        View view;
        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, view);
        }
    }
}
