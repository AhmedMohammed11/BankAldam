package com.example.bankaldam.Adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bankaldam.Data.Api.ApiService;
import com.example.bankaldam.Data.Local.SharedPreferencesManger;
import com.example.bankaldam.Data.Model.addfavorite.AddFavorite;
import com.example.bankaldam.Data.Model.postes.PostesData;
import com.example.bankaldam.Helper.HelperMethod;
import com.example.bankaldam.R;
import com.example.bankaldam.View.Activity.BaseActivity;
import com.example.bankaldam.View.Fragment.homeCycle.home.DetailsPostFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.bankaldam.Data.Api.RetrofitClient.getClient;
import static com.example.bankaldam.Helper.HelperMethod.onLoadImageFromUrl;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ViewHolder> {
    private boolean viforite = false;
    private Context context;
    private Activity activity;
    private List<PostesData> postesDataList = new ArrayList<>();
    BaseActivity baseActivity;
    ApiService apiService;

//    public ImageView imageView;
//    public TextView textView;

    public ArticleAdapter(Context context, Activity activity,
                          List<PostesData> postesDataList, boolean viforite) {
        this.context = context;
        this.activity = activity;
        this.postesDataList = postesDataList;
        this.baseActivity = (BaseActivity) activity;
        this.viforite = viforite;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_view_postes,
                parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        setData(holder, position);
        setAction(holder, position);

    }

    private void setData(ViewHolder holder, int position) {
        onLoadImageFromUrl(holder.cardViewImgImageview, postesDataList.get(position).getThumbnailFullPath(), context, 0);
        holder.cardViewPostesTvTitle.setText(postesDataList.get(position).getTitle());

        if (holder.cardViewPostesCbCheckbox.isChecked()) {

        } else {

        }
    }

    private void setAction(final ViewHolder holder, final int position) {
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                SharedPreferencesManger.SaveData(baseActivity, "s", postesDataList.get(position).getContent());
//                SharedPreferencesManger.SaveData(baseActivity, "img", postesDataList.get(position).getThumbnailFullPath());

                DetailsPostFragment fragment = new DetailsPostFragment();
                fragment.postesdata = postesDataList.get(position);
                HelperMethod.ReplaceFragment(baseActivity.getSupportFragmentManager(), fragment, R.id.fram_homecycle, null, null);
            }
        });

        holder.cardViewPostesCbCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                addFavorite(holder, position);
            }
        });

    }

    private void addFavorite(final ViewHolder holder, final int position) {
        apiService = getClient().create(ApiService.class);
        apiService.addFavorite(postesDataList.get(position).getId(),
                SharedPreferencesManger.LoadStringData(baseActivity, "apitoken"))
                .enqueue(new Callback<AddFavorite>() {

            @Override
            public void onResponse(Call<AddFavorite> call, Response<AddFavorite> response) {
                if (response.body().getStatus() == 1) {
                    postesDataList.get(position).setIsFavourite(
                            !postesDataList.get(position).getIsFavourite());

                    if (holder.cardViewPostesCbCheckbox.isChecked()) {

                    } else {

                    }

                    if (viforite) {
                        postesDataList.remove(position);
                        notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(Call<AddFavorite> call, Throwable t) {

            }
        });

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
        View view;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, view);
        }
    }
}
