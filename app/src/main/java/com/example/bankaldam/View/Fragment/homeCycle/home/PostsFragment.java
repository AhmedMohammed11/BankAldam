package com.example.bankaldam.View.Fragment.homeCycle.home;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.example.bankaldam.Adapter.ArticleAdapter;
import com.example.bankaldam.Adapter.CustomSpinnerAdapter;
import com.example.bankaldam.Data.Api.ApiService;
import com.example.bankaldam.Data.Local.SharedPreferencesManger;
import com.example.bankaldam.Data.Model.generalResposnse.GeneralResponse;
import com.example.bankaldam.Data.Model.generalResposnse.GeneralResponseData;
import com.example.bankaldam.Data.Model.postes.Postes;
import com.example.bankaldam.Data.Model.postes.PostesData;
import com.example.bankaldam.Helper.HelperMethod;
import com.example.bankaldam.Helper.OnEndless;
import com.example.bankaldam.R;
import com.example.bankaldam.View.Fragment.BaseFragment;
import com.example.bankaldam.View.Fragment.homeCycle.RequestFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.bankaldam.Data.Api.RetrofitClient.getClient;


public class PostsFragment extends BaseFragment {

    private LinearLayoutManager linearLayoutManager;
    private List<PostesData> postesDataList = new ArrayList<>();
    private List<String> categories;
    private ArticleAdapter articleAdapter;
    private ApiService apiService;
    private CustomSpinnerAdapter categoryAdapter;
    private Integer maxPage = 0;
    private OnEndless onEndless;
    private int previousPage = 1;
    public boolean viforite = false;

    @BindView(R.id.fragment_posts_fb_fab)
    FloatingActionButton fragmentPostsFbFab;
    @BindView(R.id.home_fragment_til_search)
    TextInputLayout homeFragmentTilSearch;
    @BindView(R.id.home_fragment_sp_categoryes)
    Spinner homeFragmentSpCategoryes;
    @BindView(R.id.home_fragment_ll_container)
    LinearLayout homeFragmentLlContainer;
    @BindView(R.id.home_fragment_rc_posts)
    RecyclerView homeFragmentRcPosts;
    Unbinder unbinder;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setUpActivity();
        View view = inflater.inflate(R.layout.fragment_posts, container, false);
        apiService = getClient().create(ApiService.class);
        unbinder = ButterKnife.bind(this, view);
        getCategory();
        iniRecyclerView();
        return view;
    }

    private void getCategory() {
        apiService.getCategories().enqueue(new Callback<GeneralResponse>() {
            @Override
            public void onResponse(Call<GeneralResponse> call, Response<GeneralResponse> response) {
                try {
                    response.body().getData().add(0, new GeneralResponseData(0, getString(R.string.category)));
                    categories = new ArrayList<>();
                    for (int i = 0; i < response.body().getData().size(); i++) {
                        categories.add(response.body().getData().get(i).getName());
                        categoryAdapter = new CustomSpinnerAdapter(getActivity()
                                , R.layout.item_custom_spanner
                                , R.id.custom_spanner_adapter_tv_names, response.body().getData()
                                , categories);
                        homeFragmentSpCategoryes.setAdapter(categoryAdapter);
                    }
                } catch (Exception e) {
                }
            }

            @Override
            public void onFailure(Call<GeneralResponse> call, Throwable t) {

            }
        });
    }

    private void iniRecyclerView() {
        getPostes(1);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        homeFragmentRcPosts.setLayoutManager(linearLayoutManager);
        articleAdapter = new ArticleAdapter(getActivity(), getActivity()
                , postesDataList, viforite);
        homeFragmentRcPosts.setAdapter(articleAdapter);

        onEndless = new OnEndless(linearLayoutManager, 1) {
            @Override
            public void onLoadMore(int current_page) {
                if (maxPage != 0) {
                    getPostes(current_page);
                }
                if (current_page <= maxPage) {
                    if (maxPage != 0 && current_page != 1)
                        previousPage = current_page;
                    getPostes(current_page);
                } else {
                    onEndless.current_page = previousPage;
                }
            }
        };

        homeFragmentRcPosts.setOnScrollListener(onEndless);
        previousPage = 1;
        onEndless.current_page = 1;
        onEndless.previousTotal = 0;

    }

    ////////////////////////////////////////////////////////////////////////////

    private void getPostes(int page) {

        Call<Postes> call;
        if (viforite) {
            call = apiService.getAllFiviort(SharedPreferencesManger.LoadStringData(getActivity(), "apitoken"), page);
        } else {
            call = apiService.getAllPostes(SharedPreferencesManger.LoadStringData(getActivity(), "apitoken"), page);
        }
        call.enqueue(new Callback<Postes>() {
            @Override
            public void onResponse(Call<Postes> call, Response<Postes> response) {
                if (response.body().getStatus() == 1) {
                    maxPage = response.body().getData().getLastPage();
                    postesDataList.addAll(response.body().getData().getData());
                    articleAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<Postes> call, Throwable t) {

            }
        });
        ///////////////////////////////////////////////////////////////////////////////
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

    @OnClick(R.id.fragment_posts_fb_fab)
    public void onViewClicked() {
        RequestFragment requestFragment = new RequestFragment();
        HelperMethod.ReplaceFragment(getActivity().getSupportFragmentManager(), requestFragment, R.id.fram_homecycle, null, null);
    }
}
