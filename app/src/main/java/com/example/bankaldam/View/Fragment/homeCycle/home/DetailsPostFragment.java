package com.example.bankaldam.View.Fragment.homeCycle.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bankaldam.Data.Local.SharedPreferencesManger;
import com.example.bankaldam.Data.Model.favorite.FavoriteData;
import com.example.bankaldam.Data.Model.postes.PostesData;
import com.example.bankaldam.Helper.HelperMethod;
import com.example.bankaldam.R;
import com.example.bankaldam.View.Fragment.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.example.bankaldam.Helper.HelperMethod.onLoadImageFromUrl;


public class DetailsPostFragment extends BaseFragment {


    public PostesData postesdata;
    public FavoriteData favoritedata;
    @BindView(R.id.fragment_post_details_im_image)
    ImageView fragmentPostDetailsImImage;
    @BindView(R.id.fragment_post_details_tv_text)
    TextView fragmentPostDetailsTvText;
    Unbinder unbinder;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setUpActivity();


        View view = inflater.inflate(R.layout.fragment_post_details, container, false);
        unbinder = ButterKnife.bind(this, view);
//        HelperMethod.onLoadImageFromUrl(fragmentPostDetailsImImage, SharedPreferencesManger.LoadStringData(getActivity(),"img"),getContext(),0);
//        fragmentPostDetailsTvText.setText(SharedPreferencesManger.LoadStringData(getActivity(),"s"));
        if(postesdata !=null) {
            HelperMethod.onLoadImageFromUrl(fragmentPostDetailsImImage, postesdata.getThumbnailFullPath(), getContext(), 0);
            fragmentPostDetailsTvText.setText(postesdata.getContent());
        }
        else {
            HelperMethod.onLoadImageFromUrl(fragmentPostDetailsImImage, favoritedata.getThumbnailFullPath(), getContext(), 0);
            fragmentPostDetailsTvText.setText(favoritedata.getContent());
        }

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
}
