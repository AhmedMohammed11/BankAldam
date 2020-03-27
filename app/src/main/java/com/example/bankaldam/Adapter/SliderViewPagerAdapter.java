package com.example.bankaldam.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.bankaldam.R;

public class SliderViewPagerAdapter extends PagerAdapter {
    private Context context;

    public SliderViewPagerAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_item, null);
        ImageView imageView = view.findViewById(R.id.row_item_iv_row_item);
        imageView.setImageDrawable(context.getResources().getDrawable(getImageAt(position)));
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    private int getImageAt(int position) {
        switch (position) {
            case 0:
                return R.drawable.slider_page1;
            case 1:
                return R.drawable.sliderpage2;
            default:
                return R.drawable.slider_page1;
        }

    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return o == view;
    }

}
