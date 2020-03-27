package com.example.bankaldam.Helper;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.bankaldam.Adapter.CustomSpinnerAdapter;
import com.example.bankaldam.Data.Api.ApiService;
import com.example.bankaldam.Data.Model.generalResposnse.GeneralResponse;
import com.example.bankaldam.Data.Model.generalResposnse.GeneralResponseData;
import com.example.bankaldam.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MultipleMethodModel {

    public static List<String> bloodTypesNames = new ArrayList<>();
    public static List<String> governmentsNames = new ArrayList<>();
    public static List<String> citesNames = new ArrayList<>();
    public static CustomSpinnerAdapter bloodTypesAdapter, governmentsAdapter, citesAdapter;

    public static void getBloodTypes(final Activity activity, ApiService apiService,
                                     final Spinner spinner) {
        apiService.getBloodTypes().enqueue(new Callback<GeneralResponse>() {
            @Override
            public void onResponse(Call<GeneralResponse> call,
                                   Response<GeneralResponse> response) {
                try {
                    if (response.body().getStatus() == 1) {

                        response.body().getData().add(0,
                                new GeneralResponseData(0,
                                        activity.getResources().getString(R.string.blood_type)));

                        bloodTypesNames = new ArrayList<>();

                        for (int i = 0; i < response.body().getData().size(); i++) {
                            bloodTypesNames.add(response.body().getData().get(i).getName());
                        }

                        bloodTypesAdapter =
                                new CustomSpinnerAdapter(activity,
                                        R.layout.item_custom_spanner
                                        , R.id.custom_spanner_adapter_tv_names,
                                        response.body().getData(), bloodTypesNames);

                        spinner.setAdapter(bloodTypesAdapter);

                    } else {
                        Toast.makeText(activity, response.body().getMsg(),
                                Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {

                }
            }

            @Override
            public void onFailure(Call<GeneralResponse> call, Throwable t) {
                try {
                    Log.d("", "onFailure: " + t.toString());
                } catch (Exception e) {

                }
            }
        });

    }

//    public static void getGovernment(final Activity activity, final ApiService apiService
//            , final Spinner spinner, final CustomSpinnerAdapter governmentsAdapter
//            , final Spinner spinner2, final CustomSpinnerAdapter CitiesAdapter) {
//        apiService.getGovernment().enqueue(new Callback<GeneralResponse>() {
//            @Override
//            public void onResponse(Call<GeneralResponse> call, Response<GeneralResponse> response) {
//                try {
//                    if (response.body().getStatus() == 1) {
//
//                        response.body().getData().add(0, new GeneralResponseData(0, activity.getResources().getString(R.string.government)));
//
//                        governmentsNames = new ArrayList<>();
//
//                        for (int i = 0; i < response.body().getData().size(); i++) {
//                            governmentsNames.add(response.body().getData().get(i).getName());
//                        }
//
//                        governmentsAdapter =
//                                new CustomSpinnerAdapter(activity, R.layout.item_custom_spanner
//                                        , R.id.custom_spanner_adapter_tv_names, response.body().getData(), governmentsNames);
//
//                        spinner.setAdapter(governmentsAdapter);
//
//                        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//                            @Override
//                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                                if (position != 0) {
//                                    getCites(activity, apiService, spinner2, governmentsAdapter.selectId);
//                                } else {
//                                    registerFragmentRlContainerCities.setVisibility(View.GONE);
//                                }
//                            }
//
//                            @Override
//                            public void onNothingSelected(AdapterView<?> parent) {
//
//                            }
//                        });
//
//                    } else {
//                        Toast.makeText(activity, response.body().getMsg(), Toast.LENGTH_SHORT).show();
//                    }
//                } catch (Exception e) {
//
//                }
//            }
//
//            @Override
//            public void onFailure(Call<GeneralResponse> call, Throwable t) {
//                try {
//                    Log.d("", "onFailure: " + t.toString());
//                } catch (Exception e) {
//
//                }
//            }
//        });
//    }

    public static void getGovernment(final Activity activity, final ApiService apiService, final Spinner spinner
            , final Spinner cities, final RelativeLayout registerFragmentRlContainerCities) {

        apiService.getGovernment().enqueue(new Callback<GeneralResponse>() {
            @Override
            public void onResponse(Call<GeneralResponse> call, Response<GeneralResponse> response) {
                try {
                    if (response.body().getStatus() == 1) {

                        response.body().getData().add(0, new GeneralResponseData(0, activity.getResources().getString(R.string.government)));

                        governmentsNames = new ArrayList<>();

                        for (int i = 0; i < response.body().getData().size(); i++) {
                            governmentsNames.add(response.body().getData().get(i).getName());
                        }

                        governmentsAdapter =
                                new CustomSpinnerAdapter(activity, R.layout.item_custom_spanner
                                        , R.id.custom_spanner_adapter_tv_names, response.body().getData(), governmentsNames);

                        spinner.setAdapter(governmentsAdapter);

                        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                if (position != 0) {
                                    getCites(activity, apiService, cities, governmentsAdapter.selectId, registerFragmentRlContainerCities);
                                    //     finalRegisterFragmentRlContainerCities.setVisibility(view.VISIBLE);
                                } else {
                                    registerFragmentRlContainerCities.setVisibility(View.GONE);
                                }
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });

                    } else {
                        Toast.makeText(activity, response.body().getMsg(), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {

                }
            }

            @Override
            public void onFailure(Call<GeneralResponse> call, Throwable t) {
                try {
                    Log.d("", "onFailure: " + t.toString());
                } catch (Exception e) {

                }
            }
        });
    }

    public static void getGovernment(final Activity activity, final ApiService apiService, final Spinner spinner) {

        apiService.getGovernment().enqueue(new Callback<GeneralResponse>() {
            @Override
            public void onResponse(Call<GeneralResponse> call, Response<GeneralResponse> response) {
                try {
                    if (response.body().getStatus() == 1) {

                        response.body().getData().add(0, new GeneralResponseData(0, activity.getResources().getString(R.string.government)));

                        governmentsNames = new ArrayList<>();

                        for (int i = 0; i < response.body().getData().size(); i++) {
                            governmentsNames.add(response.body().getData().get(i).getName());
                        }

                        governmentsAdapter =
                                new CustomSpinnerAdapter(activity, R.layout.item_custom_spanner
                                        , R.id.custom_spanner_adapter_tv_names, response.body().getData(), governmentsNames);

                        spinner.setAdapter(governmentsAdapter);

                        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                if (position != 0) {
                                    //getCites(activity, apiService, cities, governmentsAdapter.selectId, registerFragmentRlContainerCities);
                                    //     finalRegisterFragmentRlContainerCities.setVisibility(view.VISIBLE);
                                } else {
                                  //  registerFragmentRlContainerCities.setVisibility(View.GONE);
                                }
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });

                    } else {
                        Toast.makeText(activity, response.body().getMsg(), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {

                }
            }

            @Override
            public void onFailure(Call<GeneralResponse> call, Throwable t) {
                try {
                    Log.d("", "onFailure: " + t.toString());
                } catch (Exception e) {

                }
            }
        });
    }


    public static void getCites(final Activity activity, ApiService apiService, final Spinner spinner, int selectId, final RelativeLayout registerFragmentRlContainerCities) {
        apiService.getCities(selectId).enqueue(new Callback<GeneralResponse>() {
            @Override
            public void onResponse(Call<GeneralResponse> call, Response<GeneralResponse> response) {
                try {
                    if (response.body().getStatus() == 1) {
                        registerFragmentRlContainerCities.setVisibility(View.VISIBLE);

                        response.body().getData().add(0, new GeneralResponseData(0, activity.getResources().getString(R.string.cites)));

                        citesNames = new ArrayList<>();

                        for (int i = 0; i < response.body().getData().size(); i++) {
                            citesNames.add(response.body().getData().get(i).getName());
                        }

                        citesAdapter =
                                new CustomSpinnerAdapter(activity, R.layout.item_custom_spanner
                                        , R.id.custom_spanner_adapter_tv_names, response.body().getData(), citesNames);

                        spinner.setAdapter(citesAdapter);

                    } else {
                        Toast.makeText(activity, response.body().getMsg(), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {

                }
            }

            @Override
            public void onFailure(Call<GeneralResponse> call, Throwable t) {
                try {
                    Log.d("", "onFailure: " + t.toString());
                } catch (Exception e) {

                }
            }
        });

    }
    public static void getbagsNumber(final Activity activity, ApiService apiService,
                                     final Spinner spinner){


    }


}
